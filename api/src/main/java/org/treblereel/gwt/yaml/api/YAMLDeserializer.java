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

import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base class for all the deserializer. It handles null values and exceptions. The rest is delegated to implementations.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class YAMLDeserializer<T> {

    public T deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
            YAMLDeserializationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Deserializes a YAML input into an object.
     * @param reader {@link YAMLReader} used to read the JSON input
     * @param ctx Context for the full deserialization process
     * @return the deserialized object
     * @throws YAMLDeserializationException if an error occurs during the deserialization
     */
    public T deserialize(YAMLReader reader, YAMLDeserializationContext ctx) throws YAMLDeserializationException {
        return deserialize(reader, ctx, ctx.defaultParameters());
    }

    /**
     * Deserializes a JSON input into an object.
     * @param reader {@link YAMLReader} used to read the JSON input
     * @param ctx Context for the full deserialization process
     * @param params Parameters for this deserialization
     * @return the deserialized object
     * @throws YAMLDeserializationException if an error occurs during the deserialization
     */
    public T deserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
            YAMLDeserializationException {
        return doDeserialize(reader, ctx, params);
    }

    /**
     * Deserializes a non-null JSON input into an object.
     * @param reader {@link YAMLReader} used to read the JSON input
     * @param ctx Context for the full deserialization process
     * @param params Parameters for this deserialization
     * @return the deserialized object
     */
    protected abstract T doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);
}
