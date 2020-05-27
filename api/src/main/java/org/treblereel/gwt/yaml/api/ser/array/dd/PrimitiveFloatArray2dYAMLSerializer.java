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

package org.treblereel.gwt.yaml.api.ser.array.dd;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.ser.array.BasicArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveFloatArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for 2D array of float.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveFloatArray2dYAMLSerializer extends BasicArrayYAMLSerializer<float[][]> {

    private static final PrimitiveFloatArray2dYAMLSerializer INSTANCE = new PrimitiveFloatArray2dYAMLSerializer();

    private PrimitiveFloatArray2dYAMLSerializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link PrimitiveFloatArray2dYAMLSerializer}
     */
    public static BasicArrayYAMLSerializer getInstance(String propertyName) {
        return INSTANCE.setPropertyName(propertyName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isEmpty(float[][] value) {
        return null == value || value.length == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(YAMLWriter writer, float[][] values, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        if (!ctx.isWriteEmptyYAMLArrays() && values.length == 0) {
            writer.nullValue();
            return;
        }

        BasicArrayYAMLSerializer serializer = PrimitiveFloatArrayYAMLSerializer.getInstance(propertyName);

        writer.beginObject(propertyName);
        for (float[] value : values) {
            serializer.serialize(writer, value, ctx, params);
        }
        writer.endObject();
    }
}
