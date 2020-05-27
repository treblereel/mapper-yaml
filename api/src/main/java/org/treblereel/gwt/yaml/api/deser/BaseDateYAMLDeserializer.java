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

package org.treblereel.gwt.yaml.api.deser;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.treblereel.gwt.yaml.api.JacksonContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Base implementation of {@link YAMLDeserializer} for dates.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseDateYAMLDeserializer<D extends Date> extends YAMLDeserializer<D> {

    @Override
    public D deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
            YAMLDeserializationException {
        return deserializeString(value, ctx, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public D doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return deserializeNumber(reader.valueLong(), params);
    }

    /**
     * <p>deserializeNumber</p>
     * @param millis a long.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @return a D object.
     */
    protected abstract D deserializeNumber(long millis, YAMLDeserializerParameters params);

    /**
     * <p>deserializeString</p>
     * @param date a {@link java.lang.String} object.
     * @param ctx a {@link YAMLDeserializationContext} object.
     * @param params a {@link YAMLDeserializerParameters} object.
     * @return a D object.
     */
    protected abstract D deserializeString(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);

    /**
     * Default implementation of {@link BaseDateYAMLDeserializer} for {@link Date}
     */
    public static final class DateYAMLDeserializer extends BaseDateYAMLDeserializer<Date> {

        private static final DateYAMLDeserializer INSTANCE = new DateYAMLDeserializer();

        private DateYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link DateYAMLDeserializer}
         */
        public static DateYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        protected Date deserializeString(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            if (date == null) {
                return null;
            }
            return new Date(Long.valueOf(date));
            //return JacksonContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), params.getPattern(), null, date);
        }

        @Override
        protected Date deserializeNumber(long millis, YAMLDeserializerParameters params) {
            if (millis == 0) {
                return null;
            }
            return new Date(millis);
        }
    }

    /**
     * Default implementation of {@link BaseDateYAMLDeserializer} for {@link java.sql.Date}
     */
    public static final class SqlDateYAMLDeserializer extends BaseDateYAMLDeserializer<java.sql.Date> {

        private static final SqlDateYAMLDeserializer INSTANCE = new SqlDateYAMLDeserializer();

        private static final String SQL_DATE_FORMAT = "yyyy-MM-dd";

        private SqlDateYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link SqlDateYAMLDeserializer}
         */
        public static SqlDateYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        protected java.sql.Date deserializeString(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            if (date == null) {
                return null;
            }
            return new java.sql.Date(Long.valueOf(date));
            //return new java.sql.Date(JacksonContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), SQL_DATE_FORMAT, false, date).getTime());
        }

        @Override
        protected java.sql.Date deserializeNumber(long millis, YAMLDeserializerParameters params) {
            return new java.sql.Date(millis);
        }
    }

    /**
     * Default implementation of {@link BaseDateYAMLDeserializer} for {@link Time}
     */
    public static final class SqlTimeYAMLDeserializer extends BaseDateYAMLDeserializer<Time> {

        private static final SqlTimeYAMLDeserializer INSTANCE = new SqlTimeYAMLDeserializer();

        private SqlTimeYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link SqlTimeYAMLDeserializer}
         */
        public static SqlTimeYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        protected Time deserializeString(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            return Time.valueOf(date);
        }

        @Override
        protected Time deserializeNumber(long millis, YAMLDeserializerParameters params) {
            return new Time(millis);
        }
    }

    /**
     * Default implementation of {@link BaseDateYAMLDeserializer} for {@link Timestamp}
     */
    public static final class SqlTimestampYAMLDeserializer extends BaseDateYAMLDeserializer<Timestamp> {

        private static final SqlTimestampYAMLDeserializer INSTANCE = new SqlTimestampYAMLDeserializer();

        private SqlTimestampYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link SqlTimestampYAMLDeserializer}
         */
        public static SqlTimestampYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        protected Timestamp deserializeString(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            return new Timestamp(JacksonContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), params.getPattern(), null, date).getTime());
        }

        @Override
        protected Timestamp deserializeNumber(long millis, YAMLDeserializerParameters params) {
            return new Timestamp(millis);
        }
    }
}
