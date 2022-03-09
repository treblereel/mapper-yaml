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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@J2clTestInput(StringArrayTest.class)
public class StringArrayTest {

    private static final StringArrayTest_StringArray_YAMLMapperImpl mapper = StringArrayTest_StringArray_YAMLMapperImpl.INSTANCE;

    private static final String[] values = new String[] {"aaa", "bbb", "ccc", "ddd"};

    @Test
    public void testSerializeValue() throws IOException {
        StringArrayTest.StringArray array = new StringArrayTest.StringArray();
        array.setValues(values);
        String yaml = mapper.write(array);

        assertEquals("values:\n" +
                "  - aaa\n" +
                "  - bbb\n" +
                "  - ccc\n" +
                "  - ddd", yaml);

    }

    @Test
    public void tesDeSerializeValue() throws IOException {
        StringArrayTest.StringArray array = new StringArrayTest.StringArray();
        array.setValues(values);
        String yaml = mapper.write(array);
        assertArrayEquals(values, mapper.read(yaml).getValues());
    }


    @YAMLMapper
    public static class StringArray {

        private String[] values;

        public String[] getValues() {
            return values;
        }

        public void setValues(String[] values) {
            this.values = values;
        }
    }
}
