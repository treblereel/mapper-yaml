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

import static org.junit.Assert.assertFalse;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.packagelevel.PackageNillableOverrideTest.PkgOverrideBean;

@J2clTestInput(PackageNillableOverrideTest.class)
public class PackageNillableOverrideTest extends AbstractYamlTest<PkgOverrideBean> {

  private static final PackageNillableOverrideTest_PkgOverrideBean_YamlMapperImpl mapper =
      PackageNillableOverrideTest_PkgOverrideBean_YamlMapperImpl.INSTANCE;

  public PackageNillableOverrideTest() {
    super(mapper);
  }

  @Test
  public void testClassOverridesPackage() {
    PkgOverrideBean bean = new PkgOverrideBean();
    String result = mapper.write(bean);
    assertFalse(result.contains("name"));
    assertFalse(result.contains("~"));
  }

  @Test
  public void testWithValues() throws IOException {
    PkgOverrideBean bean = new PkgOverrideBean();
    bean.setName("hello");
    assertMarshallingAndUnmarshalling(bean);
  }

  @YAMLMapper
  @YamlNillable(false)
  public static class PkgOverrideBean {

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PkgOverrideBean that = (PkgOverrideBean) o;
      return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }
  }
}
