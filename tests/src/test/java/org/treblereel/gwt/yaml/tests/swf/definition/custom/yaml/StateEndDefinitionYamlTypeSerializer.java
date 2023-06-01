/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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

package org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.BooleanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.BooleanYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.NodeType;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlSequence;
import org.treblereel.gwt.yaml.tests.swf.definition.StateEnd;
import org.treblereel.gwt.yaml.tests.swf.definition.StateEnd_YamlMapperImpl;

public class StateEndDefinitionYamlTypeSerializer implements YAMLDeserializer, YAMLSerializer {

  private static final StateEnd_YamlMapperImpl mapper = StateEnd_YamlMapperImpl.INSTANCE;

  private static final BooleanYAMLSerializer booleanYAMLSerializer = new BooleanYAMLSerializer();
  private static final BooleanYAMLDeserializer booleanYAMLDeserializer =
      new BooleanYAMLDeserializer();

  @Override
  public Object deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    YamlNode node = yaml.getNode(key);
    return deserialize(node, ctx);
  }

  @Override
  public Object deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    if (node != null) {
      if (node.type() == NodeType.MAPPING) {
        return mapper.getDeserializer().deserialize(node, ctx);
      } else if (node.type() == NodeType.SCALAR) {
        return booleanYAMLDeserializer.deserialize(node, ctx);
      }
    }
    return null;
  }

  @Override
  public void serialize(
      YamlMapping writer, String propertyName, Object value, YAMLSerializationContext ctx) {
    if (value instanceof Boolean) {
      booleanYAMLSerializer.serialize(writer, propertyName, (Boolean) value, ctx);
    } else if (value instanceof StateEnd) {
      mapper.getSerializer().serialize(writer, propertyName, (StateEnd) value, ctx);
    }
  }

  @Override
  public void serialize(YamlSequence writer, Object value, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Unsupported serialization of " + value.getClass());
  }
}
