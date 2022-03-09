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
@J2clTestInput(DoubleTest.class)
public class DoubleTest {

    private static final String YAML_0 = "value: 0";
    private static final String YAML_17222 = "value: 17222.0";
    private static final String YAML__17222 = "value: \"-17222.0\"";

    private DoubleTest_DoubleType_YAMLMapperImpl mapper = DoubleTest_DoubleType_YAMLMapperImpl.INSTANCE;

    @Test
    public void testSerializeValue() throws IOException {
        DoubleType test = new DoubleType();
        assertEquals(new Double(0), mapper.read(mapper.write(test)).value, 0.1);
        test.setValue(17222);
        assertEquals(17222.02, mapper.read(mapper.write(test)).value, 0.1);
        test.setValue(-17222);
        assertEquals(-17222.02, mapper.read(mapper.write(test)).value, 0.1);
    }

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals(0.0, mapper.read(YAML_0).getValue(), 0.0);
        assertEquals(17222.0, mapper.read(YAML_17222).getValue(), 0.0);
        assertEquals(-17222.0, mapper.read(YAML__17222).getValue(), 0.0);
    }

    @YAMLMapper
    public static class DoubleType {

        private double value;

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof DoubleType)) {
                return false;
            }
            DoubleType type = (DoubleType) o;
            return getValue() == type.getValue();
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
