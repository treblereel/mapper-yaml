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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableOverrideTest.OverrideBean;

@J2clTestInput(NillableOverrideTest.class)
public class NillableOverrideTest extends AbstractYamlTest<OverrideBean> {

  private static final NillableOverrideTest_OverrideBean_YamlMapperImpl mapper =
      NillableOverrideTest_OverrideBean_YamlMapperImpl.INSTANCE;

  public NillableOverrideTest() {
    super(mapper);
  }

  @Test
  public void testFieldOverridesClass() {
    OverrideBean bean = new OverrideBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("nillableField: ~"));
    assertFalse(result.contains("suppressedField"));
  }

  @Test
  public void testWithValues() throws IOException {
    OverrideBean bean = new OverrideBean();
    bean.setNillableField("hello");
    bean.setSuppressedField("world");
    assertMarshallingAndUnmarshalling(bean);
  }

  @YAMLMapper
  @YamlNillable
  public static class OverrideBean {

    private String nillableField;

    @YamlNillable(false)
    private String suppressedField;

    public String getNillableField() {
      return nillableField;
    }

    public void setNillableField(String nillableField) {
      this.nillableField = nillableField;
    }

    public String getSuppressedField() {
      return suppressedField;
    }

    public void setSuppressedField(String suppressedField) {
      this.suppressedField = suppressedField;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OverrideBean that = (OverrideBean) o;
      return Objects.equals(nillableField, that.nillableField)
          && Objects.equals(suppressedField, that.suppressedField);
    }

    @Override
    public int hashCode() {
      return Objects.hash(nillableField, suppressedField);
    }
  }
}
