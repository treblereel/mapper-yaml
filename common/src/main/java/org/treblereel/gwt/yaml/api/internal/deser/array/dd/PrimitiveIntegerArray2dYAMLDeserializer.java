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

import java.util.List;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.BaseNumberYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;

/**
 * Default {@link YAMLDeserializer} implementation for 2D array of int.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveIntegerArray2dYAMLDeserializer
    extends AbstractArray2dYAMLDeserializer<int[][]> {

  public static final PrimitiveIntegerArray2dYAMLDeserializer INSTANCE =
      new PrimitiveIntegerArray2dYAMLDeserializer();

  /** {@inheritDoc} */
  @Override
  public int[][] deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    List<List<Integer>> list =
        deserializeIntoList(
            yaml.getSequenceNode(key),
            ctx,
            BaseNumberYAMLDeserializer.IntegerYAMLDeserializer.INSTANCE);

    if (list.isEmpty()) {
      return new int[0][0];
    }

    List<Integer> firstList = list.get(0);
    if (firstList.isEmpty()) {
      return new int[list.size()][0];
    }

    int[][] array = new int[list.size()][firstList.size()];

    int i = 0;
    int j;
    for (List<Integer> innerList : list) {
      j = 0;
      for (Integer value : innerList) {
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
