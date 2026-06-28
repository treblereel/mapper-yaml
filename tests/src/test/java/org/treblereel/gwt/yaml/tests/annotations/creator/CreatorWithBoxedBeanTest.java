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
import org.treblereel.gwt.yaml.tests.annotations.creator.CreatorWithBoxedBeanTest.CreatorWithBoxedBean;

@J2clTestInput(CreatorWithBoxedBeanTest.class)
public class CreatorWithBoxedBeanTest extends AbstractYamlTest<CreatorWithBoxedBean> {

  private static final CreatorWithBoxedBeanTest_CreatorWithBoxedBean_YamlMapperImpl mapper =
      CreatorWithBoxedBeanTest_CreatorWithBoxedBean_YamlMapperImpl.INSTANCE;

  public CreatorWithBoxedBeanTest() {
    super(mapper);
  }

  @Test
  public void testRoundTrip() throws IOException {
    CreatorWithBoxedBean bean = new CreatorWithBoxedBean("test", 42, 3.14, true);
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testNullBoxedValues() throws IOException {
    String yaml = "label: only label";
    CreatorWithBoxedBean bean = mapper.read(yaml);
    assertEquals("only label", bean.getLabel());
    assertNull(bean.getCount());
    assertNull(bean.getScore());
    assertNull(bean.getActive());
  }

  @YAMLMapper
  public static class CreatorWithBoxedBean {

    private final String label;
    private final Integer count;
    private final Double score;
    private final Boolean active;

    @YamlCreator
    public CreatorWithBoxedBean(
        @YamlProperty("label") String label,
        @YamlProperty("count") Integer count,
        @YamlProperty("score") Double score,
        @YamlProperty("active") Boolean active) {
      this.label = label;
      this.count = count;
      this.score = score;
      this.active = active;
    }

    public String getLabel() {
      return label;
    }

    public Integer getCount() {
      return count;
    }

    public Double getScore() {
      return score;
    }

    public Boolean getActive() {
      return active;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CreatorWithBoxedBean that = (CreatorWithBoxedBean) o;
      return Objects.equals(label, that.label)
          && Objects.equals(count, that.count)
          && Objects.equals(score, that.score)
          && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
      return Objects.hash(label, count, score, active);
    }
  }
}
