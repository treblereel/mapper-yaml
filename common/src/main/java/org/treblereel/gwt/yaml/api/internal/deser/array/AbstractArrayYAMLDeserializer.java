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

package org.treblereel.gwt.yaml.api.internal.deser.array;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractBeanYAMLDeserializer;

/**
 * Base implementation of {@link YAMLDeserializer} for array.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractArrayYAMLDeserializer<T> implements YAMLDeserializer<T> {

  /** {@inheritDoc} */
  public T deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    return doDeserializeArray(yaml, key, ctx);
  }

  /**
   * doDeserializeArray
   *
   * @param yaml a {@link YamlMapping} object.
   * @param ctx a {@link YAMLDeserializationContext} object.
   * @return a T object.
   */
  protected abstract T doDeserializeArray(
      YamlMapping yaml, String key, YAMLDeserializationContext ctx);

  /**
   * Deserializes the array into a {@link java.util.List}. We need the length of the array before
   * creating it.
   *
   * @param sequence YamlSequence
   * @param ctx context of the deserialization process
   * @param deserializer deserializer for element inside the array
   * @param <C> type of the element inside the array
   * @return a list containing all the elements of the array
   */
  protected <C> List<C> deserializeIntoList(
      YamlSequence sequence, YAMLDeserializer<C> deserializer, YAMLDeserializationContext ctx) {
    List<C> list = new ArrayList<>();

    if (deserializer instanceof AbstractBeanYAMLDeserializer) {
      for (int i = 0; i < sequence.size(); i++) {
        list.add(
            ((AbstractBeanYAMLDeserializer<C>) deserializer)
                .deserialize(sequence.yamlMapping(i), ctx));
      }
    } else {
      Iterator<com.amihaiemil.eoyaml.YamlNode> iterator = sequence.iterator();
      while (iterator.hasNext()) {
        list.add(deserializer.deserialize(iterator.next(), ctx));
      }
    }
    return list;
  }

  @Override
  public T deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
