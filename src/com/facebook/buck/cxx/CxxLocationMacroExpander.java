/*
 * Copyright 2017-present Facebook, Inc.
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

package com.facebook.buck.cxx;

import com.facebook.buck.core.rules.BuildRule;
import com.facebook.buck.core.rules.BuildRuleResolver;
import com.facebook.buck.cxx.toolchain.CxxPlatform;
import com.facebook.buck.model.macros.MacroException;
import com.facebook.buck.rules.macros.LocationMacro;
import com.facebook.buck.rules.macros.LocationMacroExpander;

class CxxLocationMacroExpander extends LocationMacroExpander {

  private final CxxPlatform platform;

  CxxLocationMacroExpander(CxxPlatform platform) {
    this.platform = platform;
  }

  @Override
  protected BuildRule resolve(BuildRuleResolver resolver, LocationMacro input)
      throws MacroException {
    BuildRule rule = super.resolve(resolver, input);
    if (rule instanceof CxxGenrule) {
      rule = resolver.requireRule(rule.getBuildTarget().withAppendedFlavors(platform.getFlavor()));
    }
    return rule;
  }
}
