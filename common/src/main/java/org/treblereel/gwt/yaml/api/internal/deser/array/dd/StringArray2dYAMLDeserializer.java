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

package org.treblereel.gwt.yaml.api.internal.deser.array.dd;

import com.amihaiemil.eoyaml.YamlMapping;
import java.util.List;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.StringYAMLDeserializer;

/** @author Dmitrii Tikhomirov Created by treblereel 3/28/20 */
public class StringArray2dYAMLDeserializer extends AbstractArray2dYAMLDeserializer<String[][]> {

  private static final StringArray2dYAMLDeserializer INSTANCE = new StringArray2dYAMLDeserializer();

  private StringArray2dYAMLDeserializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link StringArray2dYAMLDeserializer}
   */
  public static StringArray2dYAMLDeserializer newInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public String[][] doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    List<List<String>> list =
        deserializeIntoList(yaml.yamlSequence(key), ctx, StringYAMLDeserializer.getInstance());

    if (list.isEmpty()) {
      return new String[0][0];
    }

    List<String> firstList = list.get(0);
    if (firstList.isEmpty()) {
      return new String[list.size()][0];
    }

    String[][] array = new String[list.size()][firstList.size()];

    int i = 0;
    int j;
    for (List<String> innerList : list) {
      j = 0;
      for (String value : innerList) {
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
