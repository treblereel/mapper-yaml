/*
 * Copyright © 2022 Treblereel
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

package org.treblereel.gwt.yaml.tests.primitive;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

/** @author Dmitrii Tikhomirov Created by treblereel 4/22/20 */
@J2clTestInput(FloatTest.class)
public class FloatTest {

  private static final String YAML_TAG_EMPTY = "value: 0";
  private static final String YAML_17222 = "value: 17222.01";
  private static final String YAML__17222 = "value: -17222.01";

  private FloatTest_FloatType_YamlMapperImpl mapper = FloatTest_FloatType_YamlMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    FloatType test = new FloatType();
    test.setValue(17222.01f);
    assertEquals(new Float(17222.01f), mapper.read(mapper.write(test)).value, 0.1);
    test.setValue(-17222.01f);
    assertEquals(new Float(-17222.01f), mapper.read(mapper.write(test)).value, 0.1);
    assertEquals(test, mapper.read(mapper.write(test)));
  }

  @Test
  public void testDeserializeValue() throws IOException {
    assertEquals(0.0, mapper.read(YAML_TAG_EMPTY).getValue(), 0.0);
    assertEquals(17222.01, mapper.read(YAML_17222).getValue(), 0.1);
    assertEquals(-17222.01, mapper.read(YAML__17222).getValue(), 0.1);
  }

  @YAMLMapper
  public static class FloatType {

    private float value;

    @Override
    public int hashCode() {
      return Objects.hash(getValue());
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof FloatType)) {
        return false;
      }
      FloatType type = (FloatType) o;
      return getValue() == type.getValue();
    }

    public float getValue() {
      return value;
    }

    public void setValue(float value) {
      this.value = value;
    }
  }
}
