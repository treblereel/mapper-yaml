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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(BooleanArrayTest.class)
public class BooleanArrayTest {

  private static final BooleanArrayTest_BooleanArray_YamlMapperImpl mapper =
      BooleanArrayTest_BooleanArray_YamlMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    BooleanArray booleanArray = new BooleanArray();
    booleanArray.setValues(new boolean[] {true, false, true, false});
    String yaml = mapper.write(booleanArray);

    assertEquals(
        "values:"
            + System.lineSeparator()
            + "  - true"
            + System.lineSeparator()
            + "  - false"
            + System.lineSeparator()
            + "  - true"
            + System.lineSeparator()
            + "  - false",
        yaml);
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    BooleanArray booleanArray = new BooleanArray();
    boolean[] value = new boolean[] {true, false, true, false};
    booleanArray.setValues(value);
    String yaml = mapper.write(booleanArray);
    assertArrayEquals(value, mapper.read(yaml).getValues());
  }

  @YAMLMapper
  public static class BooleanArray {

    private boolean[] values;

    public boolean[] getValues() {
      return values;
    }

    public void setValues(boolean[] values) {
      this.values = values;
    }
  }
}
