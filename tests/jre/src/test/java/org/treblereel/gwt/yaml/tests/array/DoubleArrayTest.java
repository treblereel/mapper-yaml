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

@J2clTestInput(DoubleArrayTest.class)
public class DoubleArrayTest {

  private static final DoubleArrayTest_DoubleArray_YAMLMapperImpl mapper =
      DoubleArrayTest_DoubleArray_YAMLMapperImpl.INSTANCE;

  private static final double[] values = new double[] {17222.01, 2111.34, 32223.34, 6226.37};

  @Test
  public void testSerializeValue() throws IOException {
    DoubleArrayTest.DoubleArray array = new DoubleArrayTest.DoubleArray();
    array.setValues(values);
    String yaml = mapper.write(array);

    assertEquals(
        "values:\n" + "  - 17222.01\n" + "  - 2111.34\n" + "  - 32223.34\n" + "  - 6226.37", yaml);
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    DoubleArrayTest.DoubleArray array = new DoubleArrayTest.DoubleArray();
    array.setValues(values);
    String yaml = mapper.write(array);
    assertEquals(array, mapper.read(yaml));
  }

  @YAMLMapper
  public static class DoubleArray {

    private double[] values;

    public double[] getValues() {
      return values;
    }

    public void setValues(double[] values) {
      this.values = values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DoubleArray that = (DoubleArray) o;
      return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(values);
    }
  }
}
