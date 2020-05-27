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

package org.treblereel.gwt.yaml.api.stream;

/**
 * <p>YAMLWriter interface.</p>
 * @author nicolasmorel
 * @version $Id: $
 */
public interface YAMLWriter {

    /**
     * Begins encoding a new object. Each call to this method must be paired
     * with a call to {@link #endObject}.
     * @return this writer.
     */
    YAMLWriter beginObject(String name);

    /**
     * Ends encoding the current object.
     * @return this writer.
     */
    YAMLWriter endObject();

    /**
     * Encodes the property name.
     * @param name the name of the forthcoming value. May not be null.
     * @return this writer.
     */
    YAMLWriter name(String name);

    /**
     * Encodes the property name without escaping it.
     * @param name the name of the forthcoming value. May not be null.
     * @return this writer.
     */
    YAMLWriter unescapeName(String name);

    /**
     * Encodes {@code value}.
     * @param value the literal string value, or null to encode a null literal.
     * @return this writer.
     */
    YAMLWriter value(String value);

    /**
     * Encodes {@code value} without escaping it.
     * @param value the literal string value, or null to encode a null literal.
     * @return this writer.
     */
    YAMLWriter unescapeValue(String value);

    /**
     * Encodes {@code null}.
     * @return this writer.
     */
    YAMLWriter nullValue();

    /**
     * Encodes {@code value}.
     * @param value a boolean.
     * @return this writer.
     */
    YAMLWriter value(Boolean value);

    /**
     * Encodes {@code value}.
     * @param value a finite value. May not be {@link java.lang.Integer#isNaN() NaNs} or
     * {@link java.lang.Integer infinities}.
     * @return this writer.
     */
    YAMLWriter value(Integer value);

    /**
     * Encodes {@code value}.
     * @param value a finite value. May not be {@link java.lang.Double#isNaN() NaNs} or
     * {@link java.lang.Double#isInfinite() infinities}.
     * @return this writer.
     */
    YAMLWriter value(Double value);

    /**
     * Encodes {@code value}.
     * @param value a long.
     * @return this writer.
     */
    YAMLWriter value(Long value);

    /**
     * Encodes {@code value}.
     * @param value a finite value. May not be {@link java.lang.Double#isNaN() NaNs} or
     * {@link java.lang.Double#isInfinite() infinities}.
     * @return this writer.
     */
    YAMLWriter value(Number value);

    /**
     * Ensures all buffered data is written to the underlying {@link java.lang.StringBuilder}
     * and flushes that writer.
     */
    void flush();

    /**
     * Flushes and closes this writer and the underlying {@link java.lang.StringBuilder}.
     */
    void close();

    /**
     * <p>getOutput</p>
     * @return the output when the serialization is over
     */
    String getOutput();

    void beginArray();

    void endArray();
}
