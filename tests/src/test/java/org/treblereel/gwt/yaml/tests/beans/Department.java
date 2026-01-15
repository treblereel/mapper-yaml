/*
 * Copyright © 2026 Treblereel
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

package org.treblereel.gwt.yaml.tests.beans;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNewInstance;

@YAMLMapper(implementation = Department.DepartmentImpl.class)
public interface Department {

  String getName();

  void setName(String name);

  String getCode();

  void setCode(String code);

  String getLocation();

  void setLocation(String location);

  @YamlNewInstance
  static Department newInstance() {
    return new DepartmentImpl();
  }

  class DepartmentImpl implements Department {

    private String name;
    private String code;
    private String location;

    @Override
    public String getName() {
      return name;
    }

    @Override
    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String getCode() {
      return code;
    }

    @Override
    public void setCode(String code) {
      this.code = code;
    }

    @Override
    public String getLocation() {
      return location;
    }

    @Override
    public void setLocation(String location) {
      this.location = location;
    }
  }
}
