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

package org.treblereel.gwt.yaml.api.internal.deser.bean;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base implementation of {@link YAMLDeserializer} for beans.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractBeanYAMLDeserializer<T> extends YAMLDeserializer<T>
    implements InternalDeserializer<T, AbstractBeanYAMLDeserializer<T>> {

  protected final InstanceBuilder<T> instanceBuilder;
  private final IdentityDeserializationInfo defaultIdentityInfo;
  private MapLike<BeanPropertyDeserializer<T, ?>> deserializers = initDeserializers();

  /** Constructor for AbstractBeanYAMLDeserializer. */
  protected AbstractBeanYAMLDeserializer() {
    this.instanceBuilder = initInstanceBuilder();
    this.defaultIdentityInfo = initIdentityInfo();
  }

  /**
   * Initialize the {@link InstanceBuilder}. Returns null if the class isn't instantiable.
   *
   * @return a {@link InstanceBuilder} object.
   */
  protected InstanceBuilder<T> initInstanceBuilder() {
    return null;
  }

  /**
   * Initialize the {@link IdentityDeserializationInfo}.
   *
   * @return a {@link IdentityDeserializationInfo} object.
   */
  protected IdentityDeserializationInfo initIdentityInfo() {
    return null;
  }

  /** {@inheritDoc} */
  public T doDeserialize(
      YamlMapping yaml, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
    // Processing the parameters. We fallback to default if parameter is not present.
    return deserializeInline(yaml, ctx, params);
  }

  /**
   * Initialize the {@link MapLike} containing the property deserializers. Returns an empty map if
   * there are no properties to deserialize.
   *
   * @return a {@link MapLike} object.
   */
  protected MapLike<BeanPropertyDeserializer<T, ?>> initDeserializers() {
    // Change by Ahmad Bawaneh, replace JSNI types with IsInterop types
    return YAMLContextProvider.get().mapLikeFactory().make();
  }

  protected abstract String getRootElement();

  /**
   * getDeserializedType
   *
   * @return a {@link java.lang.Class} object.
   */
  public abstract Class getDeserializedType();

  private BeanPropertyDeserializer<T, ?> getPropertyDeserializer(
      String propertyName, YAMLDeserializationContext ctx) {
    BeanPropertyDeserializer<T, ?> property = deserializers.get(propertyName);
    if (null == property) {
      throw ctx.traceError(
          "Unknown property '"
              + propertyName
              + "' in (de)serializer "
              + this.getClass().getCanonicalName());
    }
    return property;
  }

  /** {@inheritDoc} */
  @Override
  public AbstractBeanYAMLDeserializer<T> getDeserializer() {
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Deserializes all the properties of the bean. The {@link YAMLReader} must be in a json
   * object.
   */
  public final T deserializeInline(
      YamlMapping yaml, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
    T instance = instanceBuilder.newInstance(ctx, params).getInstance();
    deserializers
        .keys()
        .forEach(
            key -> {
              if (getPropertyDeserializer(key, ctx).getDeserializer()
                  instanceof AbstractBeanYAMLDeserializer) {
                YamlMapping node = yaml.yamlMapping(key);
                BeanPropertyDeserializer propertyDeserializer = getPropertyDeserializer(key, ctx);
                Object value =
                    ((AbstractBeanYAMLDeserializer) propertyDeserializer.getDeserializer())
                        .doDeserialize(node, ctx, params);
                propertyDeserializer.setValue(instance, value, ctx);
              } else {
                getPropertyDeserializer(key, ctx).deserialize(yaml, key, instance, ctx);
              }
            });
    return instance;
  }
}
