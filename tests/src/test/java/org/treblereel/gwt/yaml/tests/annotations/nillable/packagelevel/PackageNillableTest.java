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

package org.treblereel.gwt.yaml.tests.annotations.nillable.packagelevel;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.packagelevel.PackageNillableTest.PkgBean;

@J2clTestInput(PackageNillableTest.class)
public class PackageNillableTest extends AbstractYamlTest<PkgBean> {

  private static final PackageNillableTest_PkgBean_YamlMapperImpl mapper =
      PackageNillableTest_PkgBean_YamlMapperImpl.INSTANCE;

  public PackageNillableTest() {
    super(mapper);
  }

  @Test
  public void testPackageLevelNillable() {
    PkgBean bean = new PkgBean();
    bean.setName("hello");
    String result = mapper.write(bean);
    assertTrue(result.contains("name: hello"));
    assertTrue(result.contains("description: ~"));
  }

  @Test
  public void testRoundTrip() throws IOException {
    PkgBean bean = new PkgBean();
    bean.setName("hello");
    String yaml = mapper.write(bean);
    PkgBean from = mapper.read(yaml);
    assertNull(from.getDescription());
  }

  @Test
  public void testWithValues() throws IOException {
    PkgBean bean = new PkgBean();
    bean.setName("hello");
    bean.setDescription("world");
    assertMarshallingAndUnmarshalling(bean);
  }

  @YAMLMapper
  public static class PkgBean {

    private String name;
    private String description;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PkgBean that = (PkgBean) o;
      return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, description);
    }
  }
}
