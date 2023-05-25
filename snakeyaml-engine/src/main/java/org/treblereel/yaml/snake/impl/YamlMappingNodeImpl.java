/*
 * Copyright © 2023 Treblereel
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

package org.treblereel.yaml.snake.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.snakeyaml.engine.v2.api.Dump;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.treblereel.gwt.yaml.api.NodeType;
import org.treblereel.gwt.yaml.api.YamlMappingNode;
import org.treblereel.gwt.yaml.api.YamlNode;
import org.treblereel.gwt.yaml.api.YamlScalarNode;
import org.treblereel.gwt.yaml.api.YamlSequenceNode;
import org.treblereel.gwt.yaml.api.exception.YamlReadingException;

class YamlMappingNodeImpl implements YamlMappingNode, Wrappable<Map<String, Object>> {

  private final DumpSettings settings;

  private final Map<String, YamlNode> holder = new HashMap<>();

  YamlMappingNodeImpl() {
    this(DumpSettings.builder().build());
  }

  YamlMappingNodeImpl(DumpSettings settings) {
    this.settings = settings;
  }

  YamlMappingNodeImpl(Map<String, Object> map) {
    this(DumpSettings.builder().build(), map);
  }

  @SuppressWarnings("unchecked")
  YamlMappingNodeImpl(DumpSettings settings, Map<String, Object> map) {
    this(settings);
    for (Entry<String, Object> entry : map.entrySet()) {
      String k = entry.getKey();
      Object v = entry.getValue();
      if (v instanceof Map) {
        holder.put(k, new YamlMappingNodeImpl(settings, (Map<String, Object>) v));
      } else if (v instanceof Iterable) {
        holder.put(k, new YamlSequenceNodeImpl(settings, (List<Object>) v));
      } else {
        holder.put(k, new YamlScalarNodeImpl(v.toString()));
      }
    }
  }

  @Override
  public Collection<String> keys() {
    return Collections.unmodifiableCollection(holder.keySet());
  }

  @Override
  public Collection<YamlNode> values() {
    return Collections.unmodifiableCollection(holder.values());
  }

  @Override
  public YamlMappingNode mapping(String key) {
    if (holder.containsKey(key)) {
      return holder.get(key).asMapping();
    }
    throw new YamlReadingException("Key " + key + " not found");
  }

  @Override
  public YamlSequenceNode sequence(String key) {
    if (holder.containsKey(key)) {
      return holder.get(key).asSequence();
    }
    throw new YamlReadingException("Key " + key + " not found");
  }

  @Override
  public String string(String key) {
    if (holder.containsKey(key)) {
      return holder.get(key).asScalar().value();
    }
    throw new YamlReadingException("Key " + key + " not found");
  }

  @Override
  public YamlNode value(String key) {
    if (holder.containsKey(key)) {
      return holder.get(key);
    }
    throw new YamlReadingException("Key " + key + " not found");
  }

  @Override
  public YamlNode addNode(String key, YamlNode node) {
    holder.put(key, node);
    return node;
  }

  @Override
  public YamlScalarNode addScalarNode(String key, String value) {
    YamlScalarNodeImpl node = new YamlScalarNodeImpl(value);
    holder.put(key, node);
    return node;
  }

  @Override
  public YamlSequenceNode addSequenceNode(String key) {
    YamlSequenceNodeImpl node = new YamlSequenceNodeImpl(settings);
    holder.put(key, node);
    return node;
  }

  @Override
  public YamlMappingNode addMappingNode(String key) {
    YamlMappingNodeImpl node = new YamlMappingNodeImpl(settings);
    holder.put(key, node);
    return node;
  }

  @Override
  public boolean isEmpty() {
    return holder.isEmpty();
  }

  @Override
  public NodeType type() {
    return NodeType.MAPPING;
  }

  @Override
  public YamlScalarNode asScalar() throws YamlReadingException {
    throw new YamlReadingException("Node is not scalar");
  }

  @Override
  public YamlMappingNode asMapping() throws YamlReadingException {
    return this;
  }

  @Override
  public YamlSequenceNode asSequence() throws YamlReadingException {
    throw new YamlReadingException("Node is not sequence");
  }

  @Override
  public String toString() {
    return new Dump(settings).dumpToString(unwrap());
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> unwrap() {
    Map<String, Object> unwapped = new HashMap<>();
    for (Entry<String, YamlNode> entry : holder.entrySet()) {
      unwapped.put(entry.getKey(), ((Wrappable<Object>) entry.getValue()).unwrap());
    }
    return unwapped;
  }
}
