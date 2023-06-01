/*
 * Copyright 2013 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.internal.deser;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlScalar;

public class CharacterYAMLDeserializer implements YAMLDeserializer<Character> {

  public static final CharacterYAMLDeserializer INSTANCE = new CharacterYAMLDeserializer();

  @Override
  public Character deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    YamlNode value = yaml.getNode(key);
    return deserialize(value, ctx);
  }

  @Override
  public Character deserialize(YamlNode value, YAMLDeserializationContext ctx) {
    if (value == null || value.isEmpty()) {
      return '\u0000';
    }
    YamlScalar<String> scalar = value.asScalar();
    return scalar.value().charAt(0);
  }
}
