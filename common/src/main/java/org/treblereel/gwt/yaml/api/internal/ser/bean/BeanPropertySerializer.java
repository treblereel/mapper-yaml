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

import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Serializes a bean's property
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BeanPropertySerializer<T, V> extends HasSerializer<V, YAMLSerializer<V>> {

  protected String propertyName;

  private YAMLSerializerParameters parameters = newParameters();
  private YAMLSerializer parent;

  /**
   * Constructor for BeanPropertySerializer.
   *
   * @param propertyName a {@link String} object.
   */
  protected BeanPropertySerializer(String propertyName) {
    this.propertyName = propertyName;
  }

  /**
   * newParameters
   *
   * @return a {@link YAMLSerializerParameters} object.
   */
  protected YAMLSerializerParameters newParameters() {
    return YAMLContextProvider.get().defaultSerializerParameters();
  }

  /**
   * Getter for the field <code>propertyName</code>.
   *
   * @return a {@link String} object.
   */
  public String getPropertyName() {
    return propertyName;
  }

  /**
   * Serializes the property defined for this instance.
   *
   * @param writer writer
   * @param bean bean containing the property to serialize
   * @param ctx context of the serialization process
   */
  public void serialize(YAMLWriter writer, T bean, YAMLSerializationContext ctx) {
    getSerializer((V) bean.getClass())
        .setPropertyName(propertyName)
        .setParent(parent)
        .serialize(writer, getValue(bean, ctx), ctx, getParameters());
  }

  public boolean isAbstractBeanYAMLSerializer(T bean) {
    return getSerializer((V) bean.getClass()) instanceof AbstractBeanYAMLSerializer;
  }

  /**
   * getValue
   *
   * @param bean bean containing the property to serialize
   * @param ctx context of the serialization process
   * @return the property's value
   */
  public abstract V getValue(T bean, YAMLSerializationContext ctx);

  /**
   * Getter for the field <code>parameters</code>.
   *
   * @return a {@link YAMLSerializerParameters} object.
   */
  protected YAMLSerializerParameters getParameters() {
    return parameters;
  }

  BeanPropertySerializer<T, V> setParent(YAMLSerializer parent) {
    this.parent = parent;
    return this;
  }
}
