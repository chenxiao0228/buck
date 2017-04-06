/*
 * Copyright 2012-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.artifact_cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import com.facebook.buck.io.BorrowablePath;
import com.facebook.buck.io.LazyPath;
import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.rules.RuleKey;
import com.facebook.buck.testutil.integration.TemporaryPaths;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nullable;

public class MultiArtifactCacheTest {

  @Rule
  public TemporaryPaths tmp = new TemporaryPaths();

  private static final RuleKey dummyRuleKey =
      new RuleKey("76b1c1beae69428db2d1befb31cf743ac8ce90df");
  private static final LazyPath dummyFile = LazyPath.ofInstance(Paths.get("dummy"));

  // An cache which always returns errors from fetching.
  class ErroringArtifactCache extends NoopArtifactCache {

    @Override
    public CacheResult fetch(RuleKey ruleKey, LazyPath output) {
      return CacheResult.error("cache", "error");
    }

  }

  @Test
  public void testCacheFetch() throws InterruptedException, IOException {
    DummyArtifactCache dummyArtifactCache1 = new DummyArtifactCache();
    DummyArtifactCache dummyArtifactCache2 = new DummyArtifactCache();
    MultiArtifactCache multiArtifactCache = new MultiArtifactCache(ImmutableList.of(
        (ArtifactCache) dummyArtifactCache1,
        dummyArtifactCache2));

    assertEquals(
        "Fetch should fail",
        CacheResultType.MISS,
        multiArtifactCache.fetch(dummyRuleKey, dummyFile).getType());

    dummyArtifactCache1.store(
        ArtifactInfo.builder().addRuleKeys(dummyRuleKey).build(),
        BorrowablePath.notBorrowablePath(dummyFile.get()));
    assertEquals(
        "Fetch should succeed after store",
        CacheResultType.HIT,
        multiArtifactCache.fetch(dummyRuleKey, dummyFile).getType());

    dummyArtifactCache1.reset();
    dummyArtifactCache2.reset();
    dummyArtifactCache2.store(
        ArtifactInfo.builder().addRuleKeys(dummyRuleKey).build(),
        BorrowablePath.notBorrowablePath(dummyFile.get()));
    assertEquals("Fetch should succeed after store",
        CacheResultType.HIT,
        multiArtifactCache.fetch(dummyRuleKey, dummyFile).getType());

    multiArtifactCache.close();
  }

  @Test
  public void testCacheStore() throws InterruptedException, IOException {
    DummyArtifactCache dummyArtifactCache1 = new DummyArtifactCache();
    DummyArtifactCache dummyArtifactCache2 = new DummyArtifactCache();
    MultiArtifactCache multiArtifactCache = new MultiArtifactCache(ImmutableList.of(
        dummyArtifactCache1,
        dummyArtifactCache2));

    multiArtifactCache.store(
        ArtifactInfo.builder().addRuleKeys(dummyRuleKey).build(),
        BorrowablePath.notBorrowablePath(dummyFile.get()));

    assertEquals(
        "MultiArtifactCache.store() should store to all contained ArtifactCaches",
        dummyArtifactCache1.storeKey,
        dummyRuleKey);
    assertEquals("MultiArtifactCache.store() should store to all contained ArtifactCaches",
        dummyArtifactCache2.storeKey,
        dummyRuleKey);

    multiArtifactCache.close();
  }

  private static class SimpleArtifactCache implements ArtifactCache {
    private final ProjectFilesystem filesystem;
    @Nullable
    public AtomicReference<RuleKey> storedKey;

    public SimpleArtifactCache(ProjectFilesystem filesystem) {
      this.filesystem = filesystem;
      this.storedKey = new AtomicReference<>(null);
    }

    @Override
    public CacheResult fetch(RuleKey ruleKey, LazyPath output) {
      if (ruleKey.equals(storedKey.get())) {
        try {
          filesystem.touch(output.get());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return CacheResult.hit("cache");
      } else {
        return CacheResult.miss();
      }
    }

    @Override
    public ListenableFuture<Void> store(
        ArtifactInfo info, BorrowablePath output) {
      if (output.canBorrow()) {
        try {
          filesystem.deleteFileAtPath(output.getPath());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      storedKey.set(Iterables.getFirst(info.getRuleKeys(), null));
      return Futures.immediateFuture(null);
    }

    @Override
    public CacheReadMode getCacheReadMode() {
      return CacheReadMode.readwrite;
    }

    @Override
    public void close() {
    }
  }

  @Test
  public void testCacheStoreWithBorrowablePathConsumingCache()
      throws InterruptedException, IOException, ExecutionException {
    ProjectFilesystem filesystem = new ProjectFilesystem(tmp.getRoot());
    Path fetchFile = filesystem.resolve("fetch_file");

    SimpleArtifactCache fakeDirCache = new SimpleArtifactCache(filesystem);
    SimpleArtifactCache fakeReadOnlyCache = new SimpleArtifactCache(filesystem) {
      @Override
      public CacheReadMode getCacheReadMode() {
        return CacheReadMode.readonly;
      }
    };
    MultiArtifactCache multiArtifactCache = new MultiArtifactCache(ImmutableList.of(
        fakeDirCache,
        fakeReadOnlyCache));

    fakeReadOnlyCache.storedKey.set(dummyRuleKey);

    multiArtifactCache.fetch(
        dummyRuleKey,
        LazyPath.ofInstance(fetchFile));

    assertThat(
        "The .get() call should not delete the path it's fetching.",
        filesystem.exists(fetchFile),
        Matchers.is(true));

    multiArtifactCache.close();
  }

  @Test
  public void preserveErrorsFromInnerCache() throws InterruptedException, IOException {
    ErroringArtifactCache inner = new ErroringArtifactCache();
    MultiArtifactCache cache = new MultiArtifactCache(ImmutableList.of(inner));
    CacheResult result = cache.fetch(dummyRuleKey, dummyFile);
    assertSame(result.getType(), CacheResultType.ERROR);
    cache.close();
  }

  @Test
  public void cacheFetchPushesMetadataToHigherCache() throws Exception {
    InMemoryArtifactCache cache1 = new InMemoryArtifactCache();
    InMemoryArtifactCache cache2 = new InMemoryArtifactCache();
    MultiArtifactCache multiArtifactCache =
        new MultiArtifactCache(ImmutableList.of(
            cache1,
            cache2));

    LazyPath output = LazyPath.ofInstance(tmp.newFile());

    ImmutableMap<String, String> metadata = ImmutableMap.of("hello", "world");
    cache2.store(
        ArtifactInfo.builder().addRuleKeys(dummyRuleKey).setMetadata(metadata).build(),
        new byte[0]);
    multiArtifactCache.fetch(dummyRuleKey, output);

    CacheResult result = cache1.fetch(dummyRuleKey, output);
    assertThat(
        result.getType(),
        Matchers.equalTo(CacheResultType.HIT));
    assertThat(
        result.getMetadata(),
        Matchers.equalTo(metadata));

    multiArtifactCache.close();
  }

}
