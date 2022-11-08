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

package org.treblereel.gwt.yaml.api;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import org.treblereel.gwt.jakarta.utils.GwtIncompatible;
import org.treblereel.gwt.yaml.api.internal.ser.bean.TypeSerializationInfo;

/**
 * This class includes parameters defined through properties annotations like YAMLFormat. They are
 * specific to one {@link YAMLSerializer} and that's why they are not contained inside {@link
 * YAMLSerializationContext}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
@GwtIncompatible
public final class ServerYAMLSerializerParameters implements YAMLSerializerParameters {

  /** Constant <code>DEFAULT</code> */
  public static final YAMLSerializerParameters DEFAULT = new ServerYAMLSerializerParameters();

  /**
   * Datatype-specific additional piece of configuration that may be used to further refine
   * formatting aspects. This may, for example, determine low-level format String used for {@link
   * java.util.Date} serialization; however, exact use is determined by specific {@link
   * YAMLSerializer}
   */
  private String pattern;

  /** Locale to use for serialization (if needed). */
  private String locale;

  /** Timezone to use for serialization (if needed). */
  private ZoneId timezone;

  /** Names of properties to ignore. */
  private Set<String> ignoredProperties;

  /** Bean type informations */
  private TypeSerializationInfo typeInfo;

  /** If true, all the properties of an object will be serialized inside the current object. */
  private boolean unwrapped = false;

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>pattern</code>.
   */
  @Override
  public String getPattern() {
    return pattern;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>pattern</code>.
   */
  @Override
  public YAMLSerializerParameters setPattern(String pattern) {
    this.pattern = pattern;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>locale</code>.
   */
  @Override
  public String getLocale() {
    return locale;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>locale</code>.
   */
  @Override
  public YAMLSerializerParameters setLocale(String locale) {
    this.locale = locale;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>timezone</code>.
   */
  @Override
  public ZoneId getTimezone() {
    return timezone;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>timezone</code>.
   */
  @Override
  public YAMLSerializerParameters setTimezone(Object timezone) {
    this.timezone = (ZoneId) timezone;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>ignoredProperties</code>.
   */
  @Override
  public Set<String> getIgnoredProperties() {
    return ignoredProperties;
  }

  /**
   * {@inheritDoc}
   *
   * <p>addIgnoredProperty
   */
  @Override
  public YAMLSerializerParameters addIgnoredProperty(String ignoredProperty) {
    if (null == ignoredProperties) {
      ignoredProperties = new HashSet<String>();
    }
    ignoredProperties.add(ignoredProperty);
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>typeInfo</code>.
   */
  @Override
  public TypeSerializationInfo getTypeInfo() {
    return typeInfo;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>typeInfo</code>.
   */
  @Override
  public YAMLSerializerParameters setTypeInfo(TypeSerializationInfo typeInfo) {
    this.typeInfo = typeInfo;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>isUnwrapped
   */
  @Override
  public boolean isUnwrapped() {
    return unwrapped;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>unwrapped</code>.
   */
  @Override
  public YAMLSerializerParameters setUnwrapped(boolean unwrapped) {
    this.unwrapped = unwrapped;
    return this;
  }

  @Override
  public String doubleValue(Double value) {
    return value.toString();
  }
}
