/*
 * Copyright © 2022 Treblereel
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

package org.treblereel.gwt.yaml.tests.annotations.nillable;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlNillable;
import org.treblereel.gwt.yaml.tests.AbstractYamlTest;
import org.treblereel.gwt.yaml.tests.annotations.nillable.NillableBoxedBeanTest.NillableBoxedBean;

@J2clTestInput(NillableBoxedBeanTest.class)
public class NillableBoxedBeanTest extends AbstractYamlTest<NillableBoxedBean> {

  private static final NillableBoxedBeanTest_NillableBoxedBean_YamlMapperImpl mapper =
      NillableBoxedBeanTest_NillableBoxedBean_YamlMapperImpl.INSTANCE;

  public NillableBoxedBeanTest() {
    super(mapper);
  }

  @Test
  public void testAllNulls() {
    NillableBoxedBean bean = new NillableBoxedBean();
    String result = mapper.write(bean);
    assertTrue(result.contains("_int: ~"));
    assertTrue(result.contains("_long: ~"));
    assertTrue(result.contains("_double: ~"));
    assertTrue(result.contains("_float: ~"));
    assertTrue(result.contains("_boolean: ~"));
    assertTrue(result.contains("_short: ~"));
    assertTrue(result.contains("_string: ~"));
  }

  @Test
  public void testPartialNulls() {
    NillableBoxedBean bean = new NillableBoxedBean();
    bean.set_int(42);
    bean.set_boolean(true);
    String result = mapper.write(bean);
    assertTrue(result.contains("_int: 42"));
    assertTrue(result.contains("_boolean: true"));
    assertTrue(result.contains("_long: ~"));
    assertTrue(result.contains("_double: ~"));
    assertTrue(result.contains("_float: ~"));
    assertTrue(result.contains("_short: ~"));
    assertTrue(result.contains("_string: ~"));
  }

  @Test
  public void testWithValues() throws IOException {
    NillableBoxedBean bean = new NillableBoxedBean();
    bean.set_int(1234);
    bean.set_long(1000L);
    bean.set_double(10d);
    bean.set_float(1122f);
    bean.set_boolean(true);
    bean.set_short((short) 17222);
    bean.set_string("hello");

    assertMarshallingAndUnmarshalling(bean);
  }

  @Test
  public void testRoundTripAllNulls() throws IOException {
    NillableBoxedBean bean = new NillableBoxedBean();
    String result = mapper.write(bean);
    NillableBoxedBean from = mapper.read(result);
    assertNull(from.get_int());
    assertNull(from.get_long());
    assertNull(from.get_double());
    assertNull(from.get_float());
    assertNull(from.get_boolean());
    assertNull(from.get_short());
    assertNull(from.get_string());
  }

  @YAMLMapper
  @YamlNillable
  public static class NillableBoxedBean {

    private Integer _int;
    private Long _long;
    private Double _double;
    private Float _float;
    private Boolean _boolean;
    private Short _short;
    private String _string;

    public Integer get_int() {
      return _int;
    }

    public void set_int(Integer _int) {
      this._int = _int;
    }

    public Long get_long() {
      return _long;
    }

    public void set_long(Long _long) {
      this._long = _long;
    }

    public Double get_double() {
      return _double;
    }

    public void set_double(Double _double) {
      this._double = _double;
    }

    public Float get_float() {
      return _float;
    }

    public void set_float(Float _float) {
      this._float = _float;
    }

    public Boolean get_boolean() {
      return _boolean;
    }

    public void set_boolean(Boolean _boolean) {
      this._boolean = _boolean;
    }

    public Short get_short() {
      return _short;
    }

    public void set_short(Short _short) {
      this._short = _short;
    }

    public String get_string() {
      return _string;
    }

    public void set_string(String _string) {
      this._string = _string;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      NillableBoxedBean that = (NillableBoxedBean) o;
      return Objects.equals(_int, that._int)
          && Objects.equals(_long, that._long)
          && Objects.equals(_double, that._double)
          && Objects.equals(_float, that._float)
          && Objects.equals(_boolean, that._boolean)
          && Objects.equals(_short, that._short)
          && Objects.equals(_string, that._string);
    }

    @Override
    public int hashCode() {
      return Objects.hash(_int, _long, _double, _float, _boolean, _short, _string);
    }
  }
}
