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

package org.treblereel.gwt.yaml.api.deser.bean;

import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Lazy initialize a {@link YAMLDeserializer}
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class HasDeserializerAndParameters<V, S extends YAMLDeserializer<V>> extends HasDeserializer<V, S> {

    private YAMLDeserializerParameters parameters;

    /**
     * Deserializes the property defined for this instance.
     * @param reader reader
     * @param ctx context of the deserialization process
     * @return a V object.
     */
    public V deserialize(YAMLReader reader, YAMLDeserializationContext ctx) {
        return getDeserializer().deserialize(reader, ctx, getParameters());
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     * @return a {@link YAMLDeserializerParameters} object.
     */
    protected YAMLDeserializerParameters getParameters() {
        if (null == parameters) {
            parameters = newParameters();
        }
        return parameters;
    }

    /**
     * <p>newParameters</p>
     * @return a {@link YAMLDeserializerParameters} object.
     */
    protected YAMLDeserializerParameters newParameters() {
        return YAMLContextProvider.get().defaultDeserializerParameters();
    }

    public V deserialize(String value, YAMLDeserializationContext ctx) {
        return getDeserializer().deserialize(value, ctx, getParameters());
    }
}
