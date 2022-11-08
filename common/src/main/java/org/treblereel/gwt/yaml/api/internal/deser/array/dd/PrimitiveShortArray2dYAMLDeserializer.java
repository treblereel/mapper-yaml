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
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.internal.deser.BaseNumberYAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for 2D array of short.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveShortArray2dYAMLDeserializer
    extends AbstractArray2dYAMLDeserializer<short[][]> {

  private static final PrimitiveShortArray2dYAMLDeserializer INSTANCE =
      new PrimitiveShortArray2dYAMLDeserializer();

  private PrimitiveShortArray2dYAMLDeserializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link PrimitiveShortArray2dYAMLDeserializer}
   */
  public static PrimitiveShortArray2dYAMLDeserializer newInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public short[][] doDeserialize(
      YamlMapping yaml,
      String key,
      YAMLDeserializationContext ctx,
      YAMLDeserializerParameters params) {
    List<List<Short>> list =
        deserializeIntoList(
            yaml.yamlSequence(key),
            ctx,
            BaseNumberYAMLDeserializer.ShortYAMLDeserializer.getInstance(),
            params);

    if (list.isEmpty()) {
      return new short[0][0];
    }

    List<Short> firstList = list.get(0);
    if (firstList.isEmpty()) {
      return new short[list.size()][0];
    }

    short[][] array = new short[list.size()][firstList.size()];

    int i = 0;
    int j;
    for (List<Short> innerList : list) {
      j = 0;
      for (Short value : innerList) {
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
