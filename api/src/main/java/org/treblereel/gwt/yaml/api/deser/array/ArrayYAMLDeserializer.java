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

import java.util.List;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Default {@link YAMLDeserializer} implementation for array.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class ArrayYAMLDeserializer<T> extends AbstractArrayYAMLDeserializer<T[]> {

    private final YAMLDeserializer<T> deserializer;
    private final ArrayCreator<T> arrayCreator;

    /**
     * <p>Constructor for ArrayYAMLDeserializer.</p>
     * @param deserializer {@link YAMLDeserializer} used to deserialize the objects inside the array.
     * @param arrayCreator {@link ArrayYAMLDeserializer.ArrayCreator} used to create a new array
     */
    protected ArrayYAMLDeserializer(YAMLDeserializer<T> deserializer, ArrayCreator<T> arrayCreator) {
        if (null == deserializer) {
            throw new IllegalArgumentException("deserializer cannot be null");
        }
        if (null == arrayCreator) {
            throw new IllegalArgumentException("Cannot deserialize an array without an arrayCreator");
        }
        this.deserializer = deserializer;
        this.arrayCreator = arrayCreator;
    }

    /**
     * <p>newInstance</p>
     * @param deserializer {@link YAMLDeserializer} used to deserialize the objects inside the array.
     * @param arrayCreator {@link ArrayYAMLDeserializer.ArrayCreator} used to create a new array
     * @param <T> Type of the elements inside the {@link java.util.AbstractCollection}
     * @return a new instance of {@link ArrayYAMLDeserializer}
     */
    public static <T> ArrayYAMLDeserializer<T> newInstance(YAMLDeserializer<T> deserializer, ArrayCreator<T> arrayCreator) {
        return new ArrayYAMLDeserializer<>(deserializer, arrayCreator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T[] doDeserializeArray(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        List<T> list = deserializeIntoList(yaml.yamlSequence(key), deserializer, ctx, params);
        if (list == null) {
            return null;
        }
        return list.toArray(arrayCreator.create(list.size()));
    }

    @FunctionalInterface
    public interface ArrayCreator<T> {

        T[] create(int length);
    }
}
