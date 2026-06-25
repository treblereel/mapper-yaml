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
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlCreator;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorWithEnumBeanTest.CreatorWithEnumBean;

@J2clTestInput(CreatorWithEnumBeanTest.class)
public class CreatorWithEnumBeanTest extends AbstractYamlTest<CreatorWithEnumBean> {

  private static final CreatorWithEnumBeanTest_CreatorWithEnumBean_YamlMapperImpl mapper =
      CreatorWithEnumBeanTest_CreatorWithEnumBean_YamlMapperImpl.INSTANCE;

  public CreatorWithEnumBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorWithEnumBean bean = new CreatorWithEnumBean("test", CreatorColor.GREEN);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testNullEnum() throws IOException {
    String yaml = "name: no color";
    CreatorWithEnumBean bean = mapper.read(yaml);
    assertEquals("no color", bean.getName());
    assertNull(bean.getColor());
  }

  @Test
  public void testAllEnumValues() throws IOException {
    for (CreatorColor color : CreatorColor.values()) {
      CreatorWithEnumBean bean = new CreatorWithEnumBean(color.name(), color);
      assertMarshallingAndUnmarshalling(bean);
    }
  }

  public enum CreatorColor {
    RED,
    GREEN,
    BLUE
  }

  @YAMLMapper
  public static class CreatorWithEnumBean {

    private final String name;
    private final CreatorColor color;

    @YamlCreator
    public CreatorWithEnumBean(
        @YamlProperty("name") String name, @YamlProperty("color") CreatorColor color) {
      this.name = name;
      this.color = color;
    }

    public String getName() {
      return name;
    }

    public CreatorColor getColor() {
      return color;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorWithEnumBean that = (CreatorWithEnumBean) o;
      return Objects.equals(name, that.name) && color == that.color;
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, color);
    }
  }
}
