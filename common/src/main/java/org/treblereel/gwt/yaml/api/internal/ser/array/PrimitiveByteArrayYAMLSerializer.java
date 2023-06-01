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

import org.treblereel.gwt.yaml.api.internal.ser.AbstractYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.internal.utils.Base64Utils;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlSequence;

/**
 * Default {@link AbstractYAMLSerializer} implementation for array of byte.
 *
 * @author Nicolas Morel
 * @version $Id: $Id
 */
public class PrimitiveByteArrayYAMLSerializer extends AbstractYAMLSerializer<byte[]> {

  public static final PrimitiveByteArrayYAMLSerializer INSTANCE =
      new PrimitiveByteArrayYAMLSerializer();

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(byte[] value) {
    return null == value || value.length == 0;
  }

  /** {@inheritDoc} */
  @Override
  public void serialize(
      YamlMapping writer, String propertyName, byte[] values, YAMLSerializationContext ctx) {
    if (isEmpty(values)) {
      if (ctx.isWriteEmptyYAMLArrays()) {
        writer.addScalarNode(propertyName, new byte[] {});
      }
      return;
    }
    writer.addScalarNode(propertyName, Base64Utils.toBase64(values));
  }

  @Override
  public void serialize(YamlSequence writer, byte[] value, YAMLSerializationContext ctx) {
    writer.addScalarNode(Base64Utils.toBase64(value));
  }
}
