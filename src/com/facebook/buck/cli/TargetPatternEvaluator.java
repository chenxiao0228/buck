/*
 * Copyright 2015-present Facebook, Inc.
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

package com.facebook.buck.cli;

import com.facebook.buck.config.BuckConfig;
import com.facebook.buck.core.cell.Cell;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.sourcepath.PathSourcePath;
import com.facebook.buck.event.BuckEventBus;
import com.facebook.buck.log.Logger;
import com.facebook.buck.parser.BuildTargetPatternTargetNodeParser;
import com.facebook.buck.parser.Parser;
import com.facebook.buck.parser.ParserConfig;
import com.facebook.buck.parser.SpeculativeParsing;
import com.facebook.buck.parser.TargetNodeSpec;
import com.facebook.buck.parser.exceptions.BuildFileParseException;
import com.facebook.buck.query.QueryBuildTarget;
import com.facebook.buck.query.QueryFileTarget;
import com.facebook.buck.query.QueryTarget;
import com.facebook.buck.util.MoreMaps;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TargetPatternEvaluator {
  private static final Logger LOG = Logger.get(TargetPatternEvaluator.class);

  private final Parser parser;
  private final BuckEventBus eventBus;
  private final boolean enableProfiling;
  private final Path projectRoot;
  private final CommandLineTargetNodeSpecParser targetNodeSpecParser;
  private final BuckConfig buckConfig;
  private final Cell rootCell;

  private Map<String, ImmutableSet<QueryTarget>> resolvedTargets = new HashMap<>();

  public TargetPatternEvaluator(
      Cell rootCell,
      BuckConfig buckConfig,
      Parser parser,
      BuckEventBus eventBus,
      boolean enableProfiling) {
    this.rootCell = rootCell;
    this.parser = parser;
    this.eventBus = eventBus;
    this.enableProfiling = enableProfiling;
    this.buckConfig = buckConfig;
    this.projectRoot = rootCell.getFilesystem().getRootPath();
    this.targetNodeSpecParser =
        new CommandLineTargetNodeSpecParser(buckConfig, new BuildTargetPatternTargetNodeParser());
  }

  /** Attempts to parse and load the given collection of patterns. */
  public void preloadTargetPatterns(Iterable<String> patterns, ListeningExecutorService executor)
      throws InterruptedException, BuildFileParseException, IOException {
    resolveTargetPatterns(patterns, executor);
  }

  ImmutableMap<String, ImmutableSet<QueryTarget>> resolveTargetPatterns(
      Iterable<String> patterns, ListeningExecutorService executor)
      throws InterruptedException, BuildFileParseException, IOException {
    ImmutableMap.Builder<String, ImmutableSet<QueryTarget>> resolved = ImmutableMap.builder();

    Map<String, String> unresolved = new HashMap<>();
    for (String pattern : patterns) {

      // First check if this pattern was resolved before.
      ImmutableSet<QueryTarget> targets = resolvedTargets.get(pattern);
      if (targets != null) {
        resolved.put(pattern, targets);
        continue;
      }

      // Check if this is an alias.
      ImmutableSet<BuildTarget> aliasTargets = buckConfig.getBuildTargetsForAlias(pattern);
      if (!aliasTargets.isEmpty()) {
        for (BuildTarget alias : aliasTargets) {
          unresolved.put(alias.getFullyQualifiedName(), pattern);
        }
      } else {
        // Check if the pattern corresponds to a build target or a path.
        if (pattern.contains("//") || pattern.startsWith(":")) {
          unresolved.put(pattern, pattern);
        } else {
          ImmutableSet<QueryTarget> fileTargets = resolveFilePattern(pattern);
          resolved.put(pattern, fileTargets);
          resolvedTargets.put(pattern, fileTargets);
        }
      }
    }

    // Resolve any remaining target patterns using the parser.
    ImmutableMap<String, ImmutableSet<QueryTarget>> results =
        MoreMaps.transformKeys(
            resolveBuildTargetPatterns(ImmutableList.copyOf(unresolved.keySet()), executor),
            Functions.forMap(unresolved));
    resolved.putAll(results);
    resolvedTargets.putAll(results);

    return resolved.build();
  }

  ImmutableSet<QueryTarget> resolveFilePattern(String pattern) throws IOException {
    ImmutableSet<Path> filePaths =
        PathArguments.getCanonicalFilesUnderProjectRoot(projectRoot, ImmutableList.of(pattern))
            .relativePathsUnderProjectRoot;

    return filePaths
        .stream()
        .map(path -> PathSourcePath.of(rootCell.getFilesystem(), path))
        .map(QueryFileTarget::of)
        .collect(ImmutableSortedSet.toImmutableSortedSet(Ordering.natural()));
  }

  ImmutableMap<String, ImmutableSet<QueryTarget>> resolveBuildTargetPatterns(
      List<String> patterns, ListeningExecutorService executor)
      throws InterruptedException, BuildFileParseException, IOException {

    // Build up an ordered list of patterns and pass them to the parse to get resolved in one go.
    // The returned list of nodes maintains the spec list ordering.
    List<TargetNodeSpec> specs = new ArrayList<>();
    for (String pattern : patterns) {
      specs.addAll(targetNodeSpecParser.parse(rootCell.getCellPathResolver(), pattern));
    }
    ImmutableList<ImmutableSet<BuildTarget>> buildTargets =
        parser.resolveTargetSpecs(
            eventBus,
            rootCell,
            enableProfiling,
            executor,
            specs,
            SpeculativeParsing.DISABLED,
            // We disable mapping //path/to:lib to //path/to:lib#default,static
            // because the query engine doesn't handle flavors very well.
            ParserConfig.ApplyDefaultFlavorsMode.DISABLED);
    LOG.verbose("Resolved target patterns %s -> targets %s", patterns, buildTargets);

    // Convert the ordered result into a result map of pattern to set of resolved targets.
    ImmutableMap.Builder<String, ImmutableSet<QueryTarget>> queryTargets = ImmutableMap.builder();
    for (int index = 0; index < buildTargets.size(); index++) {
      ImmutableSet<BuildTarget> targets = buildTargets.get(index);
      // Sorting to have predictable results across different java libraries implementations.
      ImmutableSet.Builder<QueryTarget> builder = ImmutableSortedSet.naturalOrder();
      for (BuildTarget target : targets) {
        builder.add(QueryBuildTarget.of(target));
      }
      queryTargets.put(patterns.get(index), builder.build());
    }
    return queryTargets.build();
  }
}
