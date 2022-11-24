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

package org.treblereel.gwt.yaml.tests.beans;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(PersonTest.class)
public class ArrayPersonTest {

  private static final String YAML =
      "values:"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA"
          + System.lineSeparator()
          + "    secondName: BBB"
          + System.lineSeparator()
          + "    age: 99"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC"
          + System.lineSeparator()
          + "      city: DDD"
          + System.lineSeparator()
          + "      street: EEE"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA2"
          + System.lineSeparator()
          + "    secondName: BBB2"
          + System.lineSeparator()
          + "    age: 992"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC2"
          + System.lineSeparator()
          + "      city: DDD2"
          + System.lineSeparator()
          + "      street: EEE2"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA3"
          + System.lineSeparator()
          + "    secondName: BBB3"
          + System.lineSeparator()
          + "    age: 993"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC3"
          + System.lineSeparator()
          + "      city: DDD3"
          + System.lineSeparator()
          + "      street: EEE3"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA4"
          + System.lineSeparator()
          + "    secondName: BBB4"
          + System.lineSeparator()
          + "    age: 994"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC4"
          + System.lineSeparator()
          + "      city: DDD4"
          + System.lineSeparator()
          + "      street: EEE4";

  private static final ArrayPersonTest_ArrayPersonBean_YAMLMapperImpl mapper =
      ArrayPersonTest_ArrayPersonBean_YAMLMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    ArrayPersonBean bean = genArrayPersonBean();
    String yaml = mapper.write(bean);
    assertEquals(YAML, yaml);
  }

  @Test
  public void testDeserializeValue() throws IOException {
    ArrayPersonBean temp = mapper.read(YAML);
    assertEquals(genArrayPersonBean(), temp);
    assertEquals(YAML, mapper.write(temp));
  }

  private ArrayPersonBean genArrayPersonBean() {
    Person test = new Person("AAA", "BBB", 99, new Address("CCC", "DDD", "EEE"));
    Person test2 = new Person("AAA2", "BBB2", 992, new Address("CCC2", "DDD2", "EEE2"));
    Person test3 = new Person("AAA3", "BBB3", 993, new Address("CCC3", "DDD3", "EEE3"));
    Person test4 = new Person("AAA4", "BBB4", 994, new Address("CCC4", "DDD4", "EEE4"));

    Person[] values = new Person[] {test, test2, test3, test4};

    ArrayPersonBean bean = new ArrayPersonBean();
    bean.setValues(values);

    return bean;
  }

  @YAMLMapper
  public static class ArrayPersonBean {

    private Person[] values;

    public Person[] getValues() {
      return values;
    }

    public void setValues(Person[] values) {
      this.values = values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ArrayPersonBean that = (ArrayPersonBean) o;
      return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(values);
    }
  }
}
