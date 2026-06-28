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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableCollectionsTest.CollectionsBean;

@J2clTestInput(NillableCollectionsTest.class)
public class NillableCollectionsTest extends AbstractYamlTest<CollectionsBean> {

  private static final NillableCollectionsTest_CollectionsBean_YamlMapperImpl mapper =
      NillableCollectionsTest_CollectionsBean_YamlMapperImpl.INSTANCE;

  public NillableCollectionsTest() {
    super(mapper);
  }

  @Test
  public void testAllNulls() {
    CollectionsBean bean = new CollectionsBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("names: ~"));
    assertTrue(result.contains("tags: ~"));
    assertTrue(result.contains("metadata: ~"));
  }

  @Test
  public void testWithValues() throws IOException {
    CollectionsBean bean = new CollectionsBean();
    bean.setNames(Arrays.asList("a", "b"));
    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTripNulls() throws IOException {
    CollectionsBean bean = new CollectionsBean();
    String yaml = mapper.write(bean);
    CollectionsBean from = mapper.read(yaml);
    assertNull(from.getNames());
    assertNull(from.getTags());
    assertNull(from.getMetadata());
  }

  @YAMLMapper
  @YamlNillable
  public static class CollectionsBean {

    private List<String> names;
    private Set<String> tags;
    private Map<String, String> metadata;

    public List<String> getNames() {
      return names;
    }

    public void setNames(List<String> names) {
      this.names = names;
    }

    public Set<String> getTags() {
      return tags;
    }

    public void setTags(Set<String> tags) {
      this.tags = tags;
    }

    public Map<String, String> getMetadata() {
      return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
      this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CollectionsBean that = (CollectionsBean) o;
      return Objects.equals(names, that.names)
          && Objects.equals(tags, that.tags)
          && Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
      return Objects.hash(names, tags, metadata);
    }
  }
}
