/*
 * Copyright © 2025 Treblereel
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
package org.treblereel.gwt.json.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.treblereel.gwt.json.generator.model.*;

public class JavaEmitter {

  private final Path outputDir;

  public JavaEmitter(Path outputDir) {
    this.outputDir = outputDir;
  }

  public void emit(GeneratorModel model) throws IOException {
    Set<String> packagesNeedingObjectSerializer = new java.util.HashSet<>();
    for (JavaClass jc : model.classes()) {
      writeFile(jc.packageName(), jc.className(), renderClass(jc));
      for (JavaEnum je : jc.enums()) {
        writeFile(je.packageName(), je.enumName(), renderEnum(je));
      }
      for (JavaField field : jc.fields()) {
        if (!field.oneOfTypes().isEmpty()) {
          String capName = NamingUtils.toPascalCase(field.fieldName());
          writeFile(
              jc.packageName(),
              capName + "Serializer",
              renderOneOfSerializer(jc.packageName(), field.fieldName(), field.oneOfTypes()));
          writeFile(
              jc.packageName(),
              capName + "Deserializer",
              renderOneOfDeserializer(jc.packageName(), field.fieldName(), field.oneOfTypes()));
        }
      }
      boolean hasObjectFields =
          jc.fields().stream()
              .anyMatch(
                  f ->
                      f.oneOfTypes().isEmpty()
                          && (f.javaType().equals("Object") || f.javaType().contains("<Object>")));
      if (hasObjectFields) {
        packagesNeedingObjectSerializer.add(jc.packageName());
      }
    }
    for (String pkg : packagesNeedingObjectSerializer) {
      writeFile(pkg, "ObjectYamlSerializer", renderObjectSerializer(pkg));
      writeFile(pkg, "ObjectYamlDeserializer", renderObjectDeserializer(pkg));
    }
    for (JavaEnum je : model.enums()) {
      writeFile(je.packageName(), je.enumName(), renderEnum(je));
    }
  }

  String renderClass(JavaClass jc) {
    Set<String> imports = collectImports(jc);
    StringBuilder sb = new StringBuilder();
    sb.append("package ").append(jc.packageName()).append(";\n\n");
    for (String imp : imports) {
      sb.append("import ").append(imp).append(";\n");
    }
    if (!imports.isEmpty()) sb.append("\n");
    sb.append("@YAMLMapper\n");
    sb.append("public class ").append(jc.className()).append(" {\n\n");
    for (JavaField field : jc.fields()) {
      if (field.needsYamlProperty()) {
        sb.append("    @YamlProperty(\"").append(field.schemaName()).append("\")\n");
      }
      if (!field.oneOfTypes().isEmpty()) {
        String capName = NamingUtils.toPascalCase(field.fieldName());
        sb.append("    @YamlTypeSerializer(").append(capName).append("Serializer.class)\n");
        sb.append("    @YamlTypeDeserializer(").append(capName).append("Deserializer.class)\n");
      } else if (field.javaType().equals("Object") || field.javaType().contains("<Object>")) {
        sb.append("    @YamlTypeSerializer(ObjectYamlSerializer.class)\n");
        sb.append("    @YamlTypeDeserializer(ObjectYamlDeserializer.class)\n");
      }
      sb.append("    private ")
          .append(field.javaType())
          .append(" ")
          .append(field.fieldName())
          .append(";\n\n");
    }
    for (JavaField field : jc.fields()) {
      String capName =
          Character.toUpperCase(field.fieldName().charAt(0)) + field.fieldName().substring(1);
      String getterPrefix = field.javaType().equals("boolean") ? "is" : "get";
      sb.append("    public ")
          .append(field.javaType())
          .append(" ")
          .append(getterPrefix)
          .append(capName)
          .append("() {\n");
      sb.append("        return ").append(field.fieldName()).append(";\n");
      sb.append("    }\n\n");
      sb.append("    public void set")
          .append(capName)
          .append("(")
          .append(field.javaType())
          .append(" ")
          .append(field.fieldName())
          .append(") {\n");
      sb.append("        this.")
          .append(field.fieldName())
          .append(" = ")
          .append(field.fieldName())
          .append(";\n");
      sb.append("    }\n\n");
    }
    sb.append("}\n");
    return sb.toString();
  }

  String renderEnum(JavaEnum je) {
    StringBuilder sb = new StringBuilder();
    sb.append("package ").append(je.packageName()).append(";\n\n");
    sb.append("public enum ").append(je.enumName()).append(" {\n");
    List<String> values = je.values();
    for (int i = 0; i < values.size(); i++) {
      sb.append("    ").append(values.get(i));
      sb.append(i < values.size() - 1 ? ",\n" : ";\n");
    }
    sb.append("}\n");
    return sb.toString();
  }

  String renderOneOfSerializer(String packageName, String fieldName, List<String> oneOfTypes) {
    String capName = NamingUtils.toPascalCase(fieldName);
    StringBuilder sb = new StringBuilder();
    sb.append("package ").append(packageName).append(";\n\n");
    sb.append("import org.treblereel.gwt.yaml.api.internal.ser.bean.YamlSubtypeSerializer;\n\n");
    sb.append("public class ")
        .append(capName)
        .append("Serializer extends YamlSubtypeSerializer<Object> {\n\n");
    sb.append("    public ").append(capName).append("Serializer() {\n");
    sb.append("        super(\"_type\"");
    for (String typeName : oneOfTypes) {
      sb.append(",\n            new Info(\"")
          .append(typeName)
          .append("\", ")
          .append(typeName)
          .append(".class, new ")
          .append(typeName)
          .append("_YamlSerializerImpl())");
    }
    sb.append("\n        );\n");
    sb.append("    }\n");
    sb.append("}\n");
    return sb.toString();
  }

  String renderOneOfDeserializer(String packageName, String fieldName, List<String> oneOfTypes) {
    String capName = NamingUtils.toPascalCase(fieldName);
    StringBuilder sb = new StringBuilder();
    sb.append("package ").append(packageName).append(";\n\n");
    sb.append(
        "import org.treblereel.gwt.yaml.api.internal.deser.bean.YamlSubtypeDeserializer;\n\n");
    sb.append("public class ")
        .append(capName)
        .append("Deserializer extends YamlSubtypeDeserializer<Object> {\n\n");
    sb.append("    public ").append(capName).append("Deserializer() {\n");
    sb.append("        super(\"_type\"");
    for (String typeName : oneOfTypes) {
      sb.append(",\n            new YamlSubtypeDeserializer.Info(\"")
          .append(typeName)
          .append("\", ")
          .append(typeName)
          .append(".class, new ")
          .append(typeName)
          .append("_YamlDeserializerImpl())");
    }
    sb.append("\n        );\n");
    sb.append("    }\n");
    sb.append("}\n");
    return sb.toString();
  }

  private Set<String> collectImports(JavaClass jc) {
    Set<String> imports = new LinkedHashSet<>();
    imports.add("org.treblereel.gwt.yaml.api.annotation.YAMLMapper");
    boolean needsYamlProperty = jc.fields().stream().anyMatch(JavaField::needsYamlProperty);
    if (needsYamlProperty) {
      imports.add("org.treblereel.gwt.yaml.api.annotation.YamlProperty");
    }
    boolean needsTypeSerializer =
        jc.fields().stream()
            .anyMatch(
                f ->
                    !f.oneOfTypes().isEmpty()
                        || f.javaType().equals("Object")
                        || f.javaType().contains("<Object>"));
    if (needsTypeSerializer) {
      imports.add("org.treblereel.gwt.yaml.api.annotation.YamlTypeSerializer");
      imports.add("org.treblereel.gwt.yaml.api.annotation.YamlTypeDeserializer");
    }

    boolean needsList = jc.fields().stream().anyMatch(f -> f.javaType().startsWith("List<"));
    if (needsList) {
      imports.add("java.util.List");
    }
    return imports;
  }

  String renderObjectSerializer(String packageName) {
    StringBuilder sb = new StringBuilder();
    sb.append("package ").append(packageName).append(";\n\n");
    sb.append("import org.treblereel.gwt.yaml.api.YAMLSerializer;\n");
    sb.append("import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;\n");
    sb.append("import org.treblereel.gwt.yaml.api.node.YamlMapping;\n");
    sb.append("import org.treblereel.gwt.yaml.api.node.YamlSequence;\n");
    sb.append("import org.treblereel.gwt.yaml.api.node.YamlNode;\n");
    sb.append("import org.treblereel.gwt.yaml.api.node.NodeType;\n\n");
    sb.append("public class ObjectYamlSerializer implements YAMLSerializer<Object> {\n\n");
    sb.append("    @Override\n");
    sb.append(
        "    public void serialize(YamlMapping writer, String propertyName, Object value, YAMLSerializationContext ctx) {\n");
    sb.append("        if (value == null) return;\n");
    sb.append("        if (value instanceof YamlNode node) {\n");
    sb.append("            writer.addNode(propertyName, node);\n");
    sb.append("        } else {\n");
    sb.append("            writer.addScalarNode(propertyName, String.valueOf(value));\n");
    sb.append("        }\n");
    sb.append("    }\n\n");
    sb.append("    @Override\n");
    sb.append(
        "    public void serialize(YamlSequence writer, Object value, YAMLSerializationContext ctx) {\n");
    sb.append("        if (value == null) return;\n");
    sb.append("        if (value instanceof YamlNode node) {\n");
    sb.append("            writer.addNode(node);\n");
    sb.append("        } else {\n");
    sb.append("            writer.addScalarNode(String.valueOf(value));\n");
    sb.append("        }\n");
    sb.append("    }\n");
    sb.append("}\n");
    return sb.toString();
  }

  String renderObjectDeserializer(String packageName) {
    return "package "
        + packageName
        + ";\n\n"
        + "import org.treblereel.gwt.yaml.api.YAMLDeserializer;\n"
        + "import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;\n"
        + "import org.treblereel.gwt.yaml.api.node.YamlMapping;\n"
        + "import org.treblereel.gwt.yaml.api.node.YamlNode;\n\n"
        + "public class ObjectYamlDeserializer implements YAMLDeserializer<Object> {\n\n"
        + "    @Override\n"
        + "    public Object deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {\n"
        + "        return yaml.getNode(key);\n"
        + "    }\n\n"
        + "    @Override\n"
        + "    public Object deserialize(YamlNode node, YAMLDeserializationContext ctx) {\n"
        + "        return node;\n"
        + "    }\n"
        + "}\n";
  }

  private void writeFile(String packageName, String typeName, String content) throws IOException {
    Path packageDir = outputDir.resolve(packageName.replace('.', '/'));
    Files.createDirectories(packageDir);
    Files.writeString(packageDir.resolve(typeName + ".java"), content);
  }
}
