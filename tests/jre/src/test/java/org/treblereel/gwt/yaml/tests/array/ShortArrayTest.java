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
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(ShortArrayTest.class)
public class ShortArrayTest {

  private static final ShortArrayTest_ShortArray_YAMLMapperImpl mapper =
      ShortArrayTest_ShortArray_YAMLMapperImpl.INSTANCE;

  private static final short[] values = new short[] {17222, 2111, 32223, -6226};

  @Test
  public void testSerializeValue() throws IOException {
    ShortArrayTest.ShortArray array = new ShortArrayTest.ShortArray();
    array.setValues(values);
    String yaml = mapper.write(array);
    assertEquals(
        "values:"
            + System.lineSeparator()
            + "  - 17222"
            + System.lineSeparator()
            + "  - 2111"
            + System.lineSeparator()
            + "  - 32223"
            + System.lineSeparator()
            + "  - \"-6226\"",
        yaml);
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    ShortArrayTest.ShortArray array = new ShortArrayTest.ShortArray();
    array.setValues(values);
    String yaml = mapper.write(array);
    assertEquals(array, mapper.read(yaml));
  }

  @YAMLMapper
  public static class ShortArray {

    private short[] values;

    public short[] getValues() {
      return values;
    }

    public void setValues(short[] values) {
      this.values = values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ShortArray that = (ShortArray) o;
      return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(values);
    }
  }
}
