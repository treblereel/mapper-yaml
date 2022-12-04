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
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base implementation of {@link YAMLDeserializer} for beans.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class AbstractBeanYAMLDeserializer<T> implements YAMLDeserializer<T> {

  protected final InstanceBuilder<T> instanceBuilder;
  private Map<String, BeanPropertyDeserializer<T, ?>> deserializers = initDeserializers();

  /** Constructor for AbstractBeanYAMLDeserializer. */
  protected AbstractBeanYAMLDeserializer() {
    this.instanceBuilder = initInstanceBuilder();
  }

  /**
   * Initialize the {@link InstanceBuilder}. Returns null if the class isn't instantiable.
   *
   * @return a {@link InstanceBuilder} object.
   */
  protected InstanceBuilder<T> initInstanceBuilder() {
    return null;
  }

  /** {@inheritDoc} */
  public T deserialize(YamlMapping yaml, YAMLDeserializationContext ctx) {
    return deserializeInline(yaml, ctx);
  }

  @Override
  public T deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    return deserialize(yaml.yamlMapping(key), ctx);
  }

  /**
   * Initialize the {@link Map} containing the property deserializers. Returns an empty map if there
   * are no properties to deserialize.
   *
   * @return a {@link Map} object.
   */
  protected abstract Map<String, BeanPropertyDeserializer<T, ?>> initDeserializers();

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
      throw new Error(
          "Unknown property '"
              + propertyName
              + "' in (de)serializer "
              + this.getClass().getCanonicalName());
    }
    return property;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Deserializes all the properties of the bean. The {@link YAMLReader} must be in a json
   * object.
   */
  public final T deserializeInline(YamlMapping yaml, YAMLDeserializationContext ctx) {
    T instance = instanceBuilder.newInstance(ctx).getInstance();
    deserializers
        .keySet()
        .forEach(
            key -> {
              if (getPropertyDeserializer(key, ctx).getDeserializer()
                  instanceof AbstractBeanYAMLDeserializer) {
                YamlMapping node = yaml.yamlMapping(key);
                BeanPropertyDeserializer propertyDeserializer = getPropertyDeserializer(key, ctx);
                Object value =
                    ((AbstractBeanYAMLDeserializer) propertyDeserializer.getDeserializer())
                        .deserialize(node, ctx);
                propertyDeserializer.setValue(instance, value, ctx);
              } else {
                getPropertyDeserializer(key, ctx).deserialize(yaml, key, instance, ctx);
              }
            });
    return instance;
  }

  @Override
  public T deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
