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

package org.treblereel.gwt.yaml.api.internal.ser.array.dd;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.array.BasicArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for 2D array of byte.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveByteArray2dYAMLSerializer extends BasicArrayYAMLSerializer<byte[][]> {

  private static final PrimitiveByteArray2dYAMLSerializer INSTANCE =
      new PrimitiveByteArray2dYAMLSerializer();

  private PrimitiveByteArray2dYAMLSerializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link PrimitiveByteArray2dYAMLSerializer}
   */
  public static BasicArrayYAMLSerializer getInstance(String propertyName) {
    return INSTANCE.setPropertyName(propertyName);
  }

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(byte[][] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void doSerialize(YAMLWriter writer, byte[][] values, YAMLSerializationContext ctx) {
    if (!ctx.isWriteEmptyYAMLArrays() && values.length == 0) {
      writer.nullValue(propertyName);
      return;
    }

    /*        if (values.length == 0) {
        if (ctx.isWriteEmptyYAMLArrays()) {
            writer.beginObject(propertyName);
            writer.endObject();
        } else {
            writer.unescapeName(propertyName);
            writer.nullValue();
        }
        return;
    }
    BasicArrayYAMLSerializer serializer = PrimitiveByteArrayYAMLSerializer.getInstance(propertyName);
    writer.beginObject(propertyName);
    for (byte[] value : values) {
        serializer.serialize(writer, value, ctx, params);
    }
    writer.endObject();*/
  }
}
