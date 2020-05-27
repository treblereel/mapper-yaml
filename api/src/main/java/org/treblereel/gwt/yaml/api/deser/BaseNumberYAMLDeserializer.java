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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;
import org.treblereel.gwt.yaml.api.utils.NumberUtils;

/**
 * Base implementation of {@link YAMLDeserializer} for {@link java.lang.Number}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseNumberYAMLDeserializer<N extends Number> extends YAMLDeserializer<N> {

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link BigDecimal}
     */
    public static final class BigDecimalYAMLDeserializer extends BaseNumberYAMLDeserializer<BigDecimal> {

        private static final BigDecimalYAMLDeserializer INSTANCE = new BigDecimalYAMLDeserializer();

        private BigDecimalYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link BigDecimalYAMLDeserializer}
         */
        public static BigDecimalYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public BigDecimal deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return new BigDecimal(value);
        }

        @Override
        protected BigDecimal doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return new BigDecimal(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link BigInteger}
     */
    public static final class BigIntegerYAMLDeserializer extends BaseNumberYAMLDeserializer<BigInteger> {

        private static final BigIntegerYAMLDeserializer INSTANCE = new BigIntegerYAMLDeserializer();

        private BigIntegerYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link BigIntegerYAMLDeserializer}
         */
        public static BigIntegerYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public BigInteger deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return new BigInteger(value);
        }

        @Override
        protected BigInteger doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return new BigInteger(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Byte}
     */
    public static final class ByteYAMLDeserializer extends BaseNumberYAMLDeserializer<Byte> {

        private static final ByteYAMLDeserializer INSTANCE = new ByteYAMLDeserializer();

        private ByteYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link ByteYAMLDeserializer}
         */
        public static ByteYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Byte deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return 0;
            }
            return Byte.valueOf(value);
        }

        @Override
        protected Byte doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Byte.valueOf(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Double}
     */
    public static final class DoubleYAMLDeserializer extends BaseNumberYAMLDeserializer<Double> {

        private static final DoubleYAMLDeserializer INSTANCE = new DoubleYAMLDeserializer();

        private DoubleYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link DoubleYAMLDeserializer}
         */
        public static DoubleYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Double deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return Double.valueOf(value);
        }

        @Override
        protected Double doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Double.valueOf(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Float}
     */
    public static final class FloatYAMLDeserializer extends BaseNumberYAMLDeserializer<Float> {

        private static final FloatYAMLDeserializer INSTANCE = new FloatYAMLDeserializer();

        private FloatYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link FloatYAMLDeserializer}
         */
        public static FloatYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Float deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return Float.parseFloat(value);
        }

        @Override
        protected Float doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Float.parseFloat(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Integer}
     */
    public static final class IntegerYAMLDeserializer extends BaseNumberYAMLDeserializer<Integer> {

        private static final IntegerYAMLDeserializer INSTANCE = new IntegerYAMLDeserializer();

        private IntegerYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link IntegerYAMLDeserializer}
         */
        public static IntegerYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Integer deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return Integer.valueOf(value);
        }

        @Override
        protected Integer doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Integer.valueOf(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Long}
     */
    public static final class LongYAMLDeserializer extends BaseNumberYAMLDeserializer<Long> {

        private static final LongYAMLDeserializer INSTANCE = new LongYAMLDeserializer();

        private LongYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link LongYAMLDeserializer}
         */
        public static LongYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Long deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return Long.valueOf(value);
        }

        @Override
        protected Long doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Long.valueOf(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Short}
     */
    public static final class ShortYAMLDeserializer extends BaseNumberYAMLDeserializer<Short> {

        private static final ShortYAMLDeserializer INSTANCE = new ShortYAMLDeserializer();

        private ShortYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link ShortYAMLDeserializer}
         */
        public static ShortYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Short deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return Short.valueOf(value);
        }

        @Override
        protected Short doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            String value = reader.value();
            if (value == null) {
                return null;
            }
            return Short.valueOf(value);
        }
    }

    /**
     * Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Number}
     */
    public static final class NumberYAMLDeserializer extends BaseNumberYAMLDeserializer<Number> {

        private static final NumberYAMLDeserializer INSTANCE = new NumberYAMLDeserializer();

        private NumberYAMLDeserializer() {
        }

        /**
         * @return an instance of {@link NumberYAMLDeserializer}
         */
        public static NumberYAMLDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        public Number deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
                YAMLDeserializationException {
            if (value.isEmpty()) {
                return null;
            }
            return NumberUtils.toNumber(value);
        }

        @Override
        public Number doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
            return reader.number();
        }
    }
}
