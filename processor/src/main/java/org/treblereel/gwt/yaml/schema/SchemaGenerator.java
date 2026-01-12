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

package org.treblereel.gwt.yaml.schema;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import org.treblereel.gwt.yaml.api.annotation.YamlSchema;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.exception.GenerationException;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.yaml.schema.Parser;
import org.treblereel.yaml.schema.model.ObjectType;
import org.treblereel.yaml.schema.model.SchemaDefinition;

public class SchemaGenerator {

  private final GenerationContext context;
  private final ResourceOracle resourceOracle;
  private final SchemaBeanProcessor beanProcessor;
  private final BeanWriter beanWriter;

  public SchemaGenerator(GenerationContext context, TreeLogger logger) {
    this.context = context;
    this.resourceOracle = new ResourceOracleImpl(context);
    this.beanProcessor = new SchemaBeanProcessor(context, logger);
    this.beanWriter = new BeanWriter(context, logger);
  }

  public void generate(Set<Element> annotatedElements) {
    Map<URI, String> schemaLocations = new HashMap<>();
    annotatedElements.stream()
        .forEach(
            element -> {
              YamlSchema yamlSchema = element.getAnnotation(YamlSchema.class);
              try {
                URL resource = resourceOracle.findResource(element, yamlSchema.schemaLocation());
                if (resource == null) {
                  throw new RuntimeException(
                      "Unable to find resource: " + yamlSchema.schemaLocation());
                }

                if (schemaLocations.containsKey(resource.toURI())) {
                  throw new RuntimeException(
                      "Duplicate schema location found: " + yamlSchema.schemaLocation());
                } else {
                  schemaLocations.put(resource.toURI(), yamlSchema.pkg());
                }
              } catch (Exception e) {
                throw new RuntimeException(
                    "Unable to find resource: " + yamlSchema.schemaLocation(), e);
              }
            });

    schemaLocations.forEach(this::generate);
  }

  private void generate(URI uri, String pkg) {
    SchemaDefinition definition = new Parser().parse(uri);
    for (Map.Entry<String, ObjectType> entry : definition.getDefinitions().entrySet()) {
      String k = entry.getKey();
      ObjectType v = entry.getValue();
      String sourceCode = beanProcessor.process(v, k, pkg);
      try {
        System.out.println("content of " + sourceCode);
        beanWriter.write(sourceCode, k, pkg);
      } catch (IOException e) {
        throw new GenerationException("Failed to write bean: " + k, e);
      }
    }
  }
}
