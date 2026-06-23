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
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableEnumTest.EnumBean;

@J2clTestInput(NillableEnumTest.class)
public class NillableEnumTest extends AbstractYamlTest<EnumBean> {

  private static final NillableEnumTest_EnumBean_YamlMapperImpl mapper =
      NillableEnumTest_EnumBean_YamlMapperImpl.INSTANCE;

  public NillableEnumTest() {
    super(mapper);
  }

  @Test
  public void testNullEnum() {
    EnumBean bean = new EnumBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("color: ~"));
  }

  @Test
  public void testWithValue() throws IOException {
    EnumBean bean = new EnumBean();
    bean.setColor(Color.GREEN);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTripNull() throws IOException {
    EnumBean bean = new EnumBean();
    String yaml = mapper.write(bean);
    EnumBean from = mapper.read(yaml);
    assertNull(from.getColor());
  }

  public enum Color {
    RED,
    GREEN,
    BLUE
  }

  @YAMLMapper
  @YamlNillable
  public static class EnumBean {

    private Color color;

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EnumBean that = (EnumBean) o;
      return color == that.color;
    }

    @Override
    public int hashCode() {
      return Objects.hash(color);
    }
  }
}
