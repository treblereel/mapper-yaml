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

package org.treblereel.gwt.yaml.api.ser.map;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for {@link Map}.
 * @param <M> Type of the {@link Map}
 * @param <K> Type of the keys inside the {@link Map}
 * @param <V> Type of the values inside the {@link Map}
 * @author Nicolas Morel
 * @version $Id: $
 */
public class MapYAMLSerializer<M extends Map<K, V>, K, V> extends YAMLSerializer<M> {

    protected final YAMLSerializer<K> keySerializer;
    protected final YAMLSerializer<V> valueSerializer;
    protected final String propertyName;

    /**
     * <p>Constructor for MapYAMLSerializer.</p>
     * @param keySerializer {@link YAMLSerializer} used to serialize the keys.
     * @param valueSerializer {@link YAMLSerializer} used to serialize the values.
     */
    protected MapYAMLSerializer(YAMLSerializer<K> keySerializer, YAMLSerializer<V> valueSerializer, String propertyName) {
        if (null == keySerializer) {
            throw new IllegalArgumentException("keySerializer cannot be null");
        }
        if (null == valueSerializer) {
            throw new IllegalArgumentException("valueSerializer cannot be null");
        }
        if (null == propertyName) {
            throw new IllegalArgumentException("valueSerializer cannot be null");
        }
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
        this.propertyName = propertyName;
    }

    /**
     * <p>newInstance</p>
     * @param keySerializer {@link YAMLSerializer} used to serialize the keys.
     * @param valueSerializer {@link YAMLSerializer} used to serialize the values.
     * @param <M> Type of the {@link Map}
     * @return a new instance of {@link MapYAMLSerializer}
     */
    public static <M extends Map<?, ?>> MapYAMLSerializer<M, ?, ?> newInstance(YAMLSerializer<?> keySerializer, YAMLSerializer<?>
            valueSerializer, String propertyName) {
        return new MapYAMLSerializer(keySerializer, valueSerializer, propertyName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(YAMLWriter writer, M values, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        serializeValues(writer, values, ctx, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEmpty(M value) {
        return null == value || value.isEmpty();
    }

    /**
     * <p>serializeValues</p>
     * @param writer a {@link YAMLWriter} object.
     * @param values a M object.
     * @param ctx a {@link YAMLSerializationContext} object.
     * @param params a {@link YAMLSerializerParameters} object.
     */
    public void serializeValues(YAMLWriter writer, M values, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        throw new UnsupportedOperationException();
        /*        if (!values.isEmpty()) {
            Map<K, V> map = values;
            if (ctx.isOrderMapEntriesByKeys() && !(values instanceof SortedMap<?, ?>)) {
                map = new TreeMap<>(map);
            }
            writer.beginObject(propertyName);
            for (Map.Entry<K, V> entry : map.entrySet()) {
                writer.beginObject("entry");
                String keyName = getNodeName(entry.getKey().getClass(), ctx);
                String valueName = getNodeName(entry.getValue().getClass(), ctx);
                writer.unescapeName(keyName);
                keySerializer.setPropertyName(keyName)
                        .serialize(writer, entry.getKey(), ctx, params, true);

                writer.unescapeName(valueName);
                valueSerializer.setPropertyName(valueName)
                        .serialize(writer, entry.getValue(), ctx, params, true);

                writer.endObject();
            }
            writer.endObject();
        }*/
    }

    private String getNodeName(Class clazz, YAMLSerializationContext ctx) {
        if (ctx.isMapKeyAndValueCanonical()) {
            return clazz.getCanonicalName();
        }
        return clazz.getSimpleName();
    }
}
