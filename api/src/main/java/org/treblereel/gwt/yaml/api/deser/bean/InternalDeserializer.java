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

import java.util.Map;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Interface hiding the actual implementation doing the bean deserialization.
 * @author Nicolas Morel.
 */
interface InternalDeserializer<T, S extends YAMLDeserializer<T>> {

    /**
     * <p>getDeserializer</p>
     * @return a S object.
     */
    S getDeserializer();

    /**
     * <p>deserializeInline</p>
     * @param reader a {@link YAMLReader} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @param identityInfo a {@link IdentityDeserializationInfo} object.
     * @param typeInfo a {@link TypeDeserializationInfo} object.
     * @param typeInformation a {@link java.lang.String} object.
     * @param typeInformation a {@link java.lang.String} object.
     * @param bufferedProperties a {@link java.util.Map} object.
     * @return a T object.
     */
    T deserializeInline(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params,
                        IdentityDeserializationInfo identityInfo, TypeDeserializationInfo typeInfo,
                        String typeInformation, Map<String,
            String> bufferedProperties);

    /**
     * <p>deserializeWrapped</p>
     * @param reader a {@link YAMLReader} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @param identityInfo a {@link IdentityDeserializationInfo} object.
     * @param typeInfo a {@link TypeDeserializationInfo} object.
     * @param typeInformation a {@link java.lang.String} object.
     * @param typeInformation a {@link java.lang.String} object.
     * @return a T object.
     */
    T deserializeWrapped(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params,
                         IdentityDeserializationInfo identityInfo, TypeDeserializationInfo typeInfo,
                         String typeInformation);
}

