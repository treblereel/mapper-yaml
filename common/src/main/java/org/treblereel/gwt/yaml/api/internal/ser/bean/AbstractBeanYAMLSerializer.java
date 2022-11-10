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

package org.treblereel.gwt.yaml.api.internal.ser.bean;

import java.util.Map;
import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;
import org.treblereel.gwt.yaml.api.stream.impl.DefaultYAMLWriter;

/**
 * Base implementation of {@link YAMLSerializer} for beans.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractBeanYAMLSerializer<T> extends YAMLSerializer<T>
    implements InternalSerializer<T> {

  protected final BeanPropertySerializer[] serializers;

  /** Constructor for AbstractBeanYAMLSerializer. */
  protected AbstractBeanYAMLSerializer() {
    this.serializers = initSerializers();
  }

  /**
   * Initialize the {@link Map} containing the property serializers. Returns an empty map if there
   * are no properties to serialize.
   *
   * @return an array of {@link BeanPropertySerializer} objects.
   */
  protected BeanPropertySerializer[] initSerializers() {
    return new BeanPropertySerializer[0];
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YAMLWriter writer, T value, YAMLSerializationContext ctx) {
    getSerializer(value, ctx).serializeInternally(writer, value, ctx);
  }

  private InternalSerializer<T> getSerializer(T value, YAMLSerializationContext ctx) {
    if (value.getClass() == getSerializedType()) {
      return this;
    }

    return this;
  }

  /**
   * getSerializedType
   *
   * @return a {@link Class} object.
   */
  public abstract Class getSerializedType();

  protected String getRootElement() {
    return null;
  }

  /** {@inheritDoc} */
  public void serializeInternally(YAMLWriter writer, T value, YAMLSerializationContext ctx) {
    serializeObject(writer, value, ctx);
  }

  /**
   * Serializes all the properties of the bean in a json object.
   *
   * @param writer writer
   * @param value bean to serialize
   * @param ctx context of the serialization process
   */
  private void serializeObject(YAMLWriter writer, T value, YAMLSerializationContext ctx) {
    serializeObject(writer, value, ctx, getSerializeObjectName());
  }

  /**
   * Serializes all the properties of the bean in a json object.
   *
   * @param writer writer
   * @param value bean to serialize
   * @param ctx context of the serialization process
   * @param typeName in case of type info as property, the name of the property
   */
  protected void serializeObject(
      YAMLWriter writer, T value, YAMLSerializationContext ctx, String typeName) {
    if (value == null && !ctx.isSerializeNulls()) {
      return;
    }

    serializeProperties(writer, value, ctx);
  }

  private String getSerializeObjectName() {
    if (propertyName != null) {
      return propertyName;
    } else {
      return getSerializedType().getSimpleName();
    }
  }

  private void serializeProperties(YAMLWriter writer, T value, YAMLSerializationContext ctx) {

    for (BeanPropertySerializer<T, ?> propertySerializer : serializers) {
      if (propertySerializer.getValue(value, ctx) == null && !ctx.isSerializeNulls()) {
        continue;
      }
      if (propertySerializer.isAbstractBeanYAMLSerializer(value)) {
        DefaultYAMLWriter childWriter = new DefaultYAMLWriter();
        propertySerializer.setParent(this).serialize(childWriter, value, ctx);
        writer.value(propertySerializer.getPropertyName(), childWriter.getWriter().build());
      } else {
        propertySerializer.setParent(this).serialize(writer, value, ctx);
      }
    }
  }
}
