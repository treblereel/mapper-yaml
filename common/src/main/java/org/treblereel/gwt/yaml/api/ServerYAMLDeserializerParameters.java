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

import java.util.HashSet;
import java.util.Set;
import org.treblereel.gwt.jakarta.utils.GwtIncompatible;
import org.treblereel.gwt.yaml.api.internal.deser.bean.IdentityDeserializationInfo;
import org.treblereel.gwt.yaml.api.internal.deser.bean.TypeDeserializationInfo;

/**
 * This class includes parameters defined through properties annotations like {
 * YAMLIgnoreProperties}. They are specific to one {@link YAMLDeserializer} and that's why they are
 * not contained inside {@link YAMLDeserializationContext}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
@GwtIncompatible
public final class ServerYAMLDeserializerParameters implements YAMLDeserializerParameters {

  /** Constant <code>DEFAULT</code> */
  public static final YAMLDeserializerParameters DEFAULT = new ServerYAMLDeserializerParameters();

  /**
   * Datatype-specific additional piece of configuration that may be used to further refine
   * formatting aspects. This may, for example, determine low-level format String used for {@link
   * java.util.Date} serialization; however, exact use is determined by specific {@link
   * YAMLDeserializer}
   */
  private String pattern;

  /** Locale to use for deserialization (if needed). */
  private String locale;

  /** Names of properties to ignore. */
  private Set<String> ignoredProperties;

  /**
   * Property that defines whether it is ok to just ignore any unrecognized properties during
   * deserialization. If true, all properties that are unrecognized -- that is, there are no setters
   * or creators that accept them -- are ignored without warnings (although handlers for unknown
   * properties, if any, will still be called) without exception.
   */
  private boolean ignoreUnknown = false;

  /** Bean identity informations */
  private IdentityDeserializationInfo identityInfo;

  /** Bean type informations */
  private TypeDeserializationInfo typeInfo;

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
  public YAMLDeserializerParameters setPattern(String pattern) {
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
  public YAMLDeserializerParameters setLocale(String locale) {
    this.locale = locale;
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
  public YAMLDeserializerParameters addIgnoredProperty(String ignoredProperty) {
    if (null == ignoredProperties) {
      ignoredProperties = new HashSet<String>();
    }
    ignoredProperties.add(ignoredProperty);
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>isIgnoreUnknown
   */
  @Override
  public boolean isIgnoreUnknown() {
    return ignoreUnknown;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>ignoreUnknown</code>.
   */
  @Override
  public YAMLDeserializerParameters setIgnoreUnknown(boolean ignoreUnknown) {
    this.ignoreUnknown = ignoreUnknown;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>identityInfo</code>.
   */
  @Override
  public IdentityDeserializationInfo getIdentityInfo() {
    return identityInfo;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>identityInfo</code>.
   */
  @Override
  public YAMLDeserializerParameters setIdentityInfo(IdentityDeserializationInfo identityInfo) {
    this.identityInfo = identityInfo;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Getter for the field <code>typeInfo</code>.
   */
  @Override
  public TypeDeserializationInfo getTypeInfo() {
    return typeInfo;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Setter for the field <code>typeInfo</code>.
   */
  @Override
  public YAMLDeserializerParameters setTypeInfo(TypeDeserializationInfo typeInfo) {
    this.typeInfo = typeInfo;
    return this;
  }
}
