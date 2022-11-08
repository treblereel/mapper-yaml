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
import org.junit.Test;

@J2clTestInput(PersonTest.class)
public class PersonTest {

  private static final String YAML =
      "firstName: AAA\n"
          + "secondName: BBB\n"
          + "age: 99\n"
          + "address:\n"
          + "  country: CCC\n"
          + "  city: DDD\n"
          + "  street: EEE";

  private static final Person_YAMLMapperImpl mapper = Person_YAMLMapperImpl.INSTANCE;

  private static final Person test = new Person("AAA", "BBB", 99, new Address("CCC", "DDD", "EEE"));

  @Test
  public void testSerializeValue() throws IOException {
    assertEquals(YAML, mapper.write(test));
  }

  @Test
  public void testDeserializeValue() throws IOException {
    Person temp = mapper.read(YAML);
    assertEquals(test, temp);
    assertEquals(YAML, mapper.write(temp));
  }
}
