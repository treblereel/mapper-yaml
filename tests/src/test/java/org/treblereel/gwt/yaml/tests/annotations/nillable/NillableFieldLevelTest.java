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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableFieldLevelTest.FieldLevelBean;

@J2clTestInput(NillableFieldLevelTest.class)
public class NillableFieldLevelTest extends AbstractYamlTest<FieldLevelBean> {

  private static final NillableFieldLevelTest_FieldLevelBean_YamlMapperImpl mapper =
      NillableFieldLevelTest_FieldLevelBean_YamlMapperImpl.INSTANCE;

  public NillableFieldLevelTest() {
    super(mapper);
  }

  @Test
  public void testOnlyAnnotatedFieldSerializesNull() {
    FieldLevelBean bean = new FieldLevelBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("nillableField: ~"));
    assertFalse(result.contains("regularField"));
  }

  @Test
  public void testWithValues() throws IOException {
    FieldLevelBean bean = new FieldLevelBean();
    bean.setNillableField("hello");
    bean.setRegularField("world");
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTrip() throws IOException {
    FieldLevelBean bean = new FieldLevelBean();
    bean.setNillableField(null);
    bean.setRegularField("value");
    String yaml = mapper.write(bean);
    FieldLevelBean from = mapper.read(yaml);
    assertEquals("value", from.getRegularField());
    assertEquals(null, from.getNillableField());
  }

  @YAMLMapper
  public static class FieldLevelBean {

    @YamlNillable private String nillableField;

    private String regularField;

    public String getNillableField() {
      return nillableField;
    }

    public void setNillableField(String nillableField) {
      this.nillableField = nillableField;
    }

    public String getRegularField() {
      return regularField;
    }

    public void setRegularField(String regularField) {
      this.regularField = regularField;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      FieldLevelBean that = (FieldLevelBean) o;
      return Objects.equals(nillableField, that.nillableField)
          && Objects.equals(regularField, that.regularField);
    }

    @Override
    public int hashCode() {
      return Objects.hash(nillableField, regularField);
    }
  }
}
