//@formatter:off
/*
 * Copyright 2014 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.stream.impl;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * <p>DefaultYAMLWriter class.</p>
 * @author nicolasmorel
 * @version $Id: $
 */
public class DefaultYAMLWriter implements YAMLWriter {

    private YamlMappingBuilder writer = Yaml.createYamlMappingBuilder();

    private String deferredName;

    /**
     * Creates a new instance that writes a YAML-encoded stream to {@code out}.
     * @param out a {@link StringBuilder} object.
     */
    public DefaultYAMLWriter() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter beginObject(String name) {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter endObject() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter name(String name) {
        checkName(name);
        StringBuffer sb = new StringBuffer();
        sb.append('\"').append(name).append('\"');
        deferredName = sb.toString();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter unescapeName(String name) {
        checkName(name);
        deferredName = name;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter value(String value) {
        if (value == null) {
            return nullValue();
        }
        string(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter unescapeValue(String value) {
        if (value == null) {
            return nullValue();
        }
        StringBuffer sb = new StringBuffer();
        sb.append('\"').append(value).append('\"');
        value(sb.toString());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter nullValue() {
        return this;
    }

    @Override
    public YAMLWriter value(Integer value) {
        return value(value.toString());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLWriter value(Boolean value) {
        value(value ? "true" : "false");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLWriter value(Double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        value(Double.toString(value));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLWriter value(Long value) {
        value(Long.toString(value));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultYAMLWriter value(Number value) {
        if (value == null) {
            value(0);
            return this;
        }
        String string = value.toString();

        if (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN")) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }

        value(value.toString());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void flush() {
        //out.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        //out.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOutput() {
        return writer.build().toString();
    }

    @Override
    public void beginArray() {

    }

    @Override
    public void endArray() {

    }

    private void string(String value) {
        writer = writer.add(deferredName, value);
    }

    private void checkName(String name) {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
    }
}
//@formatter:on
