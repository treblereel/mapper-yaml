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

import java.util.Map;

import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base implementation of {@link YAMLDeserializer} for beans.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractBeanYAMLDeserializer<T> extends YAMLDeserializer<T> implements InternalDeserializer<T,
        AbstractBeanYAMLDeserializer<T>> {

    protected final InstanceBuilder<T> instanceBuilder;
    private final IdentityDeserializationInfo defaultIdentityInfo;
    private MapLike<BeanPropertyDeserializer<T, ?>> deserializers;

    /**
     * <p>Constructor for AbstractBeanYAMLDeserializer.</p>
     */
    protected AbstractBeanYAMLDeserializer() {
        this.instanceBuilder = initInstanceBuilder();
        this.defaultIdentityInfo = initIdentityInfo();
    }

    /**
     * Initialize the {@link InstanceBuilder}. Returns null if the class isn't instantiable.
     * @return a {@link InstanceBuilder} object.
     */
    protected InstanceBuilder<T> initInstanceBuilder() {
        return null;
    }

    /**
     * Initialize the {@link IdentityDeserializationInfo}.
     * @return a {@link IdentityDeserializationInfo} object.
     */
    protected IdentityDeserializationInfo initIdentityInfo() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        deserializers = initDeserializers();
        // Processing the parameters. We fallback to default if parameter is not present.
        final IdentityDeserializationInfo identityInfo = null == params.getIdentityInfo() ? defaultIdentityInfo : params.getIdentityInfo();
        return deserializeWrapped(reader, ctx, params, identityInfo, null, null);
    }

    /**
     * Initialize the {@link MapLike} containing the property deserializers. Returns an empty map if there are no properties to
     * deserialize.
     * @return a {@link MapLike} object.
     */
    protected MapLike<BeanPropertyDeserializer<T, ?>> initDeserializers() {
        //Change by Ahmad Bawaneh, replace JSNI types with IsInterop types
        return YAMLContextProvider.get().mapLikeFactory().make();
    }

    protected abstract String getRootElement();

    /**
     * <p>getDeserializedType</p>
     * @return a {@link java.lang.Class} object.
     */
    public abstract Class getDeserializedType();

    private BeanPropertyDeserializer<T, ?> getPropertyDeserializer(String propertyName, YAMLDeserializationContext ctx) {
        BeanPropertyDeserializer<T, ?> property = deserializers.get(propertyName);
        if (null == property) {
            throw ctx.traceError("Unknown property '" + propertyName + "' in (de)serializer " + this.getClass().getCanonicalName());
        }
        return property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractBeanYAMLDeserializer<T> getDeserializer() {
        return this;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Deserializes all the properties of the bean. The {@link YAMLReader} must be in a json object.
     */
    @Override
    public final T deserializeInline(final YAMLReader reader, final YAMLDeserializationContext ctx, YAMLDeserializerParameters params,
                                     IdentityDeserializationInfo identityInfo, TypeDeserializationInfo typeInfo, String type,
                                     Map<String, String> bufferedProperties) {
        T instance = instanceBuilder.newInstance(reader, ctx, params, null, null).getInstance();
        deserializers.keys().forEach(key -> {
            getPropertyDeserializer(key, ctx).deserialize(reader.getValue(key), instance, ctx);
        });
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T deserializeWrapped(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params,
                                IdentityDeserializationInfo identityInfo, TypeDeserializationInfo typeInfo, String typeInformation) {
        return deserializeInline(reader, ctx, params, identityInfo, typeInfo, typeInformation, null);
    }
}
