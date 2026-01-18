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

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.treblereel.gwt.yaml.JavaKeywords;
import org.treblereel.gwt.yaml.api.HasAdditionalProperties;
import org.treblereel.gwt.yaml.api.annotation.YamlNewInstance;
import org.treblereel.gwt.yaml.api.internal.AdditionalProperties;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNode;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.exception.GenerationException;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.yaml.schema.model.ArrayType;
import org.treblereel.yaml.schema.model.BooleanType;
import org.treblereel.yaml.schema.model.HasType;
import org.treblereel.yaml.schema.model.IntegerType;
import org.treblereel.yaml.schema.model.NumberType;
import org.treblereel.yaml.schema.model.ObjectType;
import org.treblereel.yaml.schema.model.RefType;
import org.treblereel.yaml.schema.model.StringType;

class SchemaBeanProcessor {

  private final GenerationContext context;
  private final TreeLogger logger;
  private final BeanWriter beanWriter;


  SchemaBeanProcessor(GenerationContext context, TreeLogger logger) {
    this.context = context;
    this.logger = logger;
    this.beanWriter = new BeanWriter(context, logger);
  }

  void process(ObjectType objectType, String clazz, String packageName) {
    CompilationUnit compilationUnit = new CompilationUnit();
    compilationUnit.setPackageDeclaration(packageName);
    compilationUnit.addImport(YAMLMapper.class);
    compilationUnit.addImport(YamlNode.class);
    compilationUnit.addImport(YamlNewInstance.class);
    compilationUnit.addImport(HasAdditionalProperties.class);
    compilationUnit.addImport(AdditionalProperties.class);

    ClassOrInterfaceDeclaration outer = new InterfaceGenerator().process(objectType, compilationUnit, clazz, packageName);
    new ImplementationGenerator().process(objectType, outer, clazz, packageName);

    write(compilationUnit.toString(), clazz, packageName);
  }

  private void write(String sourceCode, String className, String packageName) {
    try {
      beanWriter.write(sourceCode, className, packageName);
    } catch (IOException e) {
      throw new GenerationException("Failed to write bean: " + className, e);
    }
  }

  private String getType(String parent, String propertyName, HasType property) {
    parent = Character.toUpperCase(parent.charAt(0)) + parent.substring(1);
    if (property instanceof RefType ref) {
      String typeName = ref.ref().substring(8); // remove "#/$defs/"
      return Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
    } else if (property instanceof ArrayType array) {
      HasType items = array.getItems();
      return "java.util.List<" + getType(parent, propertyName, items) + ">";
    } else if (property instanceof StringType) {
      return "String";
    } else if (property instanceof IntegerType) {
      return "Integer";
    } else if (property instanceof NumberType) {
      return "Double";
    } else if (property instanceof BooleanType) {
      return "Boolean";
    } else if (property instanceof ObjectType) {
      return parent + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
    }
    throw new GenerationException("Unsupported property type: " + property.getClass().getName());
  }

   private class InterfaceGenerator {

    private ClassOrInterfaceDeclaration process(ObjectType objectType, CompilationUnit compilationUnit, String clazz, String packageName) {
      String className = Character.toUpperCase(clazz.charAt(0)) + clazz.substring(1);
      ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass(className);
      classOrInterfaceDeclaration.setInterface(true);

      NormalAnnotationExpr annotation = new NormalAnnotationExpr();
      annotation.setName(new Name(YAMLMapper.class.getSimpleName()));
      annotation.addPair("implementation", className + "." + className + "Impl.class");
      classOrInterfaceDeclaration.addAnnotation(annotation);

      addYamlObjectAnnotation(classOrInterfaceDeclaration, objectType);


      Map<String, HasType> properties = new TreeMap<>(objectType.properties());
      properties.forEach(
              (name, property) ->
                      addGetterSetter(
                              classOrInterfaceDeclaration, name, getType(clazz, name, property)));

      addNewInstanceMethod(classOrInterfaceDeclaration, clazz);
      return classOrInterfaceDeclaration;
    }

     private void addYamlObjectAnnotation(ClassOrInterfaceDeclaration definition, ObjectType objectType) {
       NormalAnnotationExpr annotation = new NormalAnnotationExpr();
       annotation.setName(new Name(YamlNode.class.getSimpleName()));

       if (objectType.hasUnevaluatedProperties() && !objectType.unevaluatedProperties()) {
         annotation.addPair("unevaluatedProperties", new BooleanLiteralExpr(false));
       } else {
         ClassOrInterfaceType additionalProperties = new ClassOrInterfaceType(null, HasAdditionalProperties.class.getSimpleName());
         definition.addExtendedType(additionalProperties);
       }

       if (objectType.hasRequired() && !objectType.getRequired().isEmpty()) {
         StringBuilder requiredBuilder = new StringBuilder();
         requiredBuilder.append("{");
         String joined = objectType.getRequired().stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(", "));
         requiredBuilder.append(joined);
         requiredBuilder.append("}");
         annotation.addPair("required", requiredBuilder.toString());
       }

