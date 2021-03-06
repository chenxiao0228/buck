/*
 * Copyright 2016-present Facebook, Inc.
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

package com.facebook.buck.parser;

import com.facebook.buck.core.cell.Cell;
import com.facebook.buck.event.BuckEventBus;
import com.facebook.buck.log.Logger;
import com.facebook.buck.parser.api.BuildFileManifest;
import com.facebook.buck.parser.api.ProjectBuildFileParser;
import com.facebook.buck.util.concurrent.ResourcePool;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.GuardedBy;

/**
 * Allows multiple concurrently executing futures to share a constrained number of parsers.
 *
 * <p>Parser instances are lazily created up till a fixed maximum. If more than max parser are
 * requested the associated 'requests' are queued up. As soon as a parser is returned it will be
 * used to satisfy the first pending request, otherwise it is "parked".
 */
class ProjectBuildFileParserPool implements AutoCloseable {
  private static final Logger LOG = Logger.get(ProjectBuildFileParserPool.class);

  private final int maxParsersPerCell;

  @GuardedBy("this")
  private final Map<Cell, ResourcePool<ProjectBuildFileParser>> parserResourcePools;

  private final ProjectBuildFileParserFactory projectBuildFileParserFactory;
  private final AtomicBoolean closing;
  private final boolean enableProfiler;

  /** @param maxParsersPerCell maximum number of parsers to create for a single cell. */
  public ProjectBuildFileParserPool(
      int maxParsersPerCell,
      ProjectBuildFileParserFactory projectBuildFileParserFactory,
      boolean enableProfiler) {
    Preconditions.checkArgument(maxParsersPerCell > 0);

    this.maxParsersPerCell = maxParsersPerCell;
    this.parserResourcePools = new HashMap<>();
    this.projectBuildFileParserFactory = projectBuildFileParserFactory;
    this.closing = new AtomicBoolean(false);
    this.enableProfiler = enableProfiler;
  }

  /**
   * @param cell the cell in which we're parsing
   * @param buildFile the file to parse
   * @param executorService where to perform the parsing.
   * @return a {@link ListenableFuture} containing the result of the parsing. The future will be
   *     cancelled if the {@link ProjectBuildFileParserPool#close()} method is called.
   */
  public ListenableFuture<BuildFileManifest> getBuildFileManifest(
      BuckEventBus buckEventBus,
      Cell cell,
      Path buildFile,
      AtomicLong processedBytes,
      ListeningExecutorService executorService) {
    Preconditions.checkState(!closing.get());

    return getResourcePoolForCell(buckEventBus, cell)
        .scheduleOperationWithResource(
            parser -> parser.getBuildFileManifest(buildFile, processedBytes), executorService);
  }

  private synchronized ResourcePool<ProjectBuildFileParser> getResourcePoolForCell(
      BuckEventBus buckEventBus, Cell cell) {
    return parserResourcePools.computeIfAbsent(
        cell,
        c ->
            new ResourcePool<>(
                maxParsersPerCell,
                // If the Python process garbles the output stream then the bser codec doesn't
                // always
                // recover and subsequent attempts at invoking the parser will fail.
                ResourcePool.ResourceUsageErrorPolicy.RETIRE,
                () -> projectBuildFileParserFactory.createBuildFileParser(buckEventBus, c)));
  }

  private void reportProfile() {
    if (!enableProfiler) {
      return;
    }
    synchronized (this) {
      parserResourcePools
          .values()
          .forEach(
              resourcePool -> {
                resourcePool.callOnEachResource(
                    parser -> {
                      try {
                        parser.reportProfile();
                      } catch (IOException exception) {
                        LOG.debug(
                            exception,
                            "Exception raised during reportProfile() and we're ignoring it");
                      }
                    });
              });
    }
  }

  @Override
  public void close() {
    reportProfile();
    ImmutableSet<ResourcePool<ProjectBuildFileParser>> resourcePools;
    synchronized (this) {
      Preconditions.checkState(!closing.get());
      closing.set(true);
      resourcePools = ImmutableSet.copyOf(parserResourcePools.values());
    }
    resourcePools.forEach(ResourcePool::close);
  }
}
