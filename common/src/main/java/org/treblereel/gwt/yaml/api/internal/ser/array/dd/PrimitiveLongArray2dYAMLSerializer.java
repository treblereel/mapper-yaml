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
import org.treblereel.gwt.yaml.api.stream.YAMLSequenceWriter;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link AbstractYAMLSerializer} implementation for 2D array of long.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveLongArray2dYAMLSerializer extends BasicArrayYAMLSerializer<long[][]> {

  private static final PrimitiveLongArray2dYAMLSerializer INSTANCE =
      new PrimitiveLongArray2dYAMLSerializer();

  private PrimitiveLongArray2dYAMLSerializer() {}

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(long[][] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YAMLWriter writer, long[][] values, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void serialize(YAMLSequenceWriter writer, long[][] value, YAMLSerializationContext ctx) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
