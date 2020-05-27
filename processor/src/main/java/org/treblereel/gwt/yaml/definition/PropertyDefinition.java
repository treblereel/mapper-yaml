package org.treblereel.gwt.yaml.definition;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Expression;
import com.google.auto.common.MoreTypes;
import org.treblereel.gwt.yaml.context.GenerationContext;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/1/20
 */
public class PropertyDefinition extends Definition {

    private final VariableElement property;

    protected PropertyDefinition(VariableElement property, GenerationContext context) {
        super(property.asType(), context);
        this.property = property;
    }

    public Expression getFieldDeserializer(CompilationUnit cu) {
        TypeMirror bean = maybeInterface(context);
        FieldDefinition fieldDefinition = propertyDefinitionFactory.getFieldDefinition(bean != null ? bean : getBean());
        Expression result = fieldDefinition.getFieldDeserializer(cu);
        return result;
    }

    private TypeMirror maybeInterface(GenerationContext context) {
        if (!getBean().getKind().equals(TypeKind.ARRAY) &&
                !getBean().getKind().isPrimitive() &&
                MoreTypes.isType(getBean())) {
            if (MoreTypes.asElement(getBean()).getKind().isInterface() ||
                    (MoreTypes.asElement(getBean()).getKind().isClass() &&
                            MoreTypes.asElement(getBean()).getModifiers().contains(Modifier.ABSTRACT))) {
                return context.getBeans().stream().filter(v -> v.getElement().equals(MoreTypes.asTypeElement(getBean()))).findFirst().map(v -> v.getBean()).orElse(null);
            }
        }
        return null;
    }

    public Expression getFieldSerializer(CompilationUnit cu, GenerationContext context) {
        TypeMirror bean = maybeInterface(context);
        FieldDefinition fieldDefinition = propertyDefinitionFactory.getFieldDefinition(bean != null ? bean : getBean());
        return fieldDefinition.getFieldSerializer(getPropertyName(), cu);
    }

    public String getPropertyName() {
        return property.getSimpleName().toString();
    }

    public VariableElement getProperty() {
        return property;
    }
}
