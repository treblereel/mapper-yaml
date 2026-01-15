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

import com.google.j2cl.junit.apt.J2clTestInput;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import static org.junit.Assert.assertEquals;

@J2clTestInput(DepartmentTest.class)
public class DepartmentTest {

  private static final DepartmentTest_Tested_YamlMapperImpl mapper =
      DepartmentTest_Tested_YamlMapperImpl.INSTANCE;

  private static final String YAML = """
department:
  name: HR
  code: D001
  location: New York
departments:
  - name: Finance
    code: D002
    location: London
  - name: IT
    code: D003
    location: San Francisco
departmentList:
  - name: Marketing
    code: D004
    location: Chicago
  - name: Sales
    code: D005
    location: Boston
""";

  @Test
  public void test() throws IOException {
    Tested tested = new Tested();
    tested.setDepartment(createDepartment("HR", "D001", "New York"));
    tested.setDepartments(
        new Department[] {
          createDepartment("Finance", "D002", "London"),
          createDepartment("IT", "D003", "San Francisco")
        });
    tested.setDepartmentList(
        List.of(
            createDepartment("Marketing", "D004", "Chicago"),
            createDepartment("Sales", "D005", "Boston")));
    String yaml = mapper.write(tested);
    System.out.println(yaml);

    assertEquals(YAML.trim(), yaml.trim());
    assertEquals(YAML.trim(), mapper.write(mapper.read(mapper.write(tested))).trim());



    System.out.println(yaml);
  }

  private Department createDepartment(String name, String code, String location) {
    Department department = Department.newInstance();
    department.setName(name);
    department.setCode(code);
    department.setLocation(location);
    return department;
  }

  @YAMLMapper
  public static class Tested {

    private Department department;
    private Department[] departments;
    private List<Department> departmentList;

    public Department getDepartment() {
      return department;
    }

    public void setDepartment(Department department) {
      this.department = department;
    }

    public Department[] getDepartments() {
      return departments;
    }

    public void setDepartments(Department[] departments) {
      this.departments = departments;
    }

    public List<Department> getDepartmentList() {
      return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
      this.departmentList = departmentList;
    }
  }
}
