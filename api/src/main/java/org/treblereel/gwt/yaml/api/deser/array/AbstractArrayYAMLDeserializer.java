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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

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
    public T doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return doDeserializeArray(yaml, key, ctx, params);
    }

    /**
     * <p>doDeserializeArray</p>
     * @param yaml a {@link YamlMapping} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @return a T object.
     */
    protected abstract T doDeserializeArray(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);

    /**
     * Deserializes the array into a {@link java.util.List}. We need the length of the array before creating it.
     * @param sequence YamlSequence
     * @param ctx context of the deserialization process
     * @param deserializer deserializer for element inside the array
     * @param params Parameters for the deserializer
     * @param <C> type of the element inside the array
     * @return a list containing all the elements of the array
     */
    protected <C> List<C> deserializeIntoList(YamlSequence sequence,  YAMLDeserializer<C> deserializer,
                                              YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        List<C> list = new ArrayList<>();
        for (int i = 0; i < sequence.size(); i++) {
            list.add(deserializer.doDeserialize(sequence.string(i), ctx, params));
        }
        return list;
    }

    @Override
    final public T doDeserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        throw new Error("Unsupported operation");
    }
}
