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

package org.treblereel.gwt.yaml.tests.annotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlPropertyOrder;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.YamlPropertyOrderTest.ValueHolder;

@J2clTestInput(YamlPropertyOrderTest.class)
public class YamlPropertyOrderTest extends AbstractYamlTest<ValueHolder> {

  private static final YamlPropertyOrderTest_ValueHolder_YamlMapperImpl mapper =
      YamlPropertyOrderTest_ValueHolder_YamlMapperImpl.INSTANCE;

  public YamlPropertyOrderTest() {
    super(mapper);
  }

  @Test
  public void test() throws IOException {
    ValueHolder tested = new ValueHolder();
    assertEquals("", mapper.write(tested));
    assertNull(mapper.read(mapper.write(tested)));
  }

  @Test
  public void test2() throws IOException {
    ValueHolder tested = new ValueHolder("a", "b", "c", "d", "e", "f", "g");
    String resulted = mapper.write(tested);

    String expected =
        "value3: c"
            + "\n"
            + "value2: b"
            + "\n"
            + "value: a"
            + "\n"
            + "value4: d"
            + "\n"
            + "value5: e"
            + "\n"
            + "value6: f"
            + "\n"
            + "value7: g";

    assertEquals(expected, mapper.write(tested));
    assertEquals(tested, mapper.read(mapper.write(tested)));
  }

  @YAMLMapper
  @YamlPropertyOrder({"value3", "value2", "value1", "value"})
  public static class ValueHolder {
    private String value;

    private String value2;

    private String value3;

    private String value4;

    private String value5;

    private String value6;

    private String value7;

    public ValueHolder() {}

    public ValueHolder(
        String value,
        String value2,
        String value3,
        String value4,
        String value5,
        String value6,
        String value7) {
      this.value = value;
      this.value2 = value2;
      this.value3 = value3;
      this.value4 = value4;
      this.value5 = value5;
      this.value6 = value6;
      this.value7 = value7;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public String getValue2() {
      return value2;
    }

    public void setValue2(String value2) {
      this.value2 = value2;
    }

    public String getValue3() {
      return value3;
    }

    public void setValue3(String value3) {
      this.value3 = value3;
    }

    public String getValue4() {
      return value4;
    }

    public void setValue4(String value4) {
      this.value4 = value4;
    }

    public String getValue5() {
      return value5;
    }

    public void setValue5(String value5) {
      this.value5 = value5;
    }

    public String getValue6() {
      return value6;
    }

    public void setValue6(String value6) {
      this.value6 = value6;
    }

    public String getValue7() {
      return value7;
    }

    public void setValue7(String value7) {
      this.value7 = value7;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ValueHolder that = (ValueHolder) o;
      return Objects.equals(value, that.value)
          && Objects.equals(value2, that.value2)
          && Objects.equals(value3, that.value3)
          && Objects.equals(value4, that.value4)
          && Objects.equals(value5, that.value5)
          && Objects.equals(value6, that.value6)
          && Objects.equals(value7, that.value7);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, value2, value3, value4, value5, value6, value7);
    }

    @Override
    public String toString() {
      return "ValueHolder{"
          + "value='"
          + value
          + '\''
          + ", value2='"
          + value2
          + '\''
          + ", value3='"
          + value3
          + '\''
          + ", value4='"
          + value4
          + '\''
          + ", value5='"
          + value5
          + '\''
          + ", value6='"
          + value6
          + '\''
          + ", value7='"
          + value7
          + '\''
          + '}';
    }
  }
}
