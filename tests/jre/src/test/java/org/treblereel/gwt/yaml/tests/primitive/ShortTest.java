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

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/22/20
 */
@J2clTestInput(ShortTest.class)
public class ShortTest {

    private static final String YAML_0 = "check: 0";
    private static final String YAML_17222 = "check: 17222";
    private static final String YAML__17222 = "check: \"-17222\"";

    private ShortTest_ShortType_YAMLMapperImpl mapper = ShortTest_ShortType_YAMLMapperImpl.INSTANCE;

    @Test
    public void testSerializeValue() throws IOException {
        ShortType test = new ShortType();
        assertEquals(YAML_0, mapper.write(test));
        test.setCheck((short) 17222);
        assertEquals(YAML_17222, mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
        test.setCheck((short) -17222);
        assertEquals(YAML__17222, mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
    }

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals(0, mapper.read(YAML_0).getCheck());
        assertEquals(17222, mapper.read(YAML_17222).getCheck());
        assertEquals(-17222, mapper.read(YAML__17222).getCheck());
    }

    @YAMLMapper
    public static class ShortType {

        private short check;

        @Override
        public int hashCode() {
            return Objects.hash(getCheck());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ShortType)) {
                return false;
            }
            ShortType byteType = (ShortType) o;
            return getCheck() == byteType.getCheck();
        }

        public short getCheck() {
            return check;
        }

        public void setCheck(short check) {
            this.check = check;
        }
    }
}

