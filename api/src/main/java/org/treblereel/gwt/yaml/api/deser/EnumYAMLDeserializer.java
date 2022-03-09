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

package org.treblereel.gwt.yaml.api.deser;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.lang.Enum}.
 * @param <E> Type of the enum
 * @author Nicolas Morel
 * @version $Id: $
 */
public class EnumYAMLDeserializer<E extends Enum<E>> extends YAMLDeserializer<E> {

    private final Class<E> enumClass;
    private final E[] values;
    /**
     * <p>Constructor for EnumYAMLDeserializer.</p>
     * @param enumClass class of the enumeration
     */
    protected EnumYAMLDeserializer(Class<E> enumClass, E[] values) {
        if (null == enumClass) {
            throw new IllegalArgumentException("enumClass cannot be null");
        }
        this.enumClass = enumClass;
        this.values = values;
    }

    /**
     * <p>newInstance</p>
     * @param enumClass class of the enumeration
     * @return a new instance of {@link EnumYAMLDeserializer}
     */
    public static <E extends Enum<E>> EnumYAMLDeserializer<E> newInstance(Class<E> enumClass, E... values) {
        return new EnumYAMLDeserializer<>(enumClass, values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return doDeserialize(yaml.string(key), ctx, params);
    }

    @Override
    public E doDeserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        try {
            return getEnum(values, value);
        } catch (IllegalArgumentException ex) {
            if (ctx.isReadUnknownEnumValuesAsNull()) {
                return null;
            }
            throw ex;
        }
    }

    public <E extends Enum<E>> E getEnum(E[] values, String name) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].name().equals(name)) {
                return values[i];
            }
        }
        throw new IllegalArgumentException("[" + name + "] is not a valid enum constant for Enum type " + getEnumClass().getName());
    }

    /**
     * <p>Getter for the field <code>enumClass</code>.</p>
     * @return a {@link java.lang.Class} object.
     */
    public Class<E> getEnumClass() {
        return enumClass;
    }
}
