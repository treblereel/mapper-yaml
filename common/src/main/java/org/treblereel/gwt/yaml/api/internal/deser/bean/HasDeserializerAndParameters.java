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

package org.treblereel.gwt.yaml.api.internal.deser.bean;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

/**
 * Lazy initialize a {@link YAMLDeserializer}
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class HasDeserializerAndParameters<V, S extends YAMLDeserializer<V>>
    extends HasDeserializer<V, S> {

  private YAMLDeserializerParameters parameters;

  /**
   * Deserializes the property defined for this instance.
   *
   * @param yaml YamlMapping
   * @param key String
   * @param ctx context of the deserialization process
   * @return a V object.
   */
  public V deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    return getDeserializer().deserialize(yaml, key, ctx, getParameters());
  }

  /**
   * Getter for the field <code>parameters</code>.
   *
   * @return a {@link YAMLDeserializerParameters} object.
   */
  protected YAMLDeserializerParameters getParameters() {
    if (null == parameters) {
      parameters = newParameters();
    }
    return parameters;
  }

  /**
   * newParameters
   *
   * @return a {@link YAMLDeserializerParameters} object.
   */
  protected YAMLDeserializerParameters newParameters() {
    return YAMLContextProvider.get().defaultDeserializerParameters();
  }
}