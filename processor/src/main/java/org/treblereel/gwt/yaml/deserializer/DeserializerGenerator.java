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

package org.treblereel.gwt.yaml.deserializer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.UnknownType;
import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.annotation.YamlProperty;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractBeanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.bean.AbstractCreatorBeanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.bean.BeanPropertyDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.bean.HasDeserializerAndParameters;
import org.treblereel.gwt.yaml.api.internal.deser.bean.Instance;
import org.treblereel.gwt.yaml.api.internal.deser.bean.InstanceBuilder;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.definition.BeanDefinition;
import org.treblereel.gwt.yaml.definition.PropertyDefinition;
import org.treblereel.gwt.yaml.generator.AbstractGenerator;
import org.treblereel.gwt.yaml.logger.TreeLogger;

/**
 * @author Dmitrii Tikhomirov Created by treblereel 3/18/20
 */
public class DeserializerGenerator extends AbstractGenerator {

  public DeserializerGenerator(GenerationContext context, TreeLogger logger) {
    super(context, logger.branch(TreeLogger.DEBUG, "Deserializers generation started"));
  }

  @Override
  protected String getMapperName(TypeElement type) {
    return context.getTypeUtils().deserializerName(type.asType());
  }

  @Override
  protected void configureClassType(BeanDefinition type) {
    cu.addImport(YAMLDeserializationContext.class);
    cu.addImport(YAMLDeserializer.class);
    cu.addImport(BeanPropertyDeserializer.class);
    cu.addImport(HasDeserializerAndParameters.class);
    cu.addImport(Instance.class);
    cu.addImport(Map.class);
    cu.addImport(HashMap.class);
    cu.addImport(InstanceBuilder.class);
    cu.addImport(YamlMapping.class);
    cu.addImport(type.getQualifiedName());

    ClassOrInterfaceType typeArg = new ClassOrInterfaceType().setName(type.getSimpleName());

    if (type.hasYamlCreator()) {
      cu.addImport(AbstractCreatorBeanYAMLDeserializer.class);
      declaration
          .getExtendedTypes()
          .add(
              new ClassOrInterfaceType()
                  .setName(AbstractCreatorBeanYAMLDeserializer.class.getSimpleName())
                  .setTypeArguments(typeArg));
    } else {
      cu.addImport(AbstractBeanYAMLDeserializer.class);
      declaration
          .getExtendedTypes()
          .add(
              new ClassOrInterfaceType()
                  .setName(AbstractBeanYAMLDeserializer.class.getSimpleName())
                  .setTypeArguments(typeArg));
    }
  }

  @Override
  protected void getType(BeanDefinition type) {
    declaration
        .addMethod("getDeserializedType", Modifier.Keyword.PUBLIC)
        .addAnnotation(Override.class)
        .setType(Class.class)
        .getBody()
        .ifPresent(
            body ->
                body.addStatement(
                    new ReturnStmt(
                        new FieldAccessExpr(new NameExpr(type.getSimpleName()), "class"))));
  }

  @Override
  protected void init(BeanDefinition beanDefinition) {
    logger.log(
        TreeLogger.DEBUG,
        "Generating " + context.getTypeUtils().deserializerName(beanDefinition.getBean()));

    if (beanDefinition.hasYamlCreator()) {
      initCreatorBean(beanDefinition);
    } else {
      initDeserializers(beanDefinition);
      initInstanceBuilder(beanDefinition);
    }
  }

  private void initDeserializers(BeanDefinition beanDefinition) {
    initDeserializers(beanDefinition, java.util.Collections.emptySet());
  }

