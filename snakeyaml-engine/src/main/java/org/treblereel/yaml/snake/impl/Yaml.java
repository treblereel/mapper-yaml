/*
 * Copyright © 2023 Treblereel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.treblereel.yaml.snake.impl;

import java.util.Map;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.treblereel.gwt.yaml.api.YamlMappingNode;

public class Yaml {

  private Yaml() {}

  public static YamlMappingNode create() {
    return new YamlMappingNodeImpl();
  }

  public static YamlMappingNode create(DumpSettings settings) {
    return new YamlMappingNodeImpl(settings);
  }

  public static YamlMappingNode fromString(String yaml) {
    LoadSettings settings = LoadSettings.builder().build();
    return fromString(settings, yaml);
  }

  @SuppressWarnings("unchecked")
  public static YamlMappingNode fromString(LoadSettings settings, String yaml) {
    Load load = new Load(settings);
    Map<String, Object> map = (Map<String, Object>) load.loadFromString(yaml);
    return new YamlMappingNodeImpl(map);
  }
}
