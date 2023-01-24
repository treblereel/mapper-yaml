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
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(StringTest.class)
public class StringTest {

  private static final String YAML_0 = "value: ~";
  private static final String YAML_1 = "value: testSerializeValue";

  private StringTest_StringType_YamlMapperImpl mapper =
      StringTest_StringType_YamlMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    StringTest.StringType test = new StringTest.StringType();
    test.setValue("testSerializeValue");
    assertEquals(YAML_1, mapper.write(test));
  }

  @Test
  public void testDeserializeValue() throws IOException {
    assertNull(mapper.read(YAML_0).getValue());
    assertEquals("testSerializeValue", mapper.read(YAML_1).getValue());
  }

  @YAMLMapper
  public static class StringType {

    private String value;

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
