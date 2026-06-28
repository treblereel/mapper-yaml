/*
 * Copyright © 2022 Treblereel
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.node.NodeType;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;

public abstract class AbstractCreatorBeanYAMLDeserializer<T>
    extends AbstractBeanYAMLDeserializer<T> {

  protected Map<String, YamlCreatorParameterDeserializer> creatorParams = new LinkedHashMap<>();

  @Override
  public T deserialize(YamlMapping yaml, YAMLDeserializationContext ctx) {
    if (yaml == null || yaml.isEmpty()) {
      return null;
    }

    Map<String, Object> params = new HashMap<>();
    Set<String> yamlKeys = new HashSet<>(yaml.keys());
    creatorParams.forEach(
        (key, deserializer) -> {
          if (!yamlKeys.contains(key)) {
            return;
          }
          YamlNode node = yaml.getNode(key);
          if (node.type() == NodeType.SCALAR && node.asScalar().value() == null) {
            return;
          }
          params.put(key, deserializer.deserialize(yaml, ctx));
        });

    T instance = createInstance(params);

    Map<String, BeanPropertyDeserializer<T, ?>> deserializers = initDeserializers();
    Set<String> props = new HashSet<>(yaml.keys());
    props.retainAll(deserializers.keySet());
    props.forEach(
        key -> {
          @SuppressWarnings("unchecked")
          BeanPropertyDeserializer propertyDeserializer = deserializers.get(key);
          YamlNode node = yaml.getNode(key);
          if (node != null && node.type() == NodeType.SCALAR && node.asScalar().value() == null) {
            return;
          }
          if (propertyDeserializer.getDeserializer() instanceof AbstractBeanYAMLDeserializer) {
            YamlMapping node2 = yaml.getMappingNode(key);
            Object value =
                ((AbstractBeanYAMLDeserializer) propertyDeserializer.getDeserializer())
                    .deserialize(node2, ctx);
            propertyDeserializer.setValue(instance, value, ctx);
          } else {
            propertyDeserializer.deserialize(yaml, key, instance, ctx);
          }
        });

    return instance;
  }

  protected abstract T createInstance(Map<String, Object> params);
}
