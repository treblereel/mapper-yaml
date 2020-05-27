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

import java.util.Collection;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base {@link YAMLDeserializer} implementation for {@link java.util.Collection}.
 * @param <C> {@link java.util.Collection} type
 * @param <T> Type of the elements inside the {@link java.util.Collection}
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseCollectionYAMLDeserializer<C extends Collection<T>, T> extends BaseIterableYAMLDeserializer<C, T> {

    C collection = newCollection();

    /**
     * <p>Constructor for BaseCollectionYAMLDeserializer.</p>
     * @param deserializer {@link YAMLDeserializer} used to map the objects inside the {@link java.util.Collection}.
     */
    public BaseCollectionYAMLDeserializer(YAMLDeserializer<T> deserializer) {
        super(deserializer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {

/*        YAMLIterator.Scanner scanner = (YAMLIterator.Scanner<C>) (reader1, ctx1, instance) -> {
            T element = deserializer.deserialize(reader1, ctx1, params);
            if (element != null) {
                instance.add(element);
            }
            return null;
        };
        return (C) ctx.iterator().iterateOverCollection(reader, ctx.isWrapCollections() ? ((Collection<C>) newCollection()) : (Collection<C>) collection,
                                                        scanner, ctx, params);*/
        throw new UnsupportedOperationException();
    }

    /**
     * Instantiates a new collection for deserialization process.
     * @return the new collection
     */
    protected abstract C newCollection();

    /**
     * <p>isNullValueAllowed</p>
     * @return true if the collection accepts null value
     */
    protected boolean isNullValueAllowed() {
        return true;
    }
}
