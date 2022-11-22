/*
 * Copyright 2015 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.internal.ser;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.ArrayList;
import java.util.Collection;
import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.internal.ser.bean.AbstractBeanYAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;
import org.treblereel.gwt.yaml.api.stream.impl.DefaultYAMLWriter;

/**
 * Default {@link AbstractYAMLSerializer} implementation for {@link Collection}.
 *
 * @param <T> Type of the elements inside the {@link Collection}
 * @author Nicolas Morel
 * @version $Id: $
 */
public class CollectionYAMLSerializer<C extends Collection<T>, T>
    extends AbstractYAMLSerializer<C> {

  protected final AbstractYAMLSerializer<T> serializer;

  /**
   * Constructor for CollectionYAMLSerializer.
   *
   * @param serializer {@link AbstractYAMLSerializer} used to serialize the objects inside the
   *     {@link Collection}.
   */
  protected CollectionYAMLSerializer(AbstractYAMLSerializer<T> serializer) {
    if (null == serializer) {
      throw new IllegalArgumentException("serializer cannot be null");
    }
    this.serializer = serializer;
  }

  /**
   * newInstance
   *
   * @param serializer {@link AbstractYAMLSerializer} used to serialize the objects inside the
   *     {@link Collection}.
   * @param <C> Type of the {@link Collection}
   * @return a new instance of {@link CollectionYAMLSerializer}
   */
  public static <C extends Collection<?>> CollectionYAMLSerializer<C, ?> newInstance(
      AbstractYAMLSerializer<?> serializer) {
    return new CollectionYAMLSerializer(serializer);
  }

  @Override
  public void serialize(
      YAMLWriter writer, String propertyName, C values, YAMLSerializationContext ctx)
      throws YAMLSerializationException {
    if (!ctx.isWriteEmptyYAMLArrays() && isEmpty(values)) {
      writer.nullValue(propertyName);
      return;
    }

    if (serializer instanceof AbstractBeanYAMLSerializer) {
      Collection<YamlNode> nodes = new ArrayList<>();
      for (T value : (Collection<T>) values) {
        DefaultYAMLWriter childWriter = new DefaultYAMLWriter();
        serializer.setParent(this).serialize(childWriter, value, ctx);
        nodes.add(childWriter.getWriter().build());
      }
      writer.collectionOfYamlNode(propertyName, nodes);

    } else {
      Collection<String> temp = new ArrayList<>();
      for (T value : (Collection<T>) values) {
        temp.add(value.toString());
      }
      writer.collectionOfString(propertyName, temp);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(C value) {
    return null == value || value.isEmpty();
  }
}
