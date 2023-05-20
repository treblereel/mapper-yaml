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

package org.treblereel.gwt.yaml.tests.collection.customser;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Locale;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.stream.YAMLSequenceWriter;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

public class WhenThenRuleThenSerializer
    implements YAMLSerializer<Object>, YAMLDeserializer<Object> {
  @Override
  public Object deserialize(
      YamlMapping yamlMapping, String key, YAMLDeserializationContext yamlDeserializationContext)
      throws YAMLDeserializationException {
    return deserialize(yamlMapping.value(key), yamlDeserializationContext);
  }

  @Override
  public Object deserialize(
      YamlNode yamlNode, YAMLDeserializationContext yamlDeserializationContext) {
    if (yamlNode == null || yamlNode.isEmpty()) {
      return null;
    }
    return yamlNode.asScalar().value().toLowerCase(Locale.ROOT);
  }

  @Override
  public void serialize(
      YAMLWriter yamlWriter,
      String s,
      Object o,
      YAMLSerializationContext yamlSerializationContext) {
    yamlWriter.value(s, o.toString());
  }

  @Override
  public void serialize(
      YAMLSequenceWriter yamlSequenceWriter,
      Object o,
      YAMLSerializationContext yamlSerializationContext) {
    yamlSequenceWriter.value(o.toString());
  }
}
