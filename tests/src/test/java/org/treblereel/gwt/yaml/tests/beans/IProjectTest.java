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
import org.treblereel.gwt.yaml.api.annotation.YamlPropertyOrder;

import static org.junit.Assert.assertEquals;

@J2clTestInput(IProjectTest.class)
public class IProjectTest {

  private static final IProjectTest_Tested_YamlMapperImpl mapper =
      IProjectTest_Tested_YamlMapperImpl.INSTANCE;

  private static final String YAML = """
project:
  name: Project1
  description: Description1
  url: http://project1.com
  version: 1.0.0
  license: MIT
projects:
  - name: Project2
    description: Description2
    url: http://project2.com
    version: 2.0.0
    license: Apache-2.0
  - name: Project3
    description: Description3
    url: http://project3.com
    version: 3.0.0
    license: GPL-3.0
projectList:
  - name: Project4
    description: Description4
    url: http://project4.com
    version: 4.0.0
    license: BSD-3-Clause
  - name: Project5
    description: Description5
    url: http://project5.com
    version: 5.0.0
    license: LGPL-2.1
      """;

  @Test
  public void test() throws IOException {
    Tested tested = new Tested();
    tested.setProject(
        createIProject("Project1", "Description1", "http://project1.com", "1.0.0", "MIT"));
    IProject[] projects =
        new IProject[] {
          createIProject("Project2", "Description2", "http://project2.com", "2.0.0", "Apache-2.0"),
          createIProject("Project3", "Description3", "http://project3.com", "3.0.0", "GPL-3.0")
        };

    tested.setProjects(projects);
    List<IProject> projectList =
        List.of(
            createIProject(
                "Project4", "Description4", "http://project4.com", "4.0.0", "BSD-3-Clause"),
            createIProject("Project5", "Description5", "http://project5.com", "5.0.0", "LGPL-2.1"));

    tested.setProjectList(projectList);

    String yaml = mapper.write(tested);
    assertEquals(YAML.trim(), yaml.trim());
    assertEquals(YAML.trim(), mapper.write(mapper.read(mapper.write(tested))).trim());
  }

  private static IProject createIProject(
      String name, String description, String url, String version, String license) {
    IProject project = new IProject.IProjectImpl();
    project.setName(name);
    project.setDescription(description);
    project.setUrl(url);
    project.setVersion(version);
    project.setLicense(license);
    return project;
  }

  @YAMLMapper
  @YamlPropertyOrder({"project", "projects", "projectList"})
  public static class Tested {

    private IProject project;
    private IProject[] projects;
    private List<IProject> projectList;

    public IProject getProject() {
      return project;
    }

    public void setProject(IProject project) {
      this.project = project;
    }

    public IProject[] getProjects() {
      return projects;
    }

    public void setProjects(IProject[] projects) {
      this.projects = projects;
    }

    public List<IProject> getProjectList() {
      return projectList;
    }

    public void setProjectList(List<IProject> projectList) {
      this.projectList = projectList;
    }
  }
}
