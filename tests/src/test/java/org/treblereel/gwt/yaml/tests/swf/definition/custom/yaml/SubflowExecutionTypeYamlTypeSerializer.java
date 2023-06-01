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
import org.treblereel.gwt.yaml.api.internal.deser.StringYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.StringYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.NodeType;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlSequence;
import org.treblereel.gwt.yaml.tests.swf.definition.SubflowExecutionType;

public class SubflowExecutionTypeYamlTypeSerializer
    implements YAMLDeserializer<SubflowExecutionType>, YAMLSerializer<SubflowExecutionType> {

  private static final StringYAMLSerializer stringYAMLSerializer = new StringYAMLSerializer();
  private static final StringYAMLDeserializer stringYAMLDeserializer = new StringYAMLDeserializer();

  @Override
  public SubflowExecutionType deserialize(
      YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    YamlNode value = yaml.getNode(key);
    if (value == null) {
      return null;
    }
    return deserialize(value, ctx);
  }

  @Override
  public SubflowExecutionType deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    if (node == null) {
      return null;
    }
    if (node.type() == NodeType.SCALAR) {
      String v = stringYAMLDeserializer.deserialize(node, ctx);
      return v.equals("terminate") ? SubflowExecutionType.TERMINATE : SubflowExecutionType.CONTINUE;
    }
    return null;
  }

  @Override
  public void serialize(
      YamlMapping writer,
      String propertyName,
      SubflowExecutionType obj,
      YAMLSerializationContext ctx) {
    stringYAMLSerializer.serialize(
        writer,
        propertyName,
        (obj == SubflowExecutionType.TERMINATE ? "terminate" : "continue"),
        ctx);
  }

  @Override
  public void serialize(
      YamlSequence writer, SubflowExecutionType obj, YAMLSerializationContext ctx) {
    stringYAMLSerializer.serialize(
        writer, (obj == SubflowExecutionType.TERMINATE ? "terminate" : "continue"), ctx);
  }
}
