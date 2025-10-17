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

package org.treblereel.gwt.yaml.tests.annotations.customtypeser;

import java.util.Locale;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeDeserializerFor;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeSerializerFor;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

@YamlTypeDeserializerFor(OneMoreValueHolder.class)
@YamlTypeSerializerFor(OneMoreValueHolder.class)
public class OneMoreValueHolderSerializer
    implements YAMLSerializer<OneMoreValueHolder>, YAMLDeserializer<OneMoreValueHolder> {

  @Override
  public void serialize(
      YamlMapping writer,
      String propertyName,
      OneMoreValueHolder value,
      YAMLSerializationContext ctx) {
    writer.addScalarNode(propertyName, value.getValue().toUpperCase(Locale.ROOT));
  }

  @Override
  public void serialize(
      YamlSequence writer, OneMoreValueHolder value, YAMLSerializationContext ctx) {
    writer.addScalarNode(value.getValue().toUpperCase(Locale.ROOT));
  }

  @Override
  public OneMoreValueHolder deserialize(
      YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    return deserialize(yaml.getNode(key), ctx);
  }

  @Override
  public OneMoreValueHolder deserialize(YamlNode value, YAMLDeserializationContext ctx) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    OneMoreValueHolder holder = new OneMoreValueHolder();
    holder.setValue(value.<String>asScalar().value().toLowerCase(Locale.ROOT));
    return holder;
  }
}
