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

package org.treblereel.gwt.yaml.tests.collection.set;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(StringSetTest.class)
public class StringSetTest {

  private static final StringSetTest_StringSetBeanTest_YAMLMapperImpl mapper =
      StringSetTest_StringSetBeanTest_YAMLMapperImpl.INSTANCE;
  private static final String YAML =
      "id: \"-1\""
          + System.lineSeparator()
          + "values:"
          + System.lineSeparator()
          + "  - AAA"
          + System.lineSeparator()
          + "  - AAA1"
          + System.lineSeparator()
          + "  - AAA2"
          + System.lineSeparator()
          + "  - AAA3"
          + System.lineSeparator()
          + "  - AAA4";

  @Test
  public void testSerializeValue() throws IOException {

    StringSetBeanTest bean = getListBeanTest();

    String yaml = mapper.write(bean);
    assertEquals(YAML, yaml);
  }

  private StringSetBeanTest getListBeanTest() {

    List<String> list = new ArrayList<>();
    StringSetBeanTest bean = new StringSetBeanTest();
    bean.setId(-1);
    bean.setValues(list);
    list.add("AAA");
    list.add("AAA1");
    list.add("AAA2");
    list.add("AAA3");
    list.add("AAA4");
    return bean;
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    StringSetBeanTest temp = mapper.read(YAML);
    assertEquals(getListBeanTest(), temp);
    assertEquals(YAML, mapper.write(temp));
  }

  @YAMLMapper
  public static class StringSetBeanTest {

    private int id;

    private List<String> values;

    public List<String> getValues() {
      return values;
    }

    public void setValues(List<String> values) {
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
      StringSetBeanTest that = (StringSetBeanTest) o;
      return id == that.id && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, values);
    }
  }
}
