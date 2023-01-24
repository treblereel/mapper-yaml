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

package org.treblereel.gwt.yaml.tests.array;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.tests.enums.SimpleEnum;

@J2clTestInput(EnumArrayTest.class)
public class EnumArrayTest {

  private static final EnumArrayTest_EnumArrayBeanTest_YamlMapperImpl mapper =
      EnumArrayTest_EnumArrayBeanTest_YamlMapperImpl.INSTANCE;
  private static final String YAML =
      "id: \"-1\""
          + System.lineSeparator()
          + "values:"
          + System.lineSeparator()
          + "  - ONE"
          + System.lineSeparator()
          + "  - TWO"
          + System.lineSeparator()
          + "  - THREE"
          + System.lineSeparator()
          + "  - FOUR"
          + System.lineSeparator()
          + "  - FIVE";

  @Test
  public void testSerializeValue() throws IOException {
    EnumArrayBeanTest bean = getListBeanTest();
    String yaml = mapper.write(bean);
    assertEquals(YAML, yaml);
  }

  private EnumArrayBeanTest getListBeanTest() {
    SimpleEnum[] array =
        new SimpleEnum[] {
          SimpleEnum.ONE, SimpleEnum.TWO, SimpleEnum.THREE, SimpleEnum.FOUR, SimpleEnum.FIVE
        };
    EnumArrayBeanTest bean = new EnumArrayBeanTest();
    bean.setId(-1);
    bean.setValues(array);
    return bean;
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    EnumArrayBeanTest temp = mapper.read(YAML);
    assertEquals(getListBeanTest(), temp);
    assertEquals(YAML, mapper.write(temp));
  }

  @YAMLMapper
  public static class EnumArrayBeanTest {

    private int id;

    private SimpleEnum[] values;

    public SimpleEnum[] getValues() {
      return values;
    }

    public void setValues(SimpleEnum[] values) {
      this.values = values;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EnumArrayBeanTest that = (EnumArrayBeanTest) o;
      return id == that.id && Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      int result = Objects.hash(id);
      result = 31 * result + Arrays.hashCode(values);
      return result;
    }
  }
}
