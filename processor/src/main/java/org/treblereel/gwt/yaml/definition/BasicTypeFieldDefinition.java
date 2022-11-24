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
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.context.GenerationContext;

/** @author Dmitrii Tikhomirov Created by treblereel 4/1/20 */
public class BasicTypeFieldDefinition extends FieldDefinition {

  protected BasicTypeFieldDefinition(TypeMirror property, GenerationContext context) {
    super(property, context);
  }

  @Override
  public Expression getFieldDeserializer(CompilationUnit cu) {
    return new FieldAccessExpr(
        new NameExpr(context.getTypeRegistry().getDeserializer(bean).toString()), "INSTANCE");
  }

  @Override
  public Expression getFieldSerializer(String fieldName, CompilationUnit cu) {
    ObjectCreationExpr expression =
        new ObjectCreationExpr()
            .setType(
                context
                    .getTypeRegistry()
                    .getSerializer(context.getProcessingEnv().getTypeUtils().erasure(getBean()))
                    .toString());
    return expression;
  }

  @Override
  public String toString() {
    return "BasicTypeFieldDefinition{" + "bean=" + bean + '}';
  }
}
