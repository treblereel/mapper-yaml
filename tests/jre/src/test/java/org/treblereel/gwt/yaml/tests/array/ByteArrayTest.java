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

@J2clTestInput(ByteArrayTest.class)
public class ByteArrayTest {
  private static final ByteArrayTest_ByteArray_YAMLMapperImpl mapper =
      ByteArrayTest_ByteArray_YAMLMapperImpl.INSTANCE;

  private static final byte[] values = new byte[] {17, 2, 33, 66};

  @Test
  public void testSerializeValue() throws IOException {
    ByteArrayTest.ByteArray array = new ByteArrayTest.ByteArray();
    array.setValues(values);
    String yaml = mapper.write(array);
    assertEquals("values: EQIhQg==", yaml);
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    ByteArrayTest.ByteArray array = new ByteArrayTest.ByteArray();
    array.setValues(values);
    String yaml = mapper.write(array);
    assertArrayEquals(values, mapper.read(yaml).getValues());
  }

  @YAMLMapper
  public static class ByteArray {

    private byte[] values;

    public byte[] getValues() {
      return values;
    }

    public void setValues(byte[] values) {
      this.values = values;
    }
  }
}
