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

package org.treblereel.gwt.yaml.tests.annotations;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlTransient;

@J2clTestInput(YamlTransientTest.class)
public class YamlTransientTest {

  private static final YamlTransientTest_Tested_YAMLMapperImpl mapper =
      YamlTransientTest_Tested_YAMLMapperImpl.INSTANCE;

  @Test
  public void test() {
    Tested tested = new Tested();
    tested.setId("zzz");
    tested.setName("zzz1");
    tested.setAddress("zzz2");
    assertEquals("id: zzz", mapper.write(tested));
  }

  @YAMLMapper
  public static class Tested {

    private String id;
    private transient String name;

    @YamlTransient private String address;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
  }
}
