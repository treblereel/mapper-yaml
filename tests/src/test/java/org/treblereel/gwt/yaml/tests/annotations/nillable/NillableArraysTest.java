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

package org.treblereel.gwt.yaml.tests.annotations.nillable;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableArraysTest.ArraysBean;

@J2clTestInput(NillableArraysTest.class)
public class NillableArraysTest extends AbstractYamlTest<ArraysBean> {

  private static final NillableArraysTest_ArraysBean_YamlMapperImpl mapper =
      NillableArraysTest_ArraysBean_YamlMapperImpl.INSTANCE;

  public NillableArraysTest() {
    super(mapper);
  }

  @Test
  public void testNullArrays() {
    ArraysBean bean = new ArraysBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("strings: ~"));
    assertTrue(result.contains("ints: ~"));
  }

  @Test
  public void testWithValues() throws IOException {
    ArraysBean bean = new ArraysBean();
    bean.setStrings(new String[] {"a", "b"});
    bean.setInts(new int[] {1, 2, 3});
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTripNulls() throws IOException {
    ArraysBean bean = new ArraysBean();
    String yaml = mapper.write(bean);
    ArraysBean from = mapper.read(yaml);
    assertNull(from.getStrings());
    assertNull(from.getInts());
  }

  @YAMLMapper
  @YamlNillable
  public static class ArraysBean {

    private String[] strings;
    private int[] ints;

    public String[] getStrings() {
      return strings;
    }

    public void setStrings(String[] strings) {
      this.strings = strings;
    }

    public int[] getInts() {
      return ints;
    }

    public void setInts(int[] ints) {
      this.ints = ints;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ArraysBean that = (ArraysBean) o;
      return Arrays.equals(strings, that.strings) && Arrays.equals(ints, that.ints);
    }

    @Override
    public int hashCode() {
      int result = Arrays.hashCode(strings);
      result = 31 * result + Arrays.hashCode(ints);
      return result;
    }
  }
}
