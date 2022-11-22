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

package org.treblereel.gwt.yaml.api.internal.deser.map;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.util.AbstractMap}. The
 * deserialization process returns a {@link java.util.LinkedHashMap}.
 *
 * <p>Cannot be overriden. Use {@link BaseMapYAMLDeserializer}.
 *
 * @param <K> Type of the keys inside the {@link java.util.AbstractMap}
 * @param <V> Type of the values inside the {@link java.util.AbstractMap}
 * @author Nicolas Morel
 * @version $Id: $
 */
public final class AbstractMapYAMLDeserializer<K, V>
    extends BaseMapYAMLDeserializer<AbstractMap<K, V>, K, V> {

  /**
   * @param keyDeserializer {@link YAMLDeserializer} used to deserialize the keys.
   * @param valueDeserializer {@link YAMLDeserializer} used to deserialize the values.
   */
  private AbstractMapYAMLDeserializer(
      YAMLDeserializer<K> keyDeserializer, YAMLDeserializer<V> valueDeserializer) {
    super(keyDeserializer, valueDeserializer);
  }

  /**
   * newInstance
   *
   * @param keyDeserializer {@link YAMLDeserializer} used to deserialize the keys.
   * @param valueDeserializer {@link YAMLDeserializer} used to deserialize the values.
   * @param <K> Type of the keys inside the {@link java.util.AbstractMap}
   * @param <V> Type of the values inside the {@link java.util.AbstractMap}
   * @return a new instance of {@link AbstractMapYAMLDeserializer}
   */
  public static <K, V> AbstractMapYAMLDeserializer<K, V> newInstance(
      YAMLDeserializer<K> keyDeserializer, YAMLDeserializer<V> valueDeserializer) {
    return new AbstractMapYAMLDeserializer<>(keyDeserializer, valueDeserializer);
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractMap<K, V> newMap() {
    return new LinkedHashMap<>();
  }
}