  private void initDeserializers(BeanDefinition beanDefinition, Set<String> excludeNames) {
    MethodDeclaration initSerializers =
        declaration.addMethod("initDeserializers", Modifier.Keyword.PROTECTED);

    initSerializers
        .addAnnotation(Override.class)
        .setType(
            new ClassOrInterfaceType()
                .setName(Map.class.getSimpleName())
                .setTypeArguments(
                    new ClassOrInterfaceType().setName(String.class.getSimpleName()),
                    new ClassOrInterfaceType()
                        .setName(BeanPropertyDeserializer.class.getSimpleName())
                        .setTypeArguments(
                            new ClassOrInterfaceType()
                                .setName(beanDefinition.getElement().getSimpleName().toString()),
                            new ClassOrInterfaceType().setName("?"))));
    ClassOrInterfaceType varType =
        new ClassOrInterfaceType()
            .setName("Map")
            .setTypeArguments(
                new ClassOrInterfaceType().setName("String"),
                new ClassOrInterfaceType()
                    .setName("BeanPropertyDeserializer")
                    .setTypeArguments(
                        new ClassOrInterfaceType()
                            .setName(beanDefinition.getElement().getSimpleName().toString()),
                        new ClassOrInterfaceType().setName("?")));

    VariableDeclarator map = new VariableDeclarator();
    map.setType(varType);
    map.setName("map");
    map.setInitializer(new NameExpr("new HashMap<>()"));

    ExpressionStmt expressionStmt = new ExpressionStmt();
    VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr();
    variableDeclarationExpr.setModifiers(Modifier.Keyword.FINAL);
    expressionStmt.setExpression(variableDeclarationExpr);
    variableDeclarationExpr.getVariables().add(map);

    initSerializers
        .getBody()
        .ifPresent(
            body -> {
              body.addStatement(expressionStmt);
              beanDefinition.getFields().stream()
                  .filter(field -> !excludeNames.contains(field.getPropertyName()))
                  .forEach(
                      field ->
                          addBeanPropertyDeserializer(body, beanDefinition.getElement(), field));
              body.addStatement(new ReturnStmt("map"));
            });
  }

  private void initInstanceBuilder(BeanDefinition type) {
    MethodDeclaration initInstanceBuilder =
        declaration.addMethod("initInstanceBuilder", Modifier.Keyword.PROTECTED);
    initInstanceBuilder
        .addAnnotation(Override.class)
        .setType(
            new ClassOrInterfaceType()
                .setName(InstanceBuilder.class.getSimpleName())
                .setTypeArguments(new ClassOrInterfaceType().setName(type.getSimpleName())));
    VariableDeclarator deserializers = new VariableDeclarator();
    deserializers.setType("Map<String, HasDeserializerAndParameters>");
    deserializers.setName("deserializers");
    deserializers.setInitializer("null");

    ExpressionStmt expressionStmt = new ExpressionStmt();
    VariableDeclarationExpr variableDeclarationExpr = new VariableDeclarationExpr();
    variableDeclarationExpr.setModifiers(Modifier.Keyword.FINAL);
    expressionStmt.setExpression(variableDeclarationExpr);
    variableDeclarationExpr.getVariables().add(deserializers);

    initInstanceBuilder
        .getBody()
        .ifPresent(
            body -> {
              body.addStatement(variableDeclarationExpr);
              addInstanceBuilder(type, body);
            });
  }

  private void addBeanPropertyDeserializer(
      BlockStmt body, TypeElement type, PropertyDefinition field) {
    NodeList<BodyDeclaration<?>> anonymousClassBody = new NodeList<>();

    ClassOrInterfaceType typeArg = getWrappedType(field.getProperty());
    ClassOrInterfaceType beanPropertyDeserializer =
        new ClassOrInterfaceType().setName(BeanPropertyDeserializer.class.getSimpleName());
    beanPropertyDeserializer.setTypeArguments(
        new ClassOrInterfaceType().setName(type.getSimpleName().toString()), typeArg);

    body.addStatement(
        new MethodCallExpr(new NameExpr("map"), "put")
            .addArgument(new StringLiteralExpr(field.getPropertyName()))
            .addArgument(
                new ObjectCreationExpr()
                    .setType(beanPropertyDeserializer)
                    .setAnonymousClassBody(anonymousClassBody)));
    addNewDeserializer(field, anonymousClassBody);
    setValue(type, typeArg, field, anonymousClassBody);
  }

  private void addInstanceBuilder(BeanDefinition type, BlockStmt body) {
    ObjectCreationExpr instanceBuilder = new ObjectCreationExpr();
    ClassOrInterfaceType instanceBuilderType =
        new ClassOrInterfaceType()
            .setName(InstanceBuilder.class.getSimpleName())
            .setTypeArguments(new ClassOrInterfaceType().setName(type.getSimpleName()));

    instanceBuilder.setType(instanceBuilderType);
    NodeList<BodyDeclaration<?>> anonymousClassBody = new NodeList<>();
    instanceBuilder.setAnonymousClassBody(anonymousClassBody);

    newInstance(type, anonymousClassBody);
    getParametersDeserializer(anonymousClassBody);
    create(type, anonymousClassBody);

    body.addStatement(new ReturnStmt(instanceBuilder));
  }

