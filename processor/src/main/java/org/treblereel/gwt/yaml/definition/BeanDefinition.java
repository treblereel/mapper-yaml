package org.treblereel.gwt.yaml.definition;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
//import javax.xml.bind.annotation.XmlTransient;

import org.treblereel.gwt.yaml.context.GenerationContext;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/1/20
 */
public class BeanDefinition extends Definition {

    private final TypeElement element;
    private Set<PropertyDefinition> properties;

    public BeanDefinition(TypeElement element, GenerationContext context) {
        super(element.asType(), context);
        this.element = element;

        loadProperties();
    }

    private void loadProperties() {
        properties = context.getTypeUtils().getAllFieldsIn(element)
                .stream()
                .filter(field -> !field.getModifiers().contains(Modifier.STATIC))
                .filter(field -> !field.getModifiers().contains(Modifier.FINAL))
                .filter(field -> !field.getModifiers().contains(Modifier.TRANSIENT))
                //.filter(field -> field.getAnnotation(XmlTransient.class) == null)
                .map(field -> new PropertyDefinition(field, context))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<PropertyDefinition> getFields() {
        if (properties == null) {
            getFields();
        }
        return properties;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeanDefinition)) {
            return false;
        }
        BeanDefinition that = (BeanDefinition) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "element=" + element +
                '}';
    }

    public String getRootElement() {
        return getElement().getSimpleName().toString();
    }

    public TypeElement getElement() {
        return element;
    }

    public String getSimpleName() {
        return getElement().getSimpleName().toString();
    }

    public String getQualifiedName() {
        return getElement().getQualifiedName().toString();
    }

    @Override
    public TypeMirror getBean() {
        return bean;
    }
}
