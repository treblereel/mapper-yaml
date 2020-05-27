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

import elemental2.core.JsNumber;
import jsinterop.base.JsPropertyMap;
import org.treblereel.gwt.yaml.api.ser.bean.TypeSerializationInfo;

/**
 * This class includes parameters defined through properties annotations like { YAMLFormat}. They are specific to one
 * {@link YAMLSerializer} and that's why they are not contained inside {@link YAMLSerializationContext}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public final class GwtJacksonYAMLSerializerParameters implements YAMLSerializerParameters {

    /**
     * Constant <code>DEFAULT</code>
     */
    public static final YAMLSerializerParameters DEFAULT = new GwtJacksonYAMLSerializerParameters();

    /**
     * Datatype-specific additional piece of configuration that may be used
     * to further refine formatting aspects. This may, for example, determine
     * low-level format String used for {@link java.util.Date} serialization;
     * however, exact use is determined by specific {@link YAMLSerializer}
     */
    private String pattern;

    /**
     * Locale to use for serialization (if needed).
     */
    private String locale;

    /**
     * Names of properties to ignore.
     */
    private Set<String> ignoredProperties;

    /**
     * Bean type informations
     */
    private TypeSerializationInfo typeInfo;

    /**
     * If true, all the properties of an object will be serialized inside the current object.
     */
    private boolean unwrapped = false;

    /**
     * {@inheritDoc}
     *
     * <p>Getter for the field <code>pattern</code>.</p>
     */
    @Override
    public String getPattern() {
        return pattern;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Setter for the field <code>pattern</code>.</p>
     */
    @Override
    public YAMLSerializerParameters setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Getter for the field <code>locale</code>.</p>
     */
    @Override
    public String getLocale() {
        return locale;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Setter for the field <code>locale</code>.</p>
     */
    @Override
    public YAMLSerializerParameters setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    @Override
    public Object getTimezone() {
        return null;
    }

    @Override
    public YAMLSerializerParameters setTimezone(Object timezone) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Getter for the field <code>ignoredProperties</code>.</p>
     */
    @Override
    public Set<String> getIgnoredProperties() {
        return ignoredProperties;
    }

    /**
     * {@inheritDoc}
     *
     * <p>addIgnoredProperty</p>
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
     * <p>Getter for the field <code>typeInfo</code>.</p>
     */
    @Override
    public TypeSerializationInfo getTypeInfo() {
        return typeInfo;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Setter for the field <code>typeInfo</code>.</p>
     */
    @Override
    public YAMLSerializerParameters setTypeInfo(TypeSerializationInfo typeInfo) {
        this.typeInfo = typeInfo;
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isUnwrapped</p>
     */
    @Override
    public boolean isUnwrapped() {
        return unwrapped;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Setter for the field <code>unwrapped</code>.</p>
     */
    @Override
    public YAMLSerializerParameters setUnwrapped(boolean unwrapped) {
        this.unwrapped = unwrapped;
        return this;
    }

    @Override
    public String doubleValue(Double value) {
        //TODO reuse options
        JsPropertyMap options = JsPropertyMap.of();
        options.set("useGrouping", false);
        options.set("minimumFractionDigits", 1);

        return new JsNumber(value).toLocaleString(JsNumber.ToLocaleStringLocalesUnionType.of("us"), options);
    }
}
