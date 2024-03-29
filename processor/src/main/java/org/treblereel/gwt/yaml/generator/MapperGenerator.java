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

package org.treblereel.gwt.yaml.generator;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.TypeParameter;
import com.google.auto.common.MoreElements;
import java.util.function.Supplier;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.api.AbstractObjectMapper;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.YamlTypeSerializerWrapper;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.definition.BeanDefinition;
import org.treblereel.gwt.yaml.deserializer.DeserializerGenerator;
import org.treblereel.gwt.yaml.logger.TreeLogger;
import org.treblereel.gwt.yaml.serializer.SerializerGenerator;

/** @author Dmitrii Tikhomirov Created by treblereel 3/20/20 */
public class MapperGenerator extends AbstractGenerator {

  private static final String MAPPER_IMPL = "_YamlMapperImpl";

  private final DeserializerGenerator deserializerGenerator;
  private final SerializerGenerator serializerGenerator;

  public MapperGenerator(GenerationContext context, TreeLogger logger) {
    super(context, logger);
    this.deserializerGenerator = new DeserializerGenerator(context, logger);
    this.serializerGenerator = new SerializerGenerator(context, logger);
  }

  @Override
  protected void configureClassType(BeanDefinition type) {
    cu.addImport(AbstractObjectMapper.class);
    cu.addImport(YAMLDeserializer.class);
    cu.addImport(YAMLSerializer.class);

    if (!type.getBean().getKind().equals(TypeKind.PACKAGE)) {
      cu.addImport(type.getQualifiedName());
    }

    setExtendedType(type);
  }

  private void setExtendedType(BeanDefinition type) {
    declaration
        .getExtendedTypes()
        .add(
            new ClassOrInterfaceType()
                .setName(AbstractObjectMapper.class.getSimpleName())
                .setTypeArguments(new ClassOrInterfaceType().setName(getTypeMapperName(type))));
  }

  private String getTypeMapperName(BeanDefinition type) {
    return type.getElement().getKind().isClass() ? type.getSimpleName() : "T";
  }

  @Override
  protected void init(BeanDefinition type) {
    if (type.getElement().getKind().isClass()) {
      if (!context.getTypeRegistry().containsDeserializer(type.getQualifiedName())) {
        serializerGenerator.generate(type);
      }
      if (!context.getTypeRegistry().containsSerializer(type.getQualifiedName())) {
        deserializerGenerator.generate(type);
      }
    }
    declaration.addFieldWithInitializer(
        new ClassOrInterfaceType().setName(getMapperName(type.getElement())),
        "INSTANCE",
        new ObjectCreationExpr()
            .setType(new ClassOrInterfaceType().setName(getMapperName(type.getElement()))),
        Modifier.Keyword.PUBLIC,
        Modifier.Keyword.STATIC,
        Modifier.Keyword.FINAL);
    addDeserializer(type);
    newSerializer(type);
  }

  private void newSerializer(BeanDefinition type) {
    ClassOrInterfaceType returnType =
        new ClassOrInterfaceType()
            .setName(YAMLSerializer.class.getSimpleName())
            .setTypeArguments(new ClassOrInterfaceType().setName(getTypeMapperName(type)));

    Supplier<Expression> supplier =
        () -> {
          Expression ser =
              addObjectCreationExpr(
                  type,
                  returnType,
                  new ObjectCreationExpr()
                      .setType(context.getTypeUtils().serializerName(getTypeName(type))));
          if (type.getElement().getAnnotation(YamlTypeSerializer.class) != null) {
            return new ObjectCreationExpr()
                .setType(YamlTypeSerializerWrapper.class.getCanonicalName())
                .addArgument(ser);
          }
          return ser;
        };
    declaration
        .addMethod("newSerializer", Modifier.Keyword.PROTECTED)
        .addAnnotation(Override.class)
        .setType(returnType)
        .getBody()
        .ifPresent(body -> body.addStatement(new ReturnStmt(supplier.get())));
  }

  private void addDeserializer(BeanDefinition type) {
    ClassOrInterfaceType returnType =
        new ClassOrInterfaceType()
            .setName(YAMLDeserializer.class.getSimpleName())
            .setTypeArguments(new ClassOrInterfaceType().setName(getTypeMapperName(type)));
    declaration
        .addMethod("newDeserializer", Modifier.Keyword.PROTECTED)
        .addAnnotation(Override.class)
        .setType(returnType)
        .getBody()
        .ifPresent(
            body ->
                body.addStatement(
                    new ReturnStmt(
                        addObjectCreationExpr(
                            type,
                            returnType,
                            new ObjectCreationExpr()
                                .setType(
                                    context.getTypeUtils().deserializerName(getTypeName(type)))))));
  }

  @Override
  protected String getMapperName(TypeElement type) {
    return (type.getEnclosingElement().getKind().equals(ElementKind.PACKAGE)
            ? ""
            : MoreElements.asType(type.getEnclosingElement()).getSimpleName().toString() + "_")
        + type.getSimpleName()
        + MAPPER_IMPL;
  }

  private Expression addObjectCreationExpr(
      BeanDefinition type, ClassOrInterfaceType returnType, ObjectCreationExpr creationExpr) {
    if (type.getElement().getKind().isClass()) {
      return creationExpr;
    }
    return new CastExpr().setType(returnType).setExpression(creationExpr);
  }

  private TypeMirror getTypeName(BeanDefinition type) {
    return type.getBean();
  }

  @Override
  protected void addTypeParam(BeanDefinition type, ClassOrInterfaceDeclaration declaration) {
    if (!type.getElement().getKind().isClass()) {
      declaration
          .getTypeParameters()
          .add(
              new TypeParameter()
                  .setName(new SimpleName("T extends " + type.getElement().getSimpleName())));
    }
  }
}
