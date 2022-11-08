/*
 * Copyright 2013 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.internal.deser.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains type deserialization informations
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class TypeDeserializationInfo<T> {

  /** Name of the property containing information about the type */
  private final String propertyName;

  private final Map<String, Class<? extends T>> typeInfoToClass;

  /**
   * Constructor for TypeDeserializationInfo.
   *
   * @param propertyName a {@link java.lang.String} object.
   */
  public TypeDeserializationInfo(String propertyName) {
    this.propertyName = propertyName;
    this.typeInfoToClass = new HashMap<>();
  }

  /**
   * addTypeInfo
   *
   * @param clazz a {@link java.lang.Class} object.
   * @param typeInfo a {@link java.lang.String} object.
   * @return a {@link TypeDeserializationInfo} object.
   */
  public <S extends T> TypeDeserializationInfo<T> addTypeInfo(Class<S> clazz, String typeInfo) {
    typeInfoToClass.put(typeInfo, clazz);
    return this;
  }

  /**
   * Getter for the field <code>propertyName</code>.
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPropertyName() {
    return propertyName;
  }

  /**
   * getTypeClass
   *
   * @param typeInfo a {@link java.lang.String} object.
   * @return a {@link java.lang.Class} object.
   */
  public Class<? extends T> getTypeClass(String typeInfo) {
    return typeInfoToClass.get(typeInfo);
  }
}
