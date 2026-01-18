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

import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import javax.lang.model.element.Element;

import org.treblereel.gwt.yaml.api.annotation.YamlSchema;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.yaml.schema.Parser;
import org.treblereel.yaml.schema.model.ArrayType;
import org.treblereel.yaml.schema.model.HasType;
import org.treblereel.yaml.schema.model.ObjectType;
import org.treblereel.yaml.schema.model.SchemaDefinition;

public class SchemaGenerator {

  private final GenerationContext context;
  private final ResourceOracle resourceOracle;
  private final SchemaBeanProcessor beanProcessor;

  public SchemaGenerator(GenerationContext context, TreeLogger logger) {
    this.context = context;
    this.resourceOracle = new ResourceOracleImpl(context);
    this.beanProcessor = new SchemaBeanProcessor(context, logger);
  }

  public void generate(Set<Element> annotatedElements) {
    Set<URI> processed = new HashSet<>();
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

                        if (processed.contains(resource.toURI())) {
                          throw new RuntimeException(
                                  "Duplicate schema location found: " + yamlSchema.schemaLocation());
                        } else {
                          processed.add(resource.toURI());
                          generate(resource.toURI(), yamlSchema);
                        }
                      } catch (Exception e) {
                        throw new RuntimeException(
                                "Unable to find resource: " + yamlSchema.schemaLocation(), e);
                      }
                    });
  }

  private void generate(URI uri, YamlSchema yamlSchema) {
    SchemaDefinition definition = new Parser().parse(uri);
    collectInnerTypes(yamlSchema, yamlSchema.defaultClassName(), definition.getObjectDefinition());
    checkAllOf(yamlSchema, definition);
    for (Map.Entry<String, ObjectType> entry : definition.getDefinitions().entrySet()) {
      String k = entry.getKey();
      ObjectType v = entry.getValue();
      collectInnerTypes(yamlSchema, k, v);
    }
  }

  private void collectInnerTypes(YamlSchema schema, String className, ObjectType objectType) {
    Stack<ObjectTypeHolder> stack = new Stack<>();
    stack.add(new ObjectTypeHolder(className, objectType));
    while (!stack.isEmpty()) {
      ObjectTypeHolder current = stack.pop();
      ObjectType inner = current.objectType;
      String name = current.name;
      beanProcessor.process(inner, name, schema.pkg());
      for (Map.Entry<String, HasType> kv : inner.properties().entrySet()) {
        HasType hasType = kv.getValue();
        if (hasType instanceof ObjectType objType) {
          String typeName = Character.toUpperCase(kv.getKey().charAt(0)) + kv.getKey().substring(1);
          stack.push(new ObjectTypeHolder(name + typeName, objType));
        } else if (hasType instanceof ArrayType arrayType) {
          if (arrayType.getItems() instanceof ObjectType objType) {
            String typeName = Character.toUpperCase(kv.getKey().charAt(0)) + kv.getKey().substring(1);
            stack.push(new ObjectTypeHolder(name + typeName, objType));
          }
        }
      }
    }
  }

  private void checkAllOf(YamlSchema yamlSchema, SchemaDefinition definition) {

  }

  private record ObjectTypeHolder(String name, ObjectType objectType) {
  }
}
