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

import java.util.SortedMap;
import java.util.TreeMap;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.util.SortedMap}. The
 * deserialization process returns a {@link java.util.TreeMap}.
 *
 * <p>Cannot be overriden. Use {@link BaseMapYAMLDeserializer}.
 *
 * @param <V> Type of the values inside the {@link java.util.SortedMap}
 * @author Nicolas Morel
 * @version $Id: $
 */
public final class SortedMapYAMLDeserializer<V>
    extends BaseMapYAMLDeserializer<SortedMap<String, V>, V> {

  /** @param valueDeserializer {@link YAMLDeserializer} used to deserialize the values. */
  private SortedMapYAMLDeserializer(YAMLDeserializer<V> valueDeserializer) {
    super(valueDeserializer);
  }

  /**
   * newInstance
   *
   * @param valueDeserializer {@link YAMLDeserializer} used to deserialize the values.
   * @param <V> Type of the values inside the {@link java.util.SortedMap}
   * @return a new instance of {@link SortedMapYAMLDeserializer}
   */
  public static <K, V> SortedMapYAMLDeserializer<V> newInstance(
      YAMLDeserializer<V> valueDeserializer) {
    return new SortedMapYAMLDeserializer<>(valueDeserializer);
  }

  /** {@inheritDoc} */
  @Override
  protected SortedMap<String, V> newMap() {
    return new TreeMap<>();
  }
}
