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

package org.treblereel.gwt.yaml.api.ser.array.dd;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for 2D array.
 * @param <T> Type of the elements inside the array
 * @author Nicolas Morel
 * @version $Id: $
 */
public class Array2dYAMLSerializer<T> extends YAMLSerializer<T[][]> {

    protected final String propertyName;
    private final YAMLSerializer<T> serializer;

    /**
     * <p>Constructor for Array2dYAMLSerializer.</p>
     * @param serializer {@link YAMLSerializer} used to serialize the objects inside the array.
     */
    protected Array2dYAMLSerializer(YAMLSerializer<T> serializer, String propertyName) {
        if (null == serializer) {
            throw new IllegalArgumentException("serializer cannot be null");
        }
        if (null == propertyName) {
            throw new IllegalArgumentException("propertyName cannot be null");
        }
        this.serializer = serializer;
        this.propertyName = propertyName;
    }

    /**
     * <p>newInstance</p>
     * @param serializer {@link YAMLSerializer} used to serialize the objects inside the array.
     * @param <T> Type of the elements inside the array
     * @return a new instance of {@link Array2dYAMLSerializer}
     */
    public static <T> Array2dYAMLSerializer<T> getInstance(YAMLSerializer<T> serializer, String propertyName) {
        return new Array2dYAMLSerializer<>(serializer, propertyName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEmpty(T[][] value) {
        return null == value || value.length == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(YAMLWriter writer, T[][] values, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        if (!ctx.isWriteEmptyYAMLArrays() && values.length == 0) {
            writer.nullValue();
            return;
        }

        writer.beginObject(propertyName);
        for (T[] array : values) {
            writer.beginObject(propertyName);
            for (T value : array) {
                serializer.serialize(writer, value, ctx, params);
            }
            writer.endObject();
        }
        writer.endObject();
    }
}
