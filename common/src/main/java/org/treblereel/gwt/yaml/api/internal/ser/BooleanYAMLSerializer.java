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

package org.treblereel.gwt.yaml.api.internal.ser;

import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLSequenceWriter;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link AbstractYAMLSerializer} implementation for {@link Boolean}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class BooleanYAMLSerializer implements YAMLSerializer<Boolean> {

  public static final BooleanYAMLSerializer INSTANCE = new BooleanYAMLSerializer();

  @Override
  public void serialize(
      YAMLWriter writer, String propertyName, Boolean value, YAMLSerializationContext ctx) {
    writer.value(propertyName, String.valueOf(value));
  }

  @Override
  public void serialize(YAMLSequenceWriter writer, Boolean value, YAMLSerializationContext ctx) {
    if (null == value) {
      if (ctx.isSerializeNulls()) {
        writer.value("~");
      }
    } else {
      writer.value(String.valueOf(value));
    }
  }
}
