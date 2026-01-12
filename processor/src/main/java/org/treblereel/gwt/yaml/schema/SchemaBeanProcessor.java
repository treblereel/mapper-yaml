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
import com.github.javaparser.ast.type.Type;

import java.util.Map;
import java.util.TreeMap;

import org.treblereel.gwt.yaml.JavaKeywords;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.exception.GenerationException;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.yaml.schema.model.*;

class SchemaBeanProcessor {

    private final GenerationContext context;
    private final TreeLogger logger;

    SchemaBeanProcessor(GenerationContext context, TreeLogger logger) {
        this.context = context;
        this.logger = logger;
    }

    String process(ObjectType objectType, String clazz, String packageName) {
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.setPackageDeclaration(packageName);
        String className = Character.toUpperCase(clazz.charAt(0)) + clazz.substring(1);
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.addClass(className);
        classOrInterfaceDeclaration.addAnnotation(YAMLMapper.class);
        Map<String, HasType> properties = new TreeMap<>(objectType.properties());
        properties.forEach(
                (name, property) ->
                        addProperty(
                                classOrInterfaceDeclaration, name, getType(property)));
        properties.forEach(
                (name, property) ->
                        addGetterSetter(
                                classOrInterfaceDeclaration, name, getType(property)));
        maybeAddAdditionalProperties(classOrInterfaceDeclaration, objectType);
        return compilationUnit.toString();
    }

    private void maybeAddAdditionalProperties(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, ObjectType objectType) {
        throw new GenerationException("Additional properties are not supported yet.");
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

    private void addPropertyRefType(
            ClassOrInterfaceDeclaration declaration, String name, RefType property) {
        String typeName = property.ref().substring(8); // remove "#/$defs/"
        String capitalized = Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);

        System.out.println("Processing RefType property: " + name + " with ref: " + capitalized);
        addProperty(declaration, name, capitalized);
    }

    private String getType(HasType property) {
        if (property instanceof RefType ref) {
            String typeName = ref.ref().substring(8); // remove "#/$defs/"
            return Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
        } else if (property instanceof ArrayType array) {
            HasType items = array.getItems();
            return "List<" + getType(items) + ">";
        } else if (property instanceof StringType) {
            return "String";
        } else if (property instanceof IntegerType) {
            return "Integer";
        } else if (property instanceof NumberType) {
            return "Double";
        } else if (property instanceof BooleanType) {
            return "Boolean";
        } else if (property instanceof ObjectType obj) {
            //return "Object";
        }
        throw new GenerationException("Unsupported property type: " + property.getClass().getName());
    }
}
