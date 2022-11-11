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

package org.treblereel.gwt.yaml.api.internal.deser;

import com.amihaiemil.eoyaml.YamlMapping;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;

/**
 * Base implementation of {@link YAMLDeserializer} for {@link java.lang.Number}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class BaseNumberYAMLDeserializer<N extends Number> extends YAMLDeserializer<N> {

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link BigDecimal} */
  public static final class BigDecimalYAMLDeserializer
      extends BaseNumberYAMLDeserializer<BigDecimal> {

    public static final BigDecimalYAMLDeserializer INSTANCE = new BigDecimalYAMLDeserializer();

    @Override
    public BigDecimal deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public BigDecimal deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return new BigDecimal(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link BigInteger} */
  public static final class BigIntegerYAMLDeserializer
      extends BaseNumberYAMLDeserializer<BigInteger> {

    public static final BigIntegerYAMLDeserializer INSTANCE = new BigIntegerYAMLDeserializer();

    @Override
    public BigInteger deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public BigInteger deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return new BigInteger(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Byte} */
  public static final class ByteYAMLDeserializer extends BaseNumberYAMLDeserializer<Byte> {

    public static final ByteYAMLDeserializer INSTANCE = new ByteYAMLDeserializer();

    @Override
    public Byte deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Byte deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return 0;
      }
      return Byte.valueOf(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Double} */
  public static final class DoubleYAMLDeserializer extends BaseNumberYAMLDeserializer<Double> {

    public static final DoubleYAMLDeserializer INSTANCE = new DoubleYAMLDeserializer();

    @Override
    public Double deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Double deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return Double.valueOf(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Float} */
  public static final class FloatYAMLDeserializer extends BaseNumberYAMLDeserializer<Float> {

    public static final FloatYAMLDeserializer INSTANCE = new FloatYAMLDeserializer();

    @Override
    public Float deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Float deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return Float.parseFloat(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Integer} */
  public static final class IntegerYAMLDeserializer extends BaseNumberYAMLDeserializer<Integer> {

    public static final IntegerYAMLDeserializer INSTANCE = new IntegerYAMLDeserializer();

    @Override
    public Integer deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Integer deserialize(String value, YAMLDeserializationContext ctx) {
      if (value == null || value.isEmpty()) {
        return null;
      }
      return Integer.valueOf(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Long} */
  public static final class LongYAMLDeserializer extends BaseNumberYAMLDeserializer<Long> {

    public static final LongYAMLDeserializer INSTANCE = new LongYAMLDeserializer();

    @Override
    public Long deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Long deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return Long.valueOf(value);
    }
  }

  /** Default implementation of {@link BaseNumberYAMLDeserializer} for {@link Short} */
  public static final class ShortYAMLDeserializer extends BaseNumberYAMLDeserializer<Short> {

    public static final ShortYAMLDeserializer INSTANCE = new ShortYAMLDeserializer();

    @Override
    public Short deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
        throws YAMLDeserializationException {
      String value = yaml.string(key);
      return deserialize(value, ctx);
    }

    @Override
    public Short deserialize(String value, YAMLDeserializationContext ctx) {
      if (value.isEmpty()) {
        return null;
      }
      return Short.valueOf(value);
    }
  }
}