  private ClassOrInterfaceType getWrappedType(Element field) {
    ClassOrInterfaceType typeArg =
        new ClassOrInterfaceType().setName(context.getTypeUtils().wrapperType(field.asType()));
    if (field.asType() instanceof DeclaredType) {
      if (!((DeclaredType) field.asType()).getTypeArguments().isEmpty()) {
        NodeList<Type> types = new NodeList<>();
        ((DeclaredType) field.asType())
            .getTypeArguments()
            .forEach(t -> types.add(new ClassOrInterfaceType().setName(t.toString())));
        typeArg.setTypeArguments(types);
      }
    }
    return typeArg;
  }

  private void addNewDeserializer(
      PropertyDefinition field, NodeList<BodyDeclaration<?>> anonymousClassBody) {
    MethodDeclaration method = new MethodDeclaration();
    method.setModifiers(Modifier.Keyword.PROTECTED);
    method.addAnnotation(Override.class);
    method.setName("newDeserializer");
    method.setType(new ClassOrInterfaceType().setName("YAMLDeserializer<?>"));

    method
        .getBody()
        .ifPresent(
            body ->
                body.addAndGetStatement(
                    new ReturnStmt().setExpression(field.getFieldDeserializer(cu))));
    anonymousClassBody.add(method);
  }

  private void setValue(
      TypeElement type,
      ClassOrInterfaceType fieldType,
      PropertyDefinition field,
      NodeList<BodyDeclaration<?>> anonymousClassBody) {
    MethodDeclaration method = new MethodDeclaration();
    method.setModifiers(Modifier.Keyword.PUBLIC);
    method.addAnnotation(Override.class);
    method.setName("setValue");
    method.setType("void");
    method.addParameter(type.getSimpleName().toString(), "bean");
    method.addParameter(fieldType, "value");
    method.addParameter(YAMLDeserializationContext.class.getSimpleName(), "ctx");

    method.getBody().ifPresent(body -> body.addAndGetStatement(getFieldAccessor(field)));
    anonymousClassBody.add(method);
  }

  private void newInstance(BeanDefinition type, NodeList<BodyDeclaration<?>> anonymousClassBody) {
    MethodDeclaration method = new MethodDeclaration();
    method.setModifiers(Modifier.Keyword.PUBLIC);
    method.addAnnotation(Override.class);
    method.setName("newInstance");
    method.setType(
        new ClassOrInterfaceType()
            .setName("Instance")
            .setTypeArguments(new ClassOrInterfaceType().setName(type.getSimpleName())));
    addParameter(method, "YAMLDeserializationContext", "ctx");

    ObjectCreationExpr instanceBuilder = new ObjectCreationExpr();
    ClassOrInterfaceType instanceBuilderType =
        new ClassOrInterfaceType()
            .setName(Instance.class.getSimpleName())
            .setTypeArguments(new ClassOrInterfaceType().setName(type.getSimpleName()));

    instanceBuilder.setType(instanceBuilderType);
    instanceBuilder.addArgument(new MethodCallExpr("create"));

    method
        .getBody()
        .ifPresent(
            body -> body.addAndGetStatement(new ReturnStmt().setExpression(instanceBuilder)));
    anonymousClassBody.add(method);
  }

  private void getParametersDeserializer(NodeList<BodyDeclaration<?>> anonymousClassBody) {
    MethodDeclaration method = new MethodDeclaration();
    method.setModifiers(Modifier.Keyword.PUBLIC);
    method.addAnnotation(Override.class);
    method.setName("getParametersDeserializer");
    method.setType(
        new ClassOrInterfaceType()
            .setName("Map")
            .setTypeArguments(
                new ClassOrInterfaceType().setName("String"),
                new ClassOrInterfaceType().setName("HasDeserializerAndParameters")));
    method
        .getBody()
        .ifPresent(
            body ->
                body.addAndGetStatement(
                    new ReturnStmt().setExpression(new NameExpr("deserializers"))));
    anonymousClassBody.add(method);
  }

