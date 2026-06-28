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
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlCreator;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorMixedBeanTest.CreatorMixedBean;

@J2clTestInput(CreatorMixedBeanTest.class)
public class CreatorMixedBeanTest extends AbstractYamlTest<CreatorMixedBean> {

  private static final CreatorMixedBeanTest_CreatorMixedBean_YamlMapperImpl mapper =
      CreatorMixedBeanTest_CreatorMixedBean_YamlMapperImpl.INSTANCE;

  public CreatorMixedBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorMixedBean bean = new CreatorMixedBean("item", 1);
    bean.setDescription("a description");
    bean.setEnabled(true);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testCreatorFieldsOnly() throws IOException {
    String yaml = "name: test\nid: 99";
    CreatorMixedBean bean = mapper.read(yaml);
    assertEquals("test", bean.getName());
    assertEquals(99, bean.getId());
    assertNull(bean.getDescription());
    assertEquals(false, bean.isEnabled());
  }

  @Test
  public void testAllFields() throws IOException {
    String yaml = "name: full\nid: 7\ndescription: desc\nenabled: true";
    CreatorMixedBean bean = mapper.read(yaml);
    assertEquals("full", bean.getName());
    assertEquals(7, bean.getId());
    assertEquals("desc", bean.getDescription());
    assertTrue(bean.isEnabled());
  }

  @YAMLMapper
  public static class CreatorMixedBean {

    private final String name;
    private final int id;
    private String description;
    private boolean enabled;

    @YamlCreator
    public CreatorMixedBean(@YamlProperty("name") String name, @YamlProperty("id") int id) {
      this.name = name;
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public int getId() {
      return id;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorMixedBean that = (CreatorMixedBean) o;
      return id == that.id
          && enabled == that.enabled
          && Objects.equals(name, that.name)
          && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, id, description, enabled);
    }
  }
}
