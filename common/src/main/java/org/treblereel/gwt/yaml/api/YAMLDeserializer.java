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

package org.treblereel.gwt.yaml.api;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;

/**
 * Base class for all the deserializer. It handles null values and exceptions. The rest is delegated
 * to implementations.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class YAMLDeserializer<T> {

  /**
   * Deserializes a YAML input into an object.
   *
   * @param yaml {@link YamlMapping} used to read the YAML input
   * @param key {@link String} the key
   * @param ctx Context for the full deserialization process
   * @return the deserialized object
   * @throws YAMLDeserializationException if an error occurs during the deserialization
   */
  public T deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    return doDeserialize(yaml, key, ctx);
  }

  /**
   * Deserializes a non-null JSON input into an object.
   *
   * @param yaml {@link YamlMapping} used to read the YAML input
   * @param key {@link String} the key
   * @param ctx Context for the full deserialization process
   * @return the deserialized object
   */
  public T doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    throw new Error("Unsupported operation");
  }

  public T doDeserialize(String value, YAMLDeserializationContext ctx) {
    throw new Error("Unsupported operation");
  }
}
