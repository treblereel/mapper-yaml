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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;

public class BooleanYAMLDeserializer implements YAMLDeserializer<Boolean> {

  public static final BooleanYAMLDeserializer INSTANCE = new BooleanYAMLDeserializer();

  @Override
  public Boolean deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    return deserialize(yaml.value(key), ctx);
  }

  @Override
  public Boolean deserialize(YamlNode value, YAMLDeserializationContext ctx) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    return Boolean.valueOf(value.asScalar().value());
  }
}
