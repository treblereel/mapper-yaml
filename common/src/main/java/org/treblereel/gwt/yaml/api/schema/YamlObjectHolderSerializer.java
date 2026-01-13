/*
 * Copyright © 2026 Treblereel
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

package org.treblereel.gwt.yaml.api.schema;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

public class YamlObjectHolderSerializer
    implements YAMLSerializer<YamlObjectHolder>, YAMLDeserializer<YamlObjectHolder> {

  @Override
  public YamlObjectHolder deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    return new YamlObjectHolderImpl();
  }

  @Override
  public YamlObjectHolder deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    return new YamlObjectHolderImpl();
  }

  @Override
  public void serialize(
      YamlMapping writer,
      String propertyName,
      YamlObjectHolder value,
      YAMLSerializationContext ctx) {}

  @Override
  public void serialize(
      YamlSequence writer, YamlObjectHolder value, YAMLSerializationContext ctx) {}

  static class YamlObjectHolderImpl implements YamlObjectHolder {}
}
