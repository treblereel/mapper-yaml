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
@J2clTestInput(LongTest.class)
public class LongTest {

  private static final String YAML_0 = "value: 0";
  private static final String YAML_17222 = "value: 17222";
  private static final String YAML__17222 = "value: \"-17222\"";

  private LongTest_LongType_YamlMapperImpl mapper = LongTest_LongType_YamlMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    LongTest.LongType test = new LongTest.LongType();
    assertEquals(YAML_0, mapper.write(test));
    test.setValue(17222l);
    assertEquals(YAML_17222, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
    test.setValue(-17222l);
    assertEquals(YAML__17222, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
  }

  @Test
  public void testDeserializeValue() throws IOException {
    assertEquals(0, mapper.read(YAML_0).getValue());
    assertEquals(17222l, mapper.read(YAML_17222).getValue());
    assertEquals(-17222l, mapper.read(YAML__17222).getValue());
  }

  @YAMLMapper
  public static class LongType {

    private long value;

    @Override
    public int hashCode() {
      return Objects.hash(getValue());
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof LongType)) {
        return false;
      }
      LongType longType = (LongType) o;
      return getValue() == longType.getValue();
    }

    public long getValue() {
      return value;
    }

    public void setValue(long value) {
      this.value = value;
    }
  }
}
