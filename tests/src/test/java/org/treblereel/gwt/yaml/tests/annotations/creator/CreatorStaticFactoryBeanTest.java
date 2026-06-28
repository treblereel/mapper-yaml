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

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlCreator;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorStaticFactoryBeanTest.CreatorStaticFactoryBean;

@J2clTestInput(CreatorStaticFactoryBeanTest.class)
public class CreatorStaticFactoryBeanTest extends AbstractYamlTest<CreatorStaticFactoryBean> {

  private static final CreatorStaticFactoryBeanTest_CreatorStaticFactoryBean_YamlMapperImpl mapper =
      CreatorStaticFactoryBeanTest_CreatorStaticFactoryBean_YamlMapperImpl.INSTANCE;

  public CreatorStaticFactoryBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorStaticFactoryBean bean = CreatorStaticFactoryBean.create("round", 55);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testDeserialize() throws IOException {
    String yaml = "name: factory\nvalue: 7";
    CreatorStaticFactoryBean bean = mapper.read(yaml);
    assertEquals("factory", bean.getName());
    assertEquals(7, bean.getValue());
  }

  @YAMLMapper
  public static class CreatorStaticFactoryBean {

    private final String name;
    private final int value;

    private CreatorStaticFactoryBean(String name, int value) {
      this.name = name;
      this.value = value;
    }

    @YamlCreator
    public static CreatorStaticFactoryBean create(
        @YamlProperty("name") String name, @YamlProperty("value") int value) {
      return new CreatorStaticFactoryBean(name, value);
    }

    public String getName() {
      return name;
    }

    public int getValue() {
      return value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorStaticFactoryBean that = (CreatorStaticFactoryBean) o;
      return value == that.value && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, value);
    }
  }
}
