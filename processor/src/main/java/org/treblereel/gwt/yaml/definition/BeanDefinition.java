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

import com.google.auto.common.MoreTypes;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlPropertyOrder;
import org.treblereel.gwt.yaml.api.annotation.YamlTransient;
import org.treblereel.gwt.yaml.context.GenerationContext;
import org.treblereel.gwt.yaml.exception.GenerationException;

/** @author Dmitrii Tikhomirov Created by treblereel 4/1/20 */
public class BeanDefinition extends Definition {

  private final TypeElement element;
  private Set<PropertyDefinition> properties;

  public BeanDefinition(TypeElement element, GenerationContext context) {
    super(element.asType(), context);
    this.element = element;
  }

  private void loadProperties() {
    TypeElement current = getCurrentYamlBean();

    properties = new LinkedHashSet<>();
    Stream<VariableElement> asStream =
        context.getTypeUtils().getAllFieldsIn(current).stream()
            .filter(field -> !field.getModifiers().contains(Modifier.STATIC))
            .filter(field -> !field.getModifiers().contains(Modifier.FINAL))
            .filter(field -> !field.getModifiers().contains(Modifier.TRANSIENT))
            .filter(field -> field.getAnnotation(YamlTransient.class) == null);

    if (element.getAnnotation(YamlPropertyOrder.class) != null
        && element.getAnnotation(YamlPropertyOrder.class).value().length > 0) {
      String[] order = element.getAnnotation(YamlPropertyOrder.class).value();
      Map<String, PropertyDefinition> asMap =
          asStream.collect(
              Collectors.toMap(
                  variableElement -> variableElement.getSimpleName().toString(),
                  variableElement -> new PropertyDefinition(variableElement, context),
                  (o1, o2) -> o1,
                  java.util.LinkedHashMap::new));

      for (String s : order) {
        if (asMap.containsKey(s)) {
          properties.add(asMap.remove(s));
        }
      }
      properties.addAll(asMap.values());
    } else {
      asStream.map(field -> new PropertyDefinition(field, context)).forEach(properties::add);
    }
  }

  @Override
  public TypeMirror getBean() {
    return bean;
  }

  public TypeElement getElement() {
    return element;
  }

  public Set<PropertyDefinition> getFields() {
    if (properties == null) {
      loadProperties();
    }
    return properties;
  }

  public String getSimpleName() {
    return getElement().getSimpleName().toString();
  }

  public String getQualifiedName() {
    return getElement().getQualifiedName().toString();
  }

  private TypeElement getCurrentYamlBean() {
    if (element.getKind().isInterface() && element.getAnnotation(YAMLMapper.class) != null) {
      try {
        element.getAnnotation(YAMLMapper.class).implementation().getCanonicalName();
      } catch (MirroredTypeException e) {
        if (!Void.class.getCanonicalName().equals(e.getTypeMirror().toString())) {
          TypeMirror typeMirror = e.getTypeMirror();
          if (typeMirror.getKind() == TypeKind.DECLARED) {
            return MoreTypes.asTypeElement(typeMirror);
          } else {
            throw new GenerationException(
                "Cannot process YAMLMapper implementation for " + element.toString());
          }
        }
      }
    }
    return element;
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
    return "BeanDefinition{" + "element=" + element + '}';
  }
}
