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

package com.facebook.buck.core.model.actiongraph;

import com.facebook.buck.core.rules.BuildRuleResolver;
import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import org.immutables.value.Value;

/** Holds an ActionGraph with the BuildRuleResolver that created it. */
@Value.Immutable
@BuckStyleImmutable
interface AbstractActionGraphAndResolver {
  @Value.Parameter
  ActionGraph getActionGraph();

  @Value.Parameter
  BuildRuleResolver getResolver();
}
