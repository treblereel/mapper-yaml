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

import java.util.*;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

/**
 * Base {@link YAMLDeserializer} implementation for {@link java.util.Set}.
 * @param <S> {@link java.util.Set} type
 * @param <T> Type of the elements inside the {@link java.util.Set}
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseSetYAMLDeserializer<S extends Set<T>, T> extends BaseCollectionYAMLDeserializer<S, T> {

    /**
     * <p>Constructor for BaseSetYAMLDeserializer.</p>
     * @param deserializer {@link YAMLDeserializer} used to map the objects inside the {@link java.util.Set}.
     */
    public BaseSetYAMLDeserializer(YAMLDeserializer<T> deserializer) {
        super(deserializer);
    }

    @Override
    public S doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        S result = (S) new HashSet<T>();
        Collection<T> temp = super.doDeserialize(yaml, key, ctx, params);
        for (T val: temp){
            result.add(val);
        }
        return result;
    }
}
