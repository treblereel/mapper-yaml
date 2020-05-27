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

package org.treblereel.gwt.yaml.api.deser.array.dd;

import java.util.ArrayList;
import java.util.Collection;
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
public abstract class AbstractArray2dYAMLDeserializer<T> extends YAMLDeserializer<T> {

    /**
     * Deserializes the array into a {@link java.util.List}. We need the length of the array before creating it.
     * @param reader reader
     * @param ctx context of the deserialization process
     * @param deserializer deserializer for element inside the array
     * @param params Parameters for the deserializer
     * @param <C> type of the element inside the array
     * @return a list containing all the elements of the array
     */
    protected <C> List<List<C>> deserializeIntoList(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializer<C> deserializer,
                                                    YAMLDeserializerParameters params) {
        return doDeserializeIntoList(reader, ctx, deserializer, params);
    }

    /**
     * <p>doDeserializeIntoList</p>
     * @param reader a {@link YAMLReader} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param deserializer a {@link YAMLDeserializer} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @param <C> a C object.
     * @return a {@link java.util.List} object.
     */
    protected <C> List<List<C>> doDeserializeIntoList(YAMLReader reader, YAMLDeserializationContext ctx,
                                                      YAMLDeserializer<C> deserializer, YAMLDeserializerParameters params) {
        List<List<C>> list = new ArrayList<>();
        // we keep the size of the first inner list to initialize the next lists with the correct size
        //return list;
        throw new UnsupportedOperationException();
    }

    protected <C> List<C> doDeserializeInnerIntoList(YAMLReader reader, YAMLDeserializationContext ctx,
                                                     YAMLDeserializer<C> deserializer, YAMLDeserializerParameters params) {
/*        List<C> innerList = new ArrayList<>();
        ctx.iterator().iterateOverCollection(reader, (Collection<T>) innerList, (reader1, ctx1, instance) -> {
            C val = deserializer.deserialize(reader1, ctx1, params);
            innerList.add(val);
            return null;
        }, ctx, params);
        return innerList;*/
        throw new UnsupportedOperationException();
    }
}