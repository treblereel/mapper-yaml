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

package org.treblereel.gwt.yaml.definition;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.api.internal.deser.array.ArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.deser.array.dd.Array2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.internal.ser.array.ArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.internal.ser.array.dd.Array2dYAMLSerializer;
import org.treblereel.gwt.yaml.context.GenerationContext;

/** @author Dmitrii Tikhomirov Created by treblereel 4/1/20 */
public class ArrayBeanFieldDefinition extends FieldDefinition {

  protected ArrayBeanFieldDefinition(TypeMirror property, GenerationContext context) {
    super(property, context);
  }

  @Override
  public Expression getFieldDeserializer(CompilationUnit cu) {
    cu.addImport(ArrayYAMLDeserializer.ArrayCreator.class);
    cu.addImport(ArrayYAMLDeserializer.class);

    ArrayType array = (ArrayType) bean;
    String arrayType = array.getComponentType().toString();
    if (array.getComponentType().getKind().isPrimitive()) {
      arrayType =
          context
              .getProcessingEnv()
              .getTypeUtils()
              .boxedClass((PrimitiveType) array.getComponentType())
              .getSimpleName()
              .toString();
    } else if (array.getComponentType().getKind().equals(TypeKind.ARRAY)) {
      ArrayType array2d = (ArrayType) array.getComponentType();
      if (array2d.getComponentType().getKind().isPrimitive()) {
        arrayType =
            context
                    .getProcessingEnv()
                    .getTypeUtils()
                    .boxedClass((PrimitiveType) array2d.getComponentType())
                    .getSimpleName()
                    .toString()
                + "[]";
      } else {
        cu.addImport(Array2dYAMLDeserializer.class);
        cu.addImport(Array2dYAMLDeserializer.Array2dCreator.class);
        arrayType = array2d.getComponentType().toString();

        ClassOrInterfaceType typeOf =
            new ClassOrInterfaceType()
                .setName(Array2dYAMLDeserializer.Array2dCreator.class.getSimpleName())
                .setTypeArguments(new ClassOrInterfaceType().setName(arrayType));

        return new MethodCallExpr(
                new NameExpr(Array2dYAMLDeserializer.class.getSimpleName()), "newInstance")
            .addArgument(
                propertyDefinitionFactory
                    .getFieldDefinition(array2d.getComponentType())
                    .getFieldDeserializer(cu))
            .addArgument(
                new CastExpr()
                    .setType(typeOf)
                    .setExpression(
                        new NameExpr("(first, second) -> new " + arrayType + "[first][second]")));
      }
    }

    ClassOrInterfaceType typeOf =
        new ClassOrInterfaceType()
            .setName(ArrayYAMLDeserializer.ArrayCreator.class.getSimpleName())
            .setTypeArguments(new ClassOrInterfaceType().setName(arrayType));

    return new MethodCallExpr(
            new NameExpr(ArrayYAMLDeserializer.class.getSimpleName()), "newInstance")
        .addArgument(
            propertyDefinitionFactory
                .getFieldDefinition(array.getComponentType())
                .getFieldDeserializer(cu))
        .addArgument(
            new CastExpr().setType(typeOf).setExpression(new NameExpr(arrayType + "[]::new")));
  }

  @Override
  public Expression getFieldSerializer(String fieldName, CompilationUnit cu) {
    cu.addImport(ArrayYAMLSerializer.class);
    cu.addImport(Array2dYAMLSerializer.class);

    ArrayType array = (ArrayType) getBean();
    String serializer;
    Expression expression;
    if (array.getComponentType().getKind().equals(TypeKind.ARRAY)) {
      serializer = Array2dYAMLSerializer.class.getSimpleName();
      ArrayType array2d = (ArrayType) array.getComponentType();

      expression =
          propertyDefinitionFactory
              .getFieldDefinition(array2d.getComponentType())
              .getFieldSerializer(null, cu);
    } else {
      serializer = ArrayYAMLSerializer.class.getSimpleName();
      expression =
          propertyDefinitionFactory
              .getFieldDefinition((array.getComponentType()))
              .getFieldSerializer(null, cu);
    }

    return new ObjectCreationExpr()
        .setType(serializer)
        .addArgument(expression)
        .addArgument(new StringLiteralExpr(fieldName));
  }

  @Override
  public String toString() {
    return "ArrayBeanFieldDefinition{" + "bean=" + bean + '}';
  }
}