  private void create(BeanDefinition type, NodeList<BodyDeclaration<?>> anonymousClassBody) {
    MethodDeclaration method = new MethodDeclaration();
    method.setModifiers(Modifier.Keyword.PRIVATE);
    method.setName("create");
    method.setType(new ClassOrInterfaceType().setName(type.getSimpleName()));

    ObjectCreationExpr instanceBuilder = new ObjectCreationExpr();
    ClassOrInterfaceType instanceBuilderType =
        new ClassOrInterfaceType().setName(type.getSimpleName());
    instanceBuilder.setType(instanceBuilderType);

    method
        .getBody()
        .ifPresent(
            body -> body.addAndGetStatement(new ReturnStmt().setExpression(instanceBuilder)));
    anonymousClassBody.add(method);
  }

  private void initCreatorBean(BeanDefinition type) {
    ExecutableElement creator = type.getYamlCreator();
    Set<String> creatorParamNames = type.getCreatorParameterNames();

    ConstructorDeclaration constructor = declaration.addConstructor(Modifier.Keyword.PUBLIC);
    BlockStmt constructorBody = constructor.getBody();

    for (VariableElement param : creator.getParameters()) {
      YamlProperty prop = param.getAnnotation(YamlProperty.class);
      String paramName =
          (prop != null && !prop.value().isEmpty())
              ? prop.value()
              : param.getSimpleName().toString();
      addCreatorParam(paramName, param.asType(), constructorBody);
    }

    initDeserializers(type, creatorParamNames);
    addCreateInstance(type, creator);
  }

  private void addCreatorParam(String paramName, TypeMirror paramType, BlockStmt body) {
    LambdaExpr lambda = new LambdaExpr();
    lambda.setEnclosingParameters(true);
    lambda.getParameters().add(new Parameter().setType(new UnknownType()).setName("yaml"));
    lambda.getParameters().add(new Parameter().setType(new UnknownType()).setName("ctx"));

    Expression deserExpr = getCreatorParamDeserExpr(paramName, paramType);
    lambda.setBody(new ExpressionStmt(deserExpr));

    body.addStatement(
        new MethodCallExpr(new NameExpr("creatorParams"), "put")
            .addArgument(new StringLiteralExpr(paramName))
            .addArgument(lambda));
  }

  private Expression getCreatorParamDeserExpr(String paramName, TypeMirror paramType) {
    TypeMirror erasedType = context.getProcessingEnv().getTypeUtils().erasure(paramType);

    if (paramType.getKind().isPrimitive() || context.getTypeUtils().isSimpleType(erasedType)) {
      TypeElement deser = context.getTypeRegistry().getDeserializer(paramType);
      return new MethodCallExpr(
              new ObjectCreationExpr().setType(deser.getQualifiedName().toString()), "deserialize")
          .addArgument(new NameExpr("yaml"))
          .addArgument(new StringLiteralExpr(paramName))
          .addArgument(new NameExpr("ctx"));
    } else if (MoreTypes.asElement(erasedType).getKind().equals(ElementKind.ENUM)) {
      cu.addImport(org.treblereel.gwt.yaml.api.internal.deser.EnumYAMLDeserializer.class);
      cu.addImport(MoreTypes.asTypeElement(erasedType).getQualifiedName().toString());
      MethodCallExpr enumDeser =
          new MethodCallExpr(new NameExpr("EnumYAMLDeserializer"), "newInstance")
              .addArgument(
                  MoreTypes.asTypeElement(erasedType).getSimpleName().toString() + ".class");
      for (Element enumConst : MoreTypes.asTypeElement(erasedType).getEnclosedElements()) {
        if (enumConst.getKind().equals(ElementKind.ENUM_CONSTANT)) {
          enumDeser.addArgument(erasedType.toString() + "." + enumConst);
        }
      }
      return new MethodCallExpr(enumDeser, "deserialize")
          .addArgument(new NameExpr("yaml"))
          .addArgument(new StringLiteralExpr(paramName))
          .addArgument(new NameExpr("ctx"));
    } else {
      String deser = context.getTypeUtils().canonicalDeserializerName(paramType);
      return new MethodCallExpr(
              new ObjectCreationExpr().setType(new ClassOrInterfaceType().setName(deser)),
              "deserialize")
          .addArgument(
              new MethodCallExpr(new NameExpr("yaml"), "getMappingNode")
                  .addArgument(new StringLiteralExpr(paramName)))
          .addArgument(new NameExpr("ctx"));
    }
  }

