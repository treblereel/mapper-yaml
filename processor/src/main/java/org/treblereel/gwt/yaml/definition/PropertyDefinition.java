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
import com.github.javaparser.ast.expr.Expression;
import com.google.auto.common.MoreTypes;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.context.GenerationContext;

/** @author Dmitrii Tikhomirov Created by treblereel 4/1/20 */
public class PropertyDefinition extends Definition {

  private final VariableElement property;

  protected PropertyDefinition(VariableElement property, GenerationContext context) {
    super(property.asType(), context);
    this.property = property;
  }

  public Expression getFieldDeserializer(CompilationUnit cu) {
    TypeMirror bean = maybeInterface(context);
    FieldDefinition fieldDefinition =
        propertyDefinitionFactory.getFieldDefinition(bean != null ? bean : getBean());
    Expression result = fieldDefinition.getFieldDeserializer(cu);
    return result;
  }

  private TypeMirror maybeInterface(GenerationContext context) {
    if (!getBean().getKind().equals(TypeKind.ARRAY)
        && !getBean().getKind().isPrimitive()
        && MoreTypes.isType(getBean())) {
      if (MoreTypes.asElement(getBean()).getKind().isInterface()
          || (MoreTypes.asElement(getBean()).getKind().isClass()
              && MoreTypes.asElement(getBean()).getModifiers().contains(Modifier.ABSTRACT))) {
        return context.getBeans().stream()
            .filter(v -> v.getElement().equals(MoreTypes.asTypeElement(getBean())))
            .findFirst()
            .map(v -> v.getBean())
            .orElse(null);
      }
    }
    return null;
  }

  public Expression getFieldSerializer(CompilationUnit cu, GenerationContext context) {
    TypeMirror bean = maybeInterface(context);
    FieldDefinition fieldDefinition =
        propertyDefinitionFactory.getFieldDefinition(bean != null ? bean : getBean());
    return fieldDefinition.getFieldSerializer(getPropertyName(), cu);
  }

  public String getPropertyName() {
    return property.getSimpleName().toString();
  }

  public VariableElement getProperty() {
    return property;
  }
}
