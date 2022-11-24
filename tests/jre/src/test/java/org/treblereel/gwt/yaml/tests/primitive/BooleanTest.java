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

package org.treblereel.gwt.yaml.tests.primitive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import org.junit.Test;
import org.treblereel.gwt.yaml.tests.beans.BooleanBean;
import org.treblereel.gwt.yaml.tests.beans.BooleanBean_YAMLMapperImpl;

/** @author Dmitrii Tikhomirov Created by treblereel 4/22/20 */
@J2clTestInput(BooleanTest.class)
public class BooleanTest {

  private static final String YAML_TRUE = "check: true";
  private static final String YAML_FALSE = "check: false";

  private BooleanBean_YAMLMapperImpl mapper = BooleanBean_YAMLMapperImpl.INSTANCE;

  @Test
  public void testSerializeValue() throws IOException {
    BooleanBean test = new BooleanBean();
    assertEquals(YAML_FALSE, mapper.write(test));
    test.setCheck(true);
    assertEquals(YAML_TRUE, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
    test.setCheck(false);
    assertEquals(YAML_FALSE, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
  }

  @Test
  public void testDeserializeValue() throws IOException {
    assertTrue(mapper.read(YAML_TRUE).isCheck());
    assertFalse(mapper.read(YAML_FALSE).isCheck());
    BooleanBean test = new BooleanBean();
    test.setCheck(true);
    assertEquals(YAML_TRUE, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
    test.setCheck(false);
    assertEquals(YAML_FALSE, mapper.write(test));
    assertEquals(test, mapper.read(mapper.write(test)));
  }
}
