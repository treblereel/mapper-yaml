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

package org.treblereel.gwt.yaml.api.internal.ser;

import java.util.Iterator;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for {@link Iterable}.
 *
 * @param <T> Type of the elements inside the {@link Iterable}
 * @author Nicolas Morel
 * @version $Id: $
 */
public class IterableYAMLSerializer<I extends Iterable<T>, T> extends YAMLSerializer<I> {

  protected final YAMLSerializer<T> serializer;

  /**
   * Constructor for IterableYAMLSerializer.
   *
   * @param serializer {@link YAMLSerializer} used to serialize the objects inside the {@link
   *     Iterable}.
   */
  protected IterableYAMLSerializer(YAMLSerializer<T> serializer) {
    if (null == serializer) {
      throw new IllegalArgumentException("serializer cannot be null");
    }
    this.serializer = serializer;
  }

  /**
   * newInstance
   *
   * @param serializer {@link YAMLSerializer} used to serialize the objects inside the {@link
   *     Iterable}
   * @param <I> Type of the {@link Iterable}
   * @return a new instance of {@link IterableYAMLSerializer}
   */
  public static <I extends Iterable<?>> IterableYAMLSerializer<I, ?> newInstance(
      YAMLSerializer<?> serializer) {
    return new IterableYAMLSerializer(serializer);
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(I value) {
    return null == value || !value.iterator().hasNext();
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YAMLWriter writer, I values, YAMLSerializationContext ctx) {
    Iterator<T> iterator = values.iterator();

    /*        if (!iterator.hasNext()) {
        if (ctx.isWriteEmptyYAMLArrays()) {
            writer.beginArray();
            writer.endArray();
        } else {
            writer.nullValue();
        }
        return;
    }

    writer.beginArray();
    while (iterator.hasNext()) {
        serializer.serialize(writer, iterator.next(), ctx, params);
    }
    writer.endArray();*/
  }
}
