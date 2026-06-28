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
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableNestedBeanTest.OuterBean;

@J2clTestInput(NillableNestedBeanTest.class)
public class NillableNestedBeanTest extends AbstractYamlTest<OuterBean> {

  private static final NillableNestedBeanTest_OuterBean_YamlMapperImpl mapper =
      NillableNestedBeanTest_OuterBean_YamlMapperImpl.INSTANCE;

  public NillableNestedBeanTest() {
    super(mapper);
  }

  @Test
  public void testNestedBeanNull() {
    OuterBean bean = new OuterBean();
    bean.setName("outer");
    String result = mapper.write(bean);
    assertTrue(result.contains("name: outer"));
    assertTrue(result.contains("inner: ~"));
  }

  @Test
  public void testNestedBeanWithValue() throws IOException {
    OuterBean bean = new OuterBean();
    bean.setName("outer");
    InnerBean inner = new InnerBean();
    inner.setValue("innerVal");
    bean.setInner(inner);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTripNullNested() throws IOException {
    OuterBean bean = new OuterBean();
    bean.setName("outer");
    String yaml = mapper.write(bean);
    OuterBean from = mapper.read(yaml);
    assertNull(from.getInner());
  }

  @YAMLMapper
  @YamlNillable
  public static class OuterBean {

    private String name;
    private InnerBean inner;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public InnerBean getInner() {
      return inner;
    }

    public void setInner(InnerBean inner) {
      this.inner = inner;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OuterBean that = (OuterBean) o;
      return Objects.equals(name, that.name) && Objects.equals(inner, that.inner);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, inner);
    }
  }

  @YAMLMapper
  public static class InnerBean {

    private String value;

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      InnerBean that = (InnerBean) o;
      return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }
  }
}
