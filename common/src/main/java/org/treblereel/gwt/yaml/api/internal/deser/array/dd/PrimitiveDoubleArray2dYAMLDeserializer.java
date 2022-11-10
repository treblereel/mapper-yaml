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

package org.treblereel.gwt.yaml.api.internal.deser.array.dd;

import com.amihaiemil.eoyaml.YamlMapping;
import java.util.List;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.BaseNumberYAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for 2D array of double.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveDoubleArray2dYAMLDeserializer
    extends AbstractArray2dYAMLDeserializer<double[][]> {

  private static final PrimitiveDoubleArray2dYAMLDeserializer INSTANCE =
      new PrimitiveDoubleArray2dYAMLDeserializer();

  private PrimitiveDoubleArray2dYAMLDeserializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link PrimitiveDoubleArray2dYAMLDeserializer}
   */
  public static PrimitiveDoubleArray2dYAMLDeserializer getInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public double[][] doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    List<List<Double>> list =
        deserializeIntoList(
            yaml.yamlSequence(key),
            ctx,
            BaseNumberYAMLDeserializer.DoubleYAMLDeserializer.getInstance());

    if (list.isEmpty()) {
      return new double[0][0];
    }

    List<Double> firstList = list.get(0);
    if (firstList.isEmpty()) {
      return new double[list.size()][0];
    }

    double[][] array = new double[list.size()][firstList.size()];

    int i = 0;
    int j;
    for (List<Double> innerList : list) {
      j = 0;
      for (Double value : innerList) {
        if (null != value) {
          array[i][j] = value;
        }
        j++;
      }
      i++;
    }
    return array;
  }
}
