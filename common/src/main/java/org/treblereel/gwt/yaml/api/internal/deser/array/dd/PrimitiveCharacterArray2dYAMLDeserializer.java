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
import org.treblereel.gwt.yaml.api.internal.deser.CharacterYAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for 2D array of char.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveCharacterArray2dYAMLDeserializer
    extends AbstractArray2dYAMLDeserializer<char[][]> {

  private static final PrimitiveCharacterArray2dYAMLDeserializer INSTANCE =
      new PrimitiveCharacterArray2dYAMLDeserializer();

  private PrimitiveCharacterArray2dYAMLDeserializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link PrimitiveCharacterArray2dYAMLDeserializer}
   */
  public static PrimitiveCharacterArray2dYAMLDeserializer getInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public char[][] doDeserialize(
      YamlMapping yaml,
      String key,
      YAMLDeserializationContext ctx,
      YAMLDeserializerParameters params) {
    List<List<Character>> list =
        deserializeIntoList(
            yaml.yamlSequence(key), ctx, CharacterYAMLDeserializer.getInstance(), params);

    if (list.isEmpty()) {
      return new char[0][0];
    }

    List<Character> firstList = list.get(0);
    if (firstList.isEmpty()) {
      return new char[list.size()][0];
    }

    char[][] array = new char[list.size()][firstList.size()];

    int i = 0;
    int j;
    for (List<Character> innerList : list) {
      j = 0;
      for (Character value : innerList) {
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