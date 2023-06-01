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

package org.treblereel.gwt.yaml.api.internal.ser.array;

import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.BaseNumberYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

/**
 * Default {@link AbstractYAMLSerializer} implementation for array of double.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveDoubleArrayYAMLSerializer extends AbstractYAMLSerializer<double[]> {

  public static final PrimitiveDoubleArrayYAMLSerializer INSTANCE =
      new PrimitiveDoubleArrayYAMLSerializer();

  private final BaseNumberYAMLSerializer.DoubleYAMLSerializer serializer =
      BaseNumberYAMLSerializer.DoubleYAMLSerializer.INSTANCE;

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(double[] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void serialize(
      YamlMapping writer, String propertyName, double[] values, YAMLSerializationContext ctx) {
    if (isEmpty(values)) {
      if (ctx.isWriteEmptyYAMLArrays()) {
        writer.addScalarNode(propertyName, new double[] {});
      }
      return;
    }
    YamlSequence yamlSequence = writer.addSequenceNode(propertyName);
    serialize(yamlSequence, values, ctx);
  }

  @Override
  public void serialize(YamlSequence writer, double[] value, YAMLSerializationContext ctx) {
    for (double o : value) {
      serializer.serialize(writer, o, ctx);
    }
  }
}