  private void addCreateInstance(BeanDefinition type, ExecutableElement creator) {
    MethodDeclaration method = declaration.addMethod("createInstance", Modifier.Keyword.PROTECTED);
    method.addAnnotation(Override.class);
    method.setType(new ClassOrInterfaceType().setName(type.getSimpleName()));
    method.addParameter(
        new ClassOrInterfaceType()
            .setName("Map")
            .setTypeArguments(
                new ClassOrInterfaceType().setName("String"),
                new ClassOrInterfaceType().setName("Object")),
        "params");

    String qualifiedName = type.getQualifiedName();

    Expression creatorCall;
    if (creator.getKind() == ElementKind.CONSTRUCTOR) {
      ObjectCreationExpr newExpr =
          new ObjectCreationExpr()
              .setType(new ClassOrInterfaceType().setName(type.getSimpleName()));
      for (VariableElement param : creator.getParameters()) {
        newExpr.addArgument(getCreateInstanceArgExpr(param));
      }
      creatorCall = newExpr;
    } else {
      MethodCallExpr factoryCall =
          new MethodCallExpr(new NameExpr(qualifiedName), creator.getSimpleName().toString());
      for (VariableElement param : creator.getParameters()) {
        factoryCall.addArgument(getCreateInstanceArgExpr(param));
      }
      creatorCall = factoryCall;
    }

    method.getBody().get().addStatement(new ReturnStmt(creatorCall));
  }

  private Expression getCreateInstanceArgExpr(VariableElement param) {
    YamlProperty prop = param.getAnnotation(YamlProperty.class);
    String paramName =
        (prop != null && !prop.value().isEmpty()) ? prop.value() : param.getSimpleName().toString();

    TypeMirror paramType = param.asType();
    Expression getCall =
        new MethodCallExpr(new NameExpr("params"), "get")
            .addArgument(new StringLiteralExpr(paramName));

    if (paramType.getKind().isPrimitive()) {
      Expression containsKey =
          new MethodCallExpr(new NameExpr("params"), "containsKey")
              .addArgument(new StringLiteralExpr(paramName));
      CastExpr cast =
          new CastExpr().setType(getBoxedType(paramType.getKind())).setExpression(getCall);
      return new ConditionalExpr(containsKey, cast, getDefaultValue(paramType.getKind()));
    } else {
      return new CastExpr()
          .setType(new ClassOrInterfaceType().setName(paramType.toString()))
          .setExpression(getCall);
    }
  }

  private ClassOrInterfaceType getBoxedType(TypeKind kind) {
    switch (kind) {
      case INT:
        return new ClassOrInterfaceType().setName("Integer");
      case LONG:
        return new ClassOrInterfaceType().setName("Long");
      case DOUBLE:
        return new ClassOrInterfaceType().setName("Double");
      case FLOAT:
        return new ClassOrInterfaceType().setName("Float");
      case BOOLEAN:
        return new ClassOrInterfaceType().setName("Boolean");
      case SHORT:
        return new ClassOrInterfaceType().setName("Short");
      case BYTE:
        return new ClassOrInterfaceType().setName("Byte");
      case CHAR:
        return new ClassOrInterfaceType().setName("Character");
      default:
        return new ClassOrInterfaceType().setName("Integer");
    }
  }

  private Expression getDefaultValue(TypeKind kind) {
    switch (kind) {
      case BOOLEAN:
        return new NameExpr("false");
      case LONG:
        return new NameExpr("0L");
      case DOUBLE:
        return new NameExpr("0.0");
      case FLOAT:
        return new NameExpr("0.0f");
      case CHAR:
        return new NameExpr("'\\0'");
      default:
        return new IntegerLiteralExpr("0");
    }
  }

  private Expression getFieldAccessor(PropertyDefinition field) {
    if (typeUtils.hasSetter(MoreElements.asVariable(field.getProperty()))) {
      return new MethodCallExpr(
              new NameExpr("bean"),
              typeUtils
                  .getSetter(MoreElements.asVariable(field.getProperty()))
                  .getSimpleName()
                  .toString())
          .addArgument("value");
    } else {
      return new AssignExpr()
          .setTarget(
              new FieldAccessExpr(
                  new NameExpr("bean"), field.getProperty().getSimpleName().toString()))
          .setValue(new NameExpr("value"));
    }
  }

  private void addParameter(MethodDeclaration method, String type, String name) {
    method.addParameter(new ClassOrInterfaceType().setName(type), name);
  }

  protected void write(TypeElement type) {
    if (type.getAnnotation(YamlTypeDeserializer.class) != null) {
      return;
    }
    super.write(type);
  }
}