       definition.addAnnotation(annotation);
     }

     private void addGetterSetter(ClassOrInterfaceDeclaration declaration, String name, String type) {
       String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);
       name = JavaKeywords.KEYWORDS.contains(name) ? "_" + name : name;
       Type fieldType = StaticJavaParser.parseType(type);

       MethodDeclaration getter = declaration.addMethod("get" + capitalized, Modifier.Keyword.PUBLIC, Modifier.Keyword.ABSTRACT);
       getter.setType(fieldType);
       getter.setBody(null);


       MethodDeclaration setter = declaration.addMethod("set" + capitalized, Modifier.Keyword.PUBLIC, Modifier.Keyword.ABSTRACT);
       setter.setType("void");
       setter.addParameter(fieldType, name);
       setter.setBody(null);
     }

     private void addNewInstanceMethod(ClassOrInterfaceDeclaration declaration, String clazz) {
       String capitalized = Character.toUpperCase(clazz.charAt(0)) + clazz.substring(1);
       MethodDeclaration newInstance = declaration.addMethod("newInstance", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC);
       newInstance.addAnnotation(YamlNewInstance.class);
       newInstance.setType(new ClassOrInterfaceType().setName(capitalized));
       newInstance.setBody(StaticJavaParser.parseBlock("{ return new " + capitalized + "Impl(); }"));
     }
   }

   private class ImplementationGenerator {

     void process(ObjectType objectType, ClassOrInterfaceDeclaration outer, String clazz, String packageName) {
       String className = Character.toUpperCase(clazz.charAt(0)) + clazz.substring(1);

       ClassOrInterfaceDeclaration classOrInterfaceDeclaration = new ClassOrInterfaceDeclaration();
       classOrInterfaceDeclaration.setName(className + "Impl");
       classOrInterfaceDeclaration.getImplementedTypes().add(new ClassOrInterfaceType(null, className));
       outer.addMember(classOrInterfaceDeclaration);


       Map<String, HasType> properties = new TreeMap<>(objectType.properties());
       properties.forEach(
               (name, property) ->
                       addProperty(
                               classOrInterfaceDeclaration, name, getType(clazz, name, property)));
       properties.forEach(
               (name, property) ->
                       addGetterSetter(
                               classOrInterfaceDeclaration, name, getType(clazz, name, property)));
       maybeAddAdditionalProperties(classOrInterfaceDeclaration, objectType);
       maybeAddAllOf(classOrInterfaceDeclaration, objectType);
     }

     private void maybeAddAdditionalProperties(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ObjectType objectType) {
       if (!objectType.hasUnevaluatedProperties() || objectType.unevaluatedProperties()) {
         ClassOrInterfaceType additionalProperties = new ClassOrInterfaceType(null, AdditionalProperties.class.getSimpleName());
         classOrInterfaceDeclaration.addExtendedType(additionalProperties);
       }
     }

     private void addProperty(ClassOrInterfaceDeclaration declaration, String name, String type) {
       name = JavaKeywords.KEYWORDS.contains(name) ? "_" + name : name;
       declaration.addField(type, name, Modifier.Keyword.PRIVATE);
     }

     private void addGetterSetter(ClassOrInterfaceDeclaration declaration, String name, String type) {
       String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);
       name = JavaKeywords.KEYWORDS.contains(name) ? "_" + name : name;
       Type fieldType = StaticJavaParser.parseType(type);

       MethodDeclaration getter = declaration.addMethod("get" + capitalized, Modifier.Keyword.PUBLIC);
       getter.setType(fieldType);

       getter.setBody(StaticJavaParser.parseBlock("{ return this." + name + "; }"));

       MethodDeclaration setter = declaration.addMethod("set" + capitalized, Modifier.Keyword.PUBLIC);
       setter.setType("void");
       setter.addParameter(fieldType, name);
       setter.setBody(StaticJavaParser.parseBlock("{ this." + name + " = " + name + "; }"));
     }

     private void maybeAddAllOf(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ObjectType objectType) {
       if(objectType.hasAllOf() && !objectType.getAllOf().isEmpty()) {
         System.out.println("Bean " + objectType.getName());
         for (HasType hasType : objectType.getAllOf()) {
           String type = getType(objectType.getName(), "allOfProperty", hasType);
           String propertyName = Character.toLowerCase(type.charAt(0)) + type.substring(1);
           addProperty(classOrInterfaceDeclaration, propertyName, getType(objectType.getName(), propertyName, hasType));
         }
       }
     }

   }
}
