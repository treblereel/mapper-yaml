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

package org.treblereel.gwt.yaml.tests.primitive;

import java.io.IOException;
import java.util.Objects;

import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/22/20
 */
//@J2clTestInput(CharTest.class) failed in htmlunit, works in browser
public class CharTest {
    private static final String YAML_0 = "value: \u0000";
    private static final String YAML_C = "value: c";

    private CharTest_CharType_YAMLMapperImpl mapper = CharTest_CharType_YAMLMapperImpl.INSTANCE;

    @Test
    public void testSerializeValue() throws IOException {
        CharType test = new CharType();
        //assertEquals(YAML_0, mapper.write(test));
        test.setValue('c');
        assertEquals(YAML_C, mapper.write(test));
    }

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals('c', mapper.read(YAML_C).getValue());
    }

    @YAMLMapper
    public static class CharType {

        private char value;

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CharType)) {
                return false;
            }
            CharType intType = (CharType) o;
            return getValue() == intType.getValue();
        }

        public char getValue() {
            return value;
        }

        public void setValue(char value) {
            this.value = value;
        }
    }
}
