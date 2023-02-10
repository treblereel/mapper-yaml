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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Locale;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.stream.YAMLSequenceWriter;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

public class HolderSerializer
    implements YAMLSerializer<ValueHolder>, YAMLDeserializer<ValueHolder> {

  @Override
  public void serialize(
      YAMLWriter writer, String propertyName, ValueHolder value, YAMLSerializationContext ctx) {
    writer.value(propertyName, value.getValue().toUpperCase(Locale.ROOT));
  }

  @Override
  public void serialize(
      YAMLSequenceWriter writer, ValueHolder value, YAMLSerializationContext ctx) {
    writer.value(value.getValue().toUpperCase(Locale.ROOT));
  }

  @Override
  public ValueHolder deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    return deserialize(yaml.value(key), ctx);
  }

  @Override
  public ValueHolder deserialize(YamlNode value, YAMLDeserializationContext ctx) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    ValueHolder holder = new ValueHolder();
    holder.setValue(value.asScalar().value().toLowerCase(Locale.ROOT));
    return holder;
  }
}
