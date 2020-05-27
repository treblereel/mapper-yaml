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

package org.treblereel.gwt.yaml.api.deser.array.dd;

import java.util.List;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Default {@link YAMLDeserializer} implementation for 2D array of float.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class PrimitiveFloatArray2dYAMLDeserializer extends AbstractArray2dYAMLDeserializer<float[][]> {

    private static final PrimitiveFloatArray2dYAMLDeserializer INSTANCE = new PrimitiveFloatArray2dYAMLDeserializer();

    private PrimitiveFloatArray2dYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link PrimitiveFloatArray2dYAMLDeserializer}
     */
    public static PrimitiveFloatArray2dYAMLDeserializer getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float[][] doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        List<List<Float>> list = deserializeIntoList(reader, ctx, BaseNumberYAMLDeserializer.FloatYAMLDeserializer.getInstance(), params);

        if (list.isEmpty()) {
            return new float[0][0];
        }

        List<Float> firstList = list.get(0);
        if (firstList.isEmpty()) {
            return new float[list.size()][0];
        }

        float[][] array = new float[list.size()][firstList.size()];

        int i = 0;
        int j;
        for (List<Float> innerList : list) {
            j = 0;
            for (Float value : innerList) {
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
