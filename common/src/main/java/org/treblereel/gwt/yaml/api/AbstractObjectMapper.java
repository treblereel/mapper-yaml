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

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import java.io.IOException;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractBeanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Base implementation of {@link ObjectMapper}. It delegates the serialization/deserialization to a
 * serializer/deserializer.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractObjectMapper<T> implements ObjectMapper<T> {

  private final String rootName;

  private YAMLDeserializer<T> deserializer;

  private YAMLSerializer<T> serializer;

  /**
   * Constructor for AbstractObjectMapper.
   *
   * @param rootName a {@link java.lang.String} object.
   */
  protected AbstractObjectMapper(String rootName) {
    this.rootName = rootName;
  }

  /** {@inheritDoc} */
  @Override
  public T read(String in) throws YAMLDeserializationException, IOException {
    YAMLDeserializationContext context = DefaultYAMLDeserializationContext.builder().build();
    return read(in, context);
  }

  /** {@inheritDoc} */
  public T read(String in, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException, IOException {
    YamlMapping reader = Yaml.createYamlInput(in).readYamlMapping();
    return ((AbstractBeanYAMLDeserializer<T>) getDeserializer())
        .deserializeInline(reader, ctx, null);
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>deserializer</code>.
   */
  @Override
  public YAMLDeserializer<T> getDeserializer() {
    if (null == deserializer) {
      deserializer = newDeserializer();
    }
    return deserializer;
  }

  /**
   * Instantiates a new deserializer
   *
   * @return a new deserializer
   */
  protected abstract YAMLDeserializer<T> newDeserializer();

  /** {@inheritDoc} */
  @Override
  public String write(T value) throws YAMLSerializationException {
    YAMLSerializationContext yamlSerializationContext =
        DefaultYAMLSerializationContext.builder().build();
    return write(value, yamlSerializationContext);
  }

  /** {@inheritDoc} */
  public String write(T value, YAMLSerializationContext ctx) throws YAMLSerializationException {
    YAMLWriter writer = ctx.newYAMLWriter();
    try {
      getSerializer().serialize(writer, value, ctx);
      return writer.getOutput();
    } catch (YAMLSerializationException e) {
      // already logged, we just throw it
      throw new Error(e);
    } catch (RuntimeException e) {
      throw ctx.traceError(value, e, writer);
    } catch (Exception e) {
      throw new Error(e);
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>serializer</code>.
   */
  @Override
  public YAMLSerializer<T> getSerializer() {
    if (null == serializer) {
      serializer = (YAMLSerializer<T>) newSerializer();
    }
    return serializer;
  }

  /**
   * Instantiates a new serializer
   *
   * @return a new serializer
   */
  protected abstract YAMLSerializer<?> newSerializer();
}
