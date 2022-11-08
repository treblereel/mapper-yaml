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

package org.treblereel.gwt.yaml.api.internal.ser;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Base implementation of {@link YAMLSerializer} for dates.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseDateYAMLSerializer<D extends Date> extends YAMLSerializer<D> {

  /** {@inheritDoc} */
  @Override
  protected boolean isEmpty(D value) {
    return null == value || value.getTime() == 0l;
  }

  /** Default implementation of {@link BaseDateYAMLSerializer} for {@link Date} */
  public static final class DateYAMLSerializer extends BaseDateYAMLSerializer<Date> {

    private static final DateYAMLSerializer INSTANCE = new DateYAMLSerializer();

    private DateYAMLSerializer() {}

    /** @return an instance of {@link DateYAMLSerializer} */
    public static DateYAMLSerializer getInstance() {
      return INSTANCE;
    }

    @Override
    protected void doSerialize(
        YAMLWriter writer,
        Date value,
        YAMLSerializationContext ctx,
        YAMLSerializerParameters params) {
      if ((ctx.isWriteDatesAsTimestamps())) {
        writer.value(propertyName, String.valueOf(value.getTime()));
      } else {
        String date = value.toString(); // TODO use a better format
        writer.value(propertyName, date);
      }
    }
  }

  /** Default implementation of {@link BaseDateYAMLSerializer} for {@link java.sql.Date} */
  public static final class SqlDateYAMLSerializer extends BaseDateYAMLSerializer<java.sql.Date> {

    private static final SqlDateYAMLSerializer INSTANCE = new SqlDateYAMLSerializer();

    private SqlDateYAMLSerializer() {}

    /** @return an instance of {@link SqlDateYAMLSerializer} */
    public static SqlDateYAMLSerializer getInstance() {
      return INSTANCE;
    }

    @Override
    protected void doSerialize(
        YAMLWriter writer,
        java.sql.Date value,
        YAMLSerializationContext ctx,
        YAMLSerializerParameters params) {
      writer.value(propertyName, value.toString());
    }
  }

  /** Default implementation of {@link BaseDateYAMLSerializer} for {@link Date} */
  public static final class SqlTimeYAMLSerializer extends BaseDateYAMLSerializer<Time> {

    private static final SqlTimeYAMLSerializer INSTANCE = new SqlTimeYAMLSerializer();

    private SqlTimeYAMLSerializer() {}

    /** @return an instance of {@link SqlTimeYAMLSerializer} */
    public static SqlTimeYAMLSerializer getInstance() {
      return INSTANCE;
    }

    @Override
    protected void doSerialize(
        YAMLWriter writer,
        Time value,
        YAMLSerializationContext ctx,
        YAMLSerializerParameters params) {
      writer.value(propertyName, value.toString());
    }
  }

  /** Default implementation of {@link BaseDateYAMLSerializer} for {@link Timestamp} */
  public static final class SqlTimestampYAMLSerializer extends BaseDateYAMLSerializer<Timestamp> {

    private static final SqlTimestampYAMLSerializer INSTANCE = new SqlTimestampYAMLSerializer();

    private SqlTimestampYAMLSerializer() {}

    /** @return an instance of {@link SqlTimestampYAMLSerializer} */
    public static SqlTimestampYAMLSerializer getInstance() {
      return INSTANCE;
    }

    @Override
    protected void doSerialize(
        YAMLWriter writer,
        Timestamp value,
        YAMLSerializationContext ctx,
        YAMLSerializerParameters params) {
      if (ctx.isWriteDatesAsTimestamps()) {
        writer.value(propertyName, String.valueOf(value.getTime()));
      } else {
        String date = value.toString(); // TODO use a better format
        writer.value(propertyName, date);
      }
    }
  }
}
