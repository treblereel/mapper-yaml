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

package org.treblereel.gwt.yaml.api.deser.bean;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;

/**
 * Lazy initialize a {@link YAMLDeserializer}
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class HasDeserializer<V, S extends YAMLDeserializer<V>> {

    private S deserializer;

    /**
     * <p>Getter for the field <code>deserializer</code>.</p>
     *
     * @return a S object.
     */
    public S getDeserializer() {
        if (null == deserializer) {
            deserializer = (S) newDeserializer();
        }
        return deserializer;
    }

    /**
     * <p>newDeserializer</p>
     *
     * @return a {@link YAMLDeserializer} object.
     */
    protected abstract YAMLDeserializer<?> newDeserializer();
}
