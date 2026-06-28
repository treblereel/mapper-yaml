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
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorSimpleBeanTest.CreatorSimpleBean;

@J2clTestInput(CreatorSimpleBeanTest.class)
public class CreatorSimpleBeanTest extends AbstractYamlTest<CreatorSimpleBean> {

  private static final CreatorSimpleBeanTest_CreatorSimpleBean_YamlMapperImpl mapper =
      CreatorSimpleBeanTest_CreatorSimpleBean_YamlMapperImpl.INSTANCE;

  public CreatorSimpleBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorSimpleBean bean = new CreatorSimpleBean("Alice", 30);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testDeserialize() throws IOException {
    String yaml = "name: Bob\nage: 25";
    CreatorSimpleBean bean = mapper.read(yaml);
    assertEquals("Bob", bean.getName());
    assertEquals(25, bean.getAge());
  }

  @Test
  public void testDefaultAge() throws IOException {
    String yaml = "name: test";
    CreatorSimpleBean bean = mapper.read(yaml);
    assertEquals("test", bean.getName());
    assertEquals(0, bean.getAge());
  }

  @Test
  public void testNullName() throws IOException {
    String yaml = "age: 10";
    CreatorSimpleBean bean = mapper.read(yaml);
    assertNull(bean.getName());
    assertEquals(10, bean.getAge());
  }

  @YAMLMapper
  public static class CreatorSimpleBean {

    private final String name;
    private final int age;

    @YamlCreator
    public CreatorSimpleBean(@YamlProperty("name") String name, @YamlProperty("age") int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorSimpleBean that = (CreatorSimpleBean) o;
      return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, age);
    }
  }
}
