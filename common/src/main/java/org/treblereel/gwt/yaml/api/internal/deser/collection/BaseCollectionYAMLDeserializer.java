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

package org.treblereel.gwt.yaml.api.internal.deser.collection;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractBeanYAMLDeserializer;

/**
 * Base {@link YAMLDeserializer} implementation for {@link java.util.Collection}.
 *
 * @param <C> {@link java.util.Collection} type
 * @param <T> Type of the elements inside the {@link java.util.Collection}
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseCollectionYAMLDeserializer<C extends Collection<T>, T>
    extends BaseIterableYAMLDeserializer<C, T> {

  C collection = newCollection();

  /**
   * Constructor for BaseCollectionYAMLDeserializer.
   *
   * @param deserializer {@link YAMLDeserializer} used to map the objects inside the {@link
   *     java.util.Collection}.
   */
  public BaseCollectionYAMLDeserializer(YAMLDeserializer<T> deserializer) {
    super(deserializer);
  }

  /** {@inheritDoc} */
  @Override
  public C deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    List<T> list = new ArrayList<>();

    YamlSequence sequence = yaml.yamlSequence(key);

    if (deserializer instanceof AbstractBeanYAMLDeserializer) {
      for (int i = 0; i < sequence.size(); i++) {
        list.add(
            ((AbstractBeanYAMLDeserializer<T>) deserializer)
                .deserialize(sequence.yamlMapping(i), ctx));
      }
    } else {
      for (int i = 0; i < sequence.size(); i++) {
        list.add(deserializer.deserialize(sequence.string(i), ctx));
      }
    }
    return (C) list;
  }

  /**
   * Instantiates a new collection for deserialization process.
   *
   * @return the new collection
   */
  protected abstract C newCollection();

  /**
   * isNullValueAllowed
   *
   * @return true if the collection accepts null value
   */
  protected boolean isNullValueAllowed() {
    return true;
  }
}
