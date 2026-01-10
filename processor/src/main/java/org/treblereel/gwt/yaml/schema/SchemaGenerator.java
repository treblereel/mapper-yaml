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

package org.treblereel.gwt.yaml.schema;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.lang.model.element.Element;
import org.treblereel.gwt.yaml.api.annotation.YamlSchema;
import org.treblereel.gwt.yaml.context.GenerationContext;

public class SchemaGenerator {

  private final GenerationContext context;

  private final ResourceOracle resourceOracle;

  public SchemaGenerator(GenerationContext context) {
    this.context = context;
    this.resourceOracle = new ResourceOracleImpl(context);
  }

  public void generate(Set<Element> annotatedElements) {
    Set<URL> resources = new HashSet<>();

    annotatedElements.stream()
        .forEach(
            element -> {
              YamlSchema yamlSchema = element.getAnnotation(YamlSchema.class);
              try {
                resources.add(resourceOracle.findResource(element, yamlSchema.schemaLocation()));

              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            });
  }

  private void generate(YamlSchema yamlSchema) {
    System.out.println(
        "Generating schema for: "
            + yamlSchema.schemaLocation()
            + " into package: "
            + yamlSchema.pkg());
  }
}
