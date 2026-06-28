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

package org.treblereel.gwt.yaml.tests.annotations.creator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlCreator;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorWithNestedBeanTest.CreatorWithNestedBean;

@J2clTestInput(CreatorWithNestedBeanTest.class)
public class CreatorWithNestedBeanTest extends AbstractYamlTest<CreatorWithNestedBean> {

  private static final CreatorWithNestedBeanTest_CreatorWithNestedBean_YamlMapperImpl mapper =
      CreatorWithNestedBeanTest_CreatorWithNestedBean_YamlMapperImpl.INSTANCE;

  public CreatorWithNestedBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorInnerBean inner = new CreatorInnerBean();
    inner.setValue("nested");
    inner.setNumber(42);
    CreatorWithNestedBean bean = new CreatorWithNestedBean("parent", inner);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testDeserialize() throws IOException {
    String yaml = "title: t\ninner:\n  value: v\n  number: 1";
    CreatorWithNestedBean bean = mapper.read(yaml);
    assertEquals("t", bean.getTitle());
    assertNotNull(bean.getInner());
    assertEquals("v", bean.getInner().getValue());
    assertEquals(Integer.valueOf(1), bean.getInner().getNumber());
  }

  @Test
  public void testNullNested() throws IOException {
    String yaml = "title: solo";
    CreatorWithNestedBean bean = mapper.read(yaml);
    assertEquals("solo", bean.getTitle());
    assertNull(bean.getInner());
  }

  @YAMLMapper
  public static class CreatorInnerBean {

    private String value;
    private Integer number;

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public Integer getNumber() {
      return number;
    }

    public void setNumber(Integer number) {
      this.number = number;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorInnerBean that = (CreatorInnerBean) o;
      return Objects.equals(value, that.value) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, number);
    }
  }

  @YAMLMapper
  public static class CreatorWithNestedBean {

    private final String title;
    private final CreatorInnerBean inner;

    @YamlCreator
    public CreatorWithNestedBean(
        @YamlProperty("title") String title, @YamlProperty("inner") CreatorInnerBean inner) {
      this.title = title;
      this.inner = inner;
    }

    public String getTitle() {
      return title;
    }

    public CreatorInnerBean getInner() {
      return inner;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorWithNestedBean that = (CreatorWithNestedBean) o;
      return Objects.equals(title, that.title) && Objects.equals(inner, that.inner);
    }

    @Override
    public int hashCode() {
      return Objects.hash(title, inner);
    }
  }
}
