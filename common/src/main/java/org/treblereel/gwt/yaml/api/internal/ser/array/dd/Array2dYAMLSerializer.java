/*
 * Copyright 2014 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.internal.ser.array.dd;

import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

/**
 * Default {@link AbstractYAMLSerializer} implementation for 2D array.
 *
 * @param <T> Type of the elements inside the array
 * @author Nicolas Morel
 * @version $Id: $
 */
public class Array2dYAMLSerializer<T> extends AbstractYAMLSerializer<T[][]> {

  protected final String propertyName;
  private final AbstractYAMLSerializer<T> serializer;

  /**
   * Constructor for Array2dYAMLSerializer.
   *
   * @param serializer {@link AbstractYAMLSerializer} used to serialize the objects inside the
   *     array.
   */
  protected Array2dYAMLSerializer(AbstractYAMLSerializer<T> serializer, String propertyName) {
    if (null == serializer) {
      throw new IllegalArgumentException("serializer cannot be null");
    }
    if (null == propertyName) {
      throw new IllegalArgumentException("propertyName cannot be null");
    }
    this.serializer = serializer;
    this.propertyName = propertyName;
  }

  /**
   * newInstance
   *
   * @param serializer {@link AbstractYAMLSerializer} used to serialize the objects inside the
   *     array.
   * @param <T> Type of the elements inside the array
   * @return a new instance of {@link Array2dYAMLSerializer}
   */
  public static <T> Array2dYAMLSerializer<T> getInstance(
      AbstractYAMLSerializer<T> serializer, String propertyName) {
    return new Array2dYAMLSerializer<>(serializer, propertyName);
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(T[][] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YamlMapping writer, T[][] values, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void serialize(YamlSequence writer, T[][] value, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
