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

package org.treblereel.gwt.yaml.api.deser.array;

import java.util.ArrayList;
import java.util.List;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base implementation of {@link YAMLDeserializer} for array.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractArrayYAMLDeserializer<T> extends YAMLDeserializer<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return doDeserializeArray(reader, ctx, params);
    }

    /**
     * <p>doDeserializeArray</p>
     * @param reader a {@link YAMLReader} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @return a T object.
     */
    protected abstract T doDeserializeArray(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);

    /**
     * <p>doDeserializeSingleArray</p>
     * @param reader a {@link YAMLReader} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @return a T object.
     */
    protected abstract T doDeserializeSingleArray(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);

    /**
     * Deserializes the array into a {@link java.util.List}. We need the length of the array before creating it.
     * @param reader reader
     * @param ctx context of the deserialization process
     * @param deserializer deserializer for element inside the array
     * @param params Parameters for the deserializer
     * @param <C> type of the element inside the array
     * @return a list containing all the elements of the array
     */
    protected <C> List<C> deserializeIntoList(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializer<C> deserializer,
                                              YAMLDeserializerParameters params) {
        List<C> list = new ArrayList<>();
        throw new UnsupportedOperationException();
    }
}
