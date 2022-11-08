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

package org.treblereel.gwt.yaml.api;

import java.util.Set;
import org.treblereel.gwt.yaml.api.internal.deser.bean.IdentityDeserializationInfo;
import org.treblereel.gwt.yaml.api.internal.deser.bean.TypeDeserializationInfo;

/**
 * YAMLDeserializerParameters interface.
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLDeserializerParameters {
  /**
   * getPattern.
   *
   * @return a {@link String} object.
   */
  String getPattern();

  /**
   * setPattern.
   *
   * @param pattern a {@link String} object.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters setPattern(String pattern);

  /**
   * getLocale.
   *
   * @return a {@link String} object.
   */
  String getLocale();

  /**
   * setLocale.
   *
   * @param locale a {@link String} object.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters setLocale(String locale);

  /**
   * getIgnoredProperties.
   *
   * @return a {@link Set} object.
   */
  Set<String> getIgnoredProperties();

  /**
   * addIgnoredProperty.
   *
   * @param ignoredProperty a {@link String} object.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters addIgnoredProperty(String ignoredProperty);

  /**
   * isIgnoreUnknown.
   *
   * @return a boolean.
   */
  boolean isIgnoreUnknown();

  /**
   * setIgnoreUnknown.
   *
   * @param ignoreUnknown a boolean.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters setIgnoreUnknown(boolean ignoreUnknown);

  /**
   * getIdentityInfo.
   *
   * @return a {@link IdentityDeserializationInfo} object.
   */
  IdentityDeserializationInfo getIdentityInfo();

  /**
   * setIdentityInfo.
   *
   * @param identityInfo a {@link IdentityDeserializationInfo} object.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters setIdentityInfo(IdentityDeserializationInfo identityInfo);

  /**
   * getTypeInfo.
   *
   * @return a {@link TypeDeserializationInfo} object.
   */
  TypeDeserializationInfo getTypeInfo();

  /**
   * setTypeInfo.
   *
   * @param typeInfo a {@link TypeDeserializationInfo} object.
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters setTypeInfo(TypeDeserializationInfo typeInfo);
}
