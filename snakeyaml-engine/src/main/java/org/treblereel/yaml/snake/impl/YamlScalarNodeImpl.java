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

import org.treblereel.gwt.yaml.api.NodeType;
import org.treblereel.gwt.yaml.api.YamlMappingNode;
import org.treblereel.gwt.yaml.api.YamlScalarNode;
import org.treblereel.gwt.yaml.api.YamlSequenceNode;
import org.treblereel.gwt.yaml.api.exception.YamlReadingException;

class YamlScalarNodeImpl implements YamlScalarNode, Wrappable<String> {

  private final String value;

  YamlScalarNodeImpl(String value) {
    this.value = value;
  }

  @Override
  public boolean isEmpty() {
    return value == null;
  }

  @Override
  public NodeType type() {
    return NodeType.SCALAR;
  }

  @Override
  public YamlScalarNode asScalar() throws YamlReadingException {
    return this;
  }

  @Override
  public YamlMappingNode asMapping() throws YamlReadingException {
    throw new YamlReadingException("Can't convert scalar to mapping");
  }

  @Override
  public YamlSequenceNode asSequence() throws YamlReadingException {
    throw new YamlReadingException("Can't convert scalar to sequence");
  }

  @Override
  public String value() {
    return value;
  }

  @Override
  public String unwrap() {
    return value;
  }
}
