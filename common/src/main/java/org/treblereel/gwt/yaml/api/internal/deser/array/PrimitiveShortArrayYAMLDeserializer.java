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

package org.treblereel.gwt.yaml.api.internal.deser.array;

import com.amihaiemil.eoyaml.YamlMapping;
import java.util.List;
import org.treblereel.gwt.yaml.api.internal.deser.BaseNumberYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for array of short.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveShortArrayYAMLDeserializer extends AbstractArrayYAMLDeserializer<short[]> {

  public static final PrimitiveShortArrayYAMLDeserializer INSTANCE =
      new PrimitiveShortArrayYAMLDeserializer();

  /** {@inheritDoc} */
  @Override
  public short[] doDeserializeArray(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    List<Short> list =
        deserializeIntoList(
            yaml.yamlSequence(key), BaseNumberYAMLDeserializer.ShortYAMLDeserializer.INSTANCE, ctx);

    short[] result = new short[list.size()];
    int i = 0;
    for (Short value : list) {
      if (null != value) {
        result[i] = value;
      }
      i++;
    }
    return result;
  }
}
