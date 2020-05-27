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

package org.treblereel.gwt.yaml.api.ser;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Base implementation of {@link YAMLSerializer} for {@link Number}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseNumberYAMLSerializer<N extends Number> extends YAMLSerializer<N> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(YAMLWriter writer, N value, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        writer.value(value);
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link BigDecimal}
     */
    public static final class BigDecimalYAMLSerializer extends BaseNumberYAMLSerializer<BigDecimal> {

        private static final BigDecimalYAMLSerializer INSTANCE = new BigDecimalYAMLSerializer();

        private BigDecimalYAMLSerializer() {
        }

        /**
         * @return an instance of {@link BigDecimalYAMLSerializer}
         */
        public static BigDecimalYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link BigInteger}
     */
    public static final class BigIntegerYAMLSerializer extends BaseNumberYAMLSerializer<BigInteger> {

        private static final BigIntegerYAMLSerializer INSTANCE = new BigIntegerYAMLSerializer();

        private BigIntegerYAMLSerializer() {
        }

        /**
         * @return an instance of {@link BigIntegerYAMLSerializer}
         */
        public static BigIntegerYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Byte}
     */
    public static final class ByteYAMLSerializer extends BaseNumberYAMLSerializer<Byte> {

        private static final ByteYAMLSerializer INSTANCE = new ByteYAMLSerializer();

        private ByteYAMLSerializer() {
        }

        /**
         * @return an instance of {@link ByteYAMLSerializer}
         */
        public static ByteYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Double}
     */
    public static final class DoubleYAMLSerializer extends BaseNumberYAMLSerializer<Double> {

        private static final DoubleYAMLSerializer INSTANCE = new DoubleYAMLSerializer();

        private DoubleYAMLSerializer() {
        }

        /**
         * @return an instance of {@link DoubleYAMLSerializer}
         */
        public static DoubleYAMLSerializer getInstance() {
            return INSTANCE;
        }

        @Override
        public void doSerialize(YAMLWriter writer, Double value, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
            // writer has a special method to write double, let's use instead of default Number method.
            writer.value(value.doubleValue());
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Float}
     */
    public static final class FloatYAMLSerializer extends BaseNumberYAMLSerializer<Float> {

        private static final FloatYAMLSerializer INSTANCE = new FloatYAMLSerializer();

        private FloatYAMLSerializer() {
        }

        /**
         * @return an instance of {@link FloatYAMLSerializer}
         */
        public static FloatYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Integer}
     */
    public static final class IntegerYAMLSerializer extends BaseNumberYAMLSerializer<Integer> {

        private static final IntegerYAMLSerializer INSTANCE = new IntegerYAMLSerializer();

        private IntegerYAMLSerializer() {
        }

        /**
         * @return an instance of {@link IntegerYAMLSerializer}
         */
        public static IntegerYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Long}
     */
    public static final class LongYAMLSerializer extends BaseNumberYAMLSerializer<Long> {

        private static final LongYAMLSerializer INSTANCE = new LongYAMLSerializer();

        private LongYAMLSerializer() {
        }

        /**
         * @return an instance of {@link LongYAMLSerializer}
         */
        public static LongYAMLSerializer getInstance() {
            return INSTANCE;
        }

        @Override
        public void doSerialize(YAMLWriter writer, Long value, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
            // writer has a special method to write long, let's use instead of default Number method.
            writer.value(value.longValue());
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Short}
     */
    public static final class ShortYAMLSerializer extends BaseNumberYAMLSerializer<Short> {

        private static final ShortYAMLSerializer INSTANCE = new ShortYAMLSerializer();

        private ShortYAMLSerializer() {
        }

        /**
         * @return an instance of {@link ShortYAMLSerializer}
         */
        public static ShortYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLSerializer} for {@link Number}
     */
    public static final class NumberYAMLSerializer extends BaseNumberYAMLSerializer<Number> {

        private static final NumberYAMLSerializer INSTANCE = new NumberYAMLSerializer();

        private NumberYAMLSerializer() {
        }

        /**
         * @return an instance of {@link NumberYAMLSerializer}
         */
        public static NumberYAMLSerializer getInstance() {
            return INSTANCE;
        }
    }
}
