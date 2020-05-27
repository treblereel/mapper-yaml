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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;
import org.treblereel.gwt.yaml.api.stream.impl.DefaultYAMLReader;

/**
 * Context for the deserialization process.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class DefaultYAMLDeserializationContext implements YAMLDeserializationContext {

    private static final Logger logger = Logger.getLogger("YAMLDeserialization");
    /*
     * Deserialization options
     */
    private final boolean failOnUnknownProperties;
    private final boolean acceptSingleValueAsArray;
    private final boolean wrapExceptions;
    private final boolean useSafeEval;
    private final boolean readUnknownEnumValuesAsNull;
    private final boolean useBrowserTimezone;
    private final boolean wrapCollections;

    private DefaultYAMLDeserializationContext(boolean failOnUnknownProperties, boolean acceptSingleValueAsArray,
                                             boolean wrapExceptions, boolean wrapCollections, boolean useSafeEval, boolean readUnknownEnumValuesAsNull,
                                             boolean useBrowserTimezone) {
        this.failOnUnknownProperties = failOnUnknownProperties;
        this.acceptSingleValueAsArray = acceptSingleValueAsArray;
        this.wrapExceptions = wrapExceptions;
        this.useSafeEval = useSafeEval;
        this.readUnknownEnumValuesAsNull = readUnknownEnumValuesAsNull;
        this.wrapCollections = wrapCollections;
        this.useBrowserTimezone = useBrowserTimezone;
    }

    /**
     * <p>builder</p>
     * @return a {@link DefaultYAMLDeserializationContext.Builder} object.
     */
    public static Builder builder() {
        return new DefaultBuilder();
    }

    /**
     * {@inheritDoc}
     *
     * <p>isFailOnUnknownProperties</p>
     * @see Builder#failOnUnknownProperties(boolean)
     */
    @Override
    public boolean isFailOnUnknownProperties() {
        return failOnUnknownProperties;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isAcceptSingleValueAsArray</p>
     * @see Builder#acceptSingleValueAsArray(boolean)
     */
    @Override
    public boolean isAcceptSingleValueAsArray() {
        return acceptSingleValueAsArray;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isUseSafeEval</p>
     * @see Builder#useSafeEval(boolean)
     */
    @Override
    public boolean isUseSafeEval() {
        return useSafeEval;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isReadUnknownEnumValuesAsNull</p>
     * @see Builder#readUnknownEnumValuesAsNull(boolean)
     */
    @Override
    public boolean isReadUnknownEnumValuesAsNull() {
        return readUnknownEnumValuesAsNull;
    }

    /**
     * {@inheritDoc}
     *
     * <p>isUseBrowserTimezone</p>
     * @see Builder#isUseBrowserTimezone()
     */
    @Override
    public boolean isUseBrowserTimezone() {
        return useBrowserTimezone;
    }

    /**
     * {@inheritDoc}
     *
     * <p>newYAMLReader</p>
     */
    @Override
    public YAMLReader newYAMLReader(String input) throws IOException {
        YAMLReader reader = new DefaultYAMLReader(input);
        return reader;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error with current reader state and returns a corresponding exception.
     */
    @Override
    public YAMLDeserializationException traceError(String message) {
        return traceError(message, null);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error with current reader state and returns a corresponding exception.
     */
    @Override
    public YAMLDeserializationException traceError(String message, YAMLReader reader) {
        getLogger().log(Level.SEVERE, message);
        traceReaderInfo(reader);
        return new YAMLDeserializationException(message);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error and returns a corresponding exception.
     */
    @Override
    public RuntimeException traceError(RuntimeException cause) {
        getLogger().log(Level.SEVERE, "Error during deserialization", cause);
        if (wrapExceptions) {
            return new YAMLDeserializationException(cause);
        } else {
            return cause;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Trace an error with current reader state and returns a corresponding exception.
     */
    @Override
    public RuntimeException traceError(RuntimeException cause, YAMLReader reader) {
        RuntimeException exception = traceError(cause);
        traceReaderInfo(reader);
        return exception;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLDeserializerParameters defaultParameters() {
        return JacksonContextProvider.get().defaultDeserializerParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger() {
        return logger;
    }

    /**
     * Trace the current reader state
     */
    private void traceReaderInfo(YAMLReader reader) {
        if (null != reader && getLogger().isLoggable(Level.INFO)) {
            getLogger().log(Level.INFO, "Error in input <" + reader.getInput() + ">");
        }
    }

    /**
     * Builder for {@link YAMLDeserializationContext}. To override default settings globally, you can extend this class, modify the
     * default settings inside the constructor and tell the compiler to use your builder instead in your gwt.yaml file :
     * <pre>
     * {@code
     *
     * <replace-with class="your.package.YourBuilder">
     *   <when-type-assignable class="org.dominokit.jacksonapt.YAMLDeserializationContext.Builder" />
     * </replace-with>
     *
     * }
     * </pre>
     */
    public static class Builder {

        protected boolean failOnUnknownProperties = true;

        protected boolean acceptSingleValueAsArray = false;

        protected boolean wrapExceptions = true;

        protected boolean useSafeEval = true;

        protected boolean readUnknownEnumValuesAsNull = false;

        protected boolean useBrowserTimezone = false;

        private boolean wrapCollections = true;

        private Builder() {
        }

        /**
         * Determines whether encountering of unknown
         * properties (ones that do not map to a property, and there is
         * no "any setter" or handler that can handle it)
         * should result in a failure (by throwing a
         * {@link YAMLDeserializationException}) or not.
         * This setting only takes effect after all other handling
         * methods for unknown properties have been tried, and
         * property remains unhandled.
         * <p>
         * Feature is enabled by default (meaning that a
         * {@link YAMLDeserializationException} will be thrown if an unknown property
         * is encountered).
         * </p>
         * @param failOnUnknownProperties true if should fail on unknown properties
         * @return the builder
         */
        public Builder failOnUnknownProperties(boolean failOnUnknownProperties) {
            this.failOnUnknownProperties = failOnUnknownProperties;
            return this;
        }

        /**
         * Feature that determines whether it is acceptable to coerce non-array
         * (in JSON) values to work with Java collection (arrays, java.util.Collection)
         * types. If enabled, collection deserializers will try to handle non-array
         * values as if they had "implicit" surrounding JSON array.
         * This feature is meant to be used for compatibility/interoperability reasons,
         * to work with packages (such as YAML-to-JSON converters) that leave out JSON
         * array in cases where there is just a single element in array.
         * <p>
         * Feature is disabled by default.
         * </p>
         * @param acceptSingleValueAsArray true if should acceptSingleValueAsArray
         * @return the builder
         */
        public Builder acceptSingleValueAsArray(boolean acceptSingleValueAsArray) {
            this.acceptSingleValueAsArray = acceptSingleValueAsArray;
            return this;
        }

        /**
         * Feature that determines whether gwt-jackson code should catch
         * and wrap {@link RuntimeException}s (but never {@link Error}s!)
         * to add additional information about
         * location (within input) of problem or not. If enabled,
         * exceptions will be caught and re-thrown; this can be
         * convenient both in that all exceptions will be checked and
         * declared, and so there is more contextual information.
         * However, sometimes calling application may just want "raw"
         * unchecked exceptions passed as is.
         * <br>
         * <br>
         * Feature is enabled by default.
         * @param wrapExceptions true if should wrapExceptions
         * @return the builder
         */
        public Builder wrapExceptions(boolean wrapExceptions) {
            this.wrapExceptions = wrapExceptions;
            return this;
        }

        /**
         * to deserialize JsType
         * <br>
         * <br>
         * @param useSafeEval true if should useSafeEval
         * @return the builder
         */
        public Builder useSafeEval(boolean useSafeEval) {
            this.useSafeEval = useSafeEval;
            return this;
        }

        /**
         * Feature that determines whether gwt-jackson should return null for unknown enum values.
         * Default is false which will throw {@link IllegalArgumentException} when unknown enum value is found.
         * @param readUnknownEnumValuesAsNull true if should readUnknownEnumValuesAsNull
         * @return the builder
         */
        public Builder readUnknownEnumValuesAsNull(boolean readUnknownEnumValuesAsNull) {
            this.readUnknownEnumValuesAsNull = readUnknownEnumValuesAsNull;
            return this;
        }

        public Builder wrapCollections(boolean wrapCollections) {
            this.wrapCollections = wrapCollections;
            return this;
        }

        /**
         * Feature that specifies whether dates that doesn't contain timezone information
         * are interpreted using the browser timezone or being relative to UTC (the default).
         * @param useBrowserTimezone true if should use browser timezone
         * @return the builder
         */
        public Builder useBrowserTimezone(boolean useBrowserTimezone) {
            this.useBrowserTimezone = useBrowserTimezone;
            return this;
        }

        public final YAMLDeserializationContext build() {
            return new DefaultYAMLDeserializationContext(failOnUnknownProperties, acceptSingleValueAsArray, wrapExceptions,
                                                        wrapCollections, useSafeEval, readUnknownEnumValuesAsNull, useBrowserTimezone);
        }
    }

    public static class DefaultBuilder extends Builder {

        private DefaultBuilder() {
        }
    }
}
