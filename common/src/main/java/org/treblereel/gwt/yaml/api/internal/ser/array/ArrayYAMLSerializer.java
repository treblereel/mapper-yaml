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

import com.amihaiemil.eoyaml.YamlNode;
import java.util.ArrayList;
import java.util.Collection;
import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.bean.AbstractBeanYAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;
import org.treblereel.gwt.yaml.api.stream.impl.DefaultYAMLWriter;

/**
 * Default {@link AbstractYAMLSerializer} implementation for array.
 *
 * @param <T> Type of the elements inside the array
 * @author Nicolas Morel
 * @version $Id: $
 */
public class ArrayYAMLSerializer<T> extends AbstractYAMLSerializer<T[]> {

  private final AbstractYAMLSerializer<T> serializer;
  protected final String propertyName;

  /**
   * Constructor for ArrayYAMLSerializer.
   *
   * @param serializer {@link AbstractYAMLSerializer} used to serialize the objects inside the
   *     array.
   */
  public ArrayYAMLSerializer(AbstractYAMLSerializer<T> serializer, String propertyName) {
    if (null == serializer) {
      throw new IllegalArgumentException("serializer cannot be null");
    }
    if (null == propertyName) {
      throw new IllegalArgumentException("propertyName cannot be null");
    }
    this.serializer = serializer;
    this.propertyName = propertyName;
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(T[] value) {
    return null == value || value.length == 0;
  }

  @Override
  public void serialize(YAMLWriter writer, T[] values, YAMLSerializationContext ctx)
      throws YAMLSerializationException {
    throw new RuntimeException("Not implemented");
  }

  /** {@inheritDoc} */
  @Override
  public void serialize(
      YAMLWriter writer, String propertyName, T[] values, YAMLSerializationContext ctx) {
    if (!ctx.isWriteEmptyYAMLArrays() && isEmpty(values)) {
      writer.nullValue(propertyName);
      return;
    }

    if (serializer instanceof AbstractBeanYAMLSerializer) {
      Collection<YamlNode> nodes = new ArrayList<>();
      for (T value : (T[]) values) {
        DefaultYAMLWriter childWriter = new DefaultYAMLWriter();
        serializer.setParent(this).serialize(childWriter, value, ctx);
        nodes.add(childWriter.getWriter().build());
      }
      writer.collectionOfYamlNode(propertyName, nodes);

    } else {
      Collection<String> temp = new ArrayList<>();
      for (T value : (T[]) values) {
        temp.add(value.toString());
      }
      writer.collectionOfString(propertyName, temp);
    }
  }
}
