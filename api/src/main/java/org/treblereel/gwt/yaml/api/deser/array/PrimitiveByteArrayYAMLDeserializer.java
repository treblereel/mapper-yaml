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

package org.treblereel.gwt.yaml.api.deser.array;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.StringYAMLDeserializer;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;
import org.treblereel.gwt.yaml.api.utils.Base64Utils;

/**
 * Default {@link YAMLDeserializer} implementation for array of byte.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveByteArrayYAMLDeserializer extends AbstractArrayYAMLDeserializer<byte[]> {

    private static final PrimitiveByteArrayYAMLDeserializer INSTANCE = new PrimitiveByteArrayYAMLDeserializer();

    private PrimitiveByteArrayYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link PrimitiveByteArrayYAMLDeserializer}
     */
    public static PrimitiveByteArrayYAMLDeserializer getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] doDeserializeArray(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {

        String result = StringYAMLDeserializer.getInstance().doDeserialize(reader, ctx, params);
        if (result != null) {
            return Base64Utils.fromBase64(result);
        }

        return new byte[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] doDeserializeSingleArray(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return new byte[]{BaseNumberYAMLDeserializer.ByteYAMLDeserializer.getInstance().deserialize(reader, ctx, params)};
    }
}
