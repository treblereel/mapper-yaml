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

package org.treblereel.gwt.yaml.api;

import java.io.IOException;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.internal.deser.DefaultYAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractBeanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.bean.AbstractBeanYAMLSerializer;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.impl.Yaml;

public abstract class AbstractObjectMapper<T> {

  private YAMLDeserializer<T> deserializer;

  private YAMLSerializer<T> serializer;

  public T read(String in) throws YAMLDeserializationException, IOException {
    YAMLDeserializationContext context = DefaultYAMLDeserializationContext.builder().build();
    return read(in, context);
  }

  public T read(String in, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException, IOException {
    YamlMapping reader = Yaml.fromString(in);
    return ((AbstractBeanYAMLDeserializer<T>) getDeserializer()).deserializeInline(reader, ctx);
  }

  public YAMLDeserializer<T> getDeserializer() {
    if (null == deserializer) {
      deserializer = newDeserializer();
    }
    return deserializer;
  }

  protected abstract YAMLDeserializer<T> newDeserializer();

  public String write(T value) throws YAMLSerializationException {
    YAMLSerializationContext yamlSerializationContext =
        DefaultYAMLSerializationContext.builder().build();
    return write(value, yamlSerializationContext);
  }

  public String write(T value, YAMLSerializationContext ctx) throws YAMLSerializationException {
    DumpSettings settings =
        DumpSettings.builder()
            .setDefaultFlowStyle(FlowStyle.BLOCK)
            .setIndent(2)
            .setIndicatorIndent(2)
            .setIndentWithIndicator(true)
            .build();
    YamlMapping writer = Yaml.create(settings);
    ((AbstractBeanYAMLSerializer<T>) getSerializer()).serializeInternally(writer, value, ctx);
    return writer.toString();
  }

  @SuppressWarnings("unchecked")
  public YAMLSerializer<T> getSerializer() {
    if (null == serializer) {
      serializer = (YAMLSerializer<T>) newSerializer();
    }
    return serializer;
  }

  protected abstract YAMLSerializer<?> newSerializer();
}
