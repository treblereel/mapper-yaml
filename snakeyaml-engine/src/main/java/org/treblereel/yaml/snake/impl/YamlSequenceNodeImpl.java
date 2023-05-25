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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.treblereel.gwt.yaml.api.NodeType;
import org.treblereel.gwt.yaml.api.YamlMappingNode;
import org.treblereel.gwt.yaml.api.YamlNode;
import org.treblereel.gwt.yaml.api.YamlScalarNode;
import org.treblereel.gwt.yaml.api.YamlSequenceNode;
import org.treblereel.gwt.yaml.api.exception.YamlReadingException;

class YamlSequenceNodeImpl implements YamlSequenceNode, Wrappable<List<Object>> {

  private final List<YamlNode> nodes = new ArrayList<>();

  private final DumpSettings settings;

  YamlSequenceNodeImpl(DumpSettings settings) {
    this.settings = settings;
  }

  @SuppressWarnings("unchecked")
  YamlSequenceNodeImpl(DumpSettings settings, List<Object> list) {
    this(settings);
    for (Object l : list) {
      if (l instanceof Map) {
        nodes.add(new YamlMappingNodeImpl((Map<String, Object>) l));
      } else if (l instanceof Iterable) {
        nodes.add(new YamlSequenceNodeImpl(settings, (List<Object>) l));
      } else {
        nodes.add(new YamlScalarNodeImpl(l.toString()));
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return nodes.isEmpty();
  }

  @Override
  public NodeType type() {
    return NodeType.SEQUENCE;
  }

  @Override
  public YamlScalarNode asScalar() throws YamlReadingException {
    throw new YamlReadingException("Can't convert sequence to scalar");
  }

  @Override
  public YamlMappingNode asMapping() throws YamlReadingException {
    throw new YamlReadingException("Can't convert sequence to mapping");
  }

  @Override
  public YamlSequenceNode asSequence() throws YamlReadingException {
    return this;
  }

  @Override
  public int size() {
    return nodes.size();
  }

  @Override
  public Collection<YamlNode> values() {
    return nodes;
  }

  @Override
  public Iterator<YamlNode> iterator() {
    return Collections.unmodifiableCollection(nodes).iterator();
  }

  @Override
  public YamlMappingNode mapping(int index) {
    if (index < nodes.size() && nodes.get(index).type() == NodeType.MAPPING) {
      return nodes.get(index).asMapping();
    }
    throw new YamlReadingException("Can't convert sequence to mapping");
  }

  @Override
  public YamlSequenceNode sequence(int index) {
    if (index < nodes.size() && nodes.get(index).type() == NodeType.SEQUENCE) {
      return nodes.get(index).asSequence();
    }
    throw new YamlReadingException("Can't convert sequence to sequence");
  }

  @Override
  public String string(int index) {
    if (index < nodes.size() && nodes.get(index).type() == NodeType.SCALAR) {
      return nodes.get(index).asScalar().value();
    }
    throw new YamlReadingException("Can't convert sequence to string");
  }

  @Override
  public YamlNode addNode(YamlNode node) {
    nodes.add(node);
    return node;
  }

  @Override
  public YamlScalarNode addScalarNode(String value) {
    YamlScalarNodeImpl node = new YamlScalarNodeImpl(value);
    nodes.add(node);
    return node;
  }

  @Override
  public YamlMappingNode addMappingNode(String key) {
    YamlMappingNodeImpl node = new YamlMappingNodeImpl(settings);
    nodes.add(node);
    return node;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object> unwrap() {
    return nodes.stream()
        .map(node -> ((Wrappable<Object>) node).unwrap())
        .collect(Collectors.toList());
  }
}
