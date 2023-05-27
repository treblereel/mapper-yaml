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

package org.treblereel.gwt.yaml.api.internal.ser.array.dd;

import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.array.BasicArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

/**
 * Default {@link AbstractYAMLSerializer} implementation for 2D array of short.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveShortArray2dYAMLSerializer extends BasicArrayYAMLSerializer<short[][]> {

  public static final PrimitiveShortArray2dYAMLSerializer INSTANCE =
      new PrimitiveShortArray2dYAMLSerializer();

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(short[][] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YamlMapping writer, short[][] values, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void serialize(YamlSequence writer, short[][] value, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
