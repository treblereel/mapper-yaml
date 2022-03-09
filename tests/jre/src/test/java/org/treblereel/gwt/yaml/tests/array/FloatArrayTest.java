/*
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

package org.treblereel.gwt.yaml.tests.array;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@J2clTestInput(FloatArrayTest.class)
public class FloatArrayTest {

    private static final FloatArrayTest_FloatArray_YAMLMapperImpl mapper = FloatArrayTest_FloatArray_YAMLMapperImpl.INSTANCE;

    private static final float[] values = new float[] {17222f, 2111.34f, 32223.34f, 6226.37f};

    @Test
    public void testSerializeValue() throws IOException {
        FloatArrayTest.FloatArray array = new FloatArrayTest.FloatArray();
        array.setValues(values);
        String yaml = mapper.write(array);

        assertEquals("values:\n" +
                "  - 17222.0\n" +
                "  - 2111.34\n" +
                "  - 32223.34\n" +
                "  - 6226.37", yaml);

    }

    @Test
    public void tesDeSerializeValue() throws IOException {
        FloatArrayTest.FloatArray array = new FloatArrayTest.FloatArray();
        array.setValues(values);
        String yaml = mapper.write(array);
        assertEquals(array, mapper.read(yaml));
    }


    @YAMLMapper
    public static class FloatArray {

        private float[] values;

        public float[] getValues() {
            return values;
        }

        public void setValues(float[] values) {
            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FloatArray that = (FloatArray) o;
            return Arrays.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(values);
        }
    }
}

