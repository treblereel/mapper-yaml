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

package org.treblereel.gwt.yaml.api.internal.ser.array;

import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

/**
 * Default {@link AbstractYAMLSerializer} implementation for array.
 *
 * @param <T> Type of the elements inside the array
 * @author Nicolas Morel
 * @version $Id: $
 */
public class ArrayYAMLSerializer<T> extends AbstractYAMLSerializer<T[]> {

  private final YAMLSerializer<T> serializer;

  /**
   * Constructor for ArrayYAMLSerializer.
   *
   * @param serializer {@link YAMLSerializer} used to serialize the objects inside the array.
   */
  public ArrayYAMLSerializer(YAMLSerializer<T> serializer) {
    if (null == serializer) {
      throw new IllegalArgumentException("serializer cannot be null");
    }
    this.serializer = serializer;
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(T[] value) {
    return null == value || value.length == 0;
  }

  @Override
  public void serialize(YamlMapping writer, T[] values, YAMLSerializationContext ctx)
      throws YAMLSerializationException {
    throw new RuntimeException("2D arrays aren't implemented");
  }

  /** {@inheritDoc} */
  @Override
  public void serialize(
      YamlMapping writer, String propertyName, T[] values, YAMLSerializationContext ctx) {
    if (isEmpty(values)) {
      if (ctx.isWriteEmptyYAMLArrays()) {
        writer.addScalarNode(propertyName, new boolean[] {});
      }
      return;
    }
    YamlSequence yamlSequence = writer.addSequenceNode(propertyName);
    serialize(yamlSequence, values, ctx);
  }

  @Override
  public void serialize(YamlSequence writer, T[] values, YAMLSerializationContext ctx) {
    for (T value : values) {
      serializer.serialize(writer, value, ctx);
    }
  }
}
