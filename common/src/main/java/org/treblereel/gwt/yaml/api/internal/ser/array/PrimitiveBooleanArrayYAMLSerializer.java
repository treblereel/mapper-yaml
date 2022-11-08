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

import java.util.ArrayList;
import java.util.Collection;
import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for array of boolean.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveBooleanArrayYAMLSerializer extends BasicArrayYAMLSerializer<boolean[]> {

  private static final PrimitiveBooleanArrayYAMLSerializer INSTANCE =
      new PrimitiveBooleanArrayYAMLSerializer();

  private PrimitiveBooleanArrayYAMLSerializer() {}

  public static BasicArrayYAMLSerializer getInstance(String propertyName) {
    return INSTANCE.setPropertyName(propertyName);
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(boolean[] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(
      YAMLWriter writer,
      boolean[] values,
      YAMLSerializationContext ctx,
      YAMLSerializerParameters params) {
    if (!ctx.isWriteEmptyYAMLArrays() && values.length == 0) {
      writer.nullValue(propertyName);
      return;
    }
    Collection<String> temp = new ArrayList<>();
    for (boolean value : values) {
      temp.add(String.valueOf(value));
    }
    writer.collectionOfString(propertyName, temp);
  }
}