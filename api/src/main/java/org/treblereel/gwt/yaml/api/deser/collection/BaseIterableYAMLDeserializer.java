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

package org.treblereel.gwt.yaml.api.deser.collection;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;

/**
 * Base {@link YAMLDeserializer} implementation for {@link java.lang.Iterable}.
 * @param <I> {@link java.lang.Iterable} type
 * @param <T> Type of the elements inside the {@link java.lang.Iterable}
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseIterableYAMLDeserializer<I extends Iterable<T>, T> extends YAMLDeserializer<I> {

    protected final YAMLDeserializer<T> deserializer;

    /**
     * <p>Constructor for BaseIterableYAMLDeserializer.</p>
     * @param deserializer {@link YAMLDeserializer} used to map the objects inside the {@link java.lang.Iterable}.
     */
    public BaseIterableYAMLDeserializer(YAMLDeserializer<T> deserializer) {
        if (null == deserializer) {
            throw new IllegalArgumentException("deserializer can't be null");
        }
        this.deserializer = deserializer;
    }
}
