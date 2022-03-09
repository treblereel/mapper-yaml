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

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLContextProvider;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

/**
 * Base implementation of {@link YAMLDeserializer} for dates.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseDateYAMLDeserializer<D extends Date> extends YAMLDeserializer<D> {

    /**
     * {@inheritDoc}
     */
    @Override
    public D doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return doDeserialize(yaml.string(key), ctx, params);
    }

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
        public Date doDeserialize(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            if (date == null) {
                return null;
            }
            return new Date(Long.valueOf(date));
            //return YamlContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), params.getPattern(), null, date);
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
        public java.sql.Date doDeserialize(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            if (date == null) {
                return null;
            }
            return new java.sql.Date(Long.valueOf(date));
            //return new java.sql.Date(YamlContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), SQL_DATE_FORMAT, false, date).getTime());
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
        public Time doDeserialize(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            return Time.valueOf(date);
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
        public Timestamp doDeserialize(String date, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            return new Timestamp(YAMLContextProvider.get().dateFormat().parse(ctx.isUseBrowserTimezone(), params.getPattern(), null, date).getTime());
        }

    }
}
