/*
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

import java.io.IOException;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.tests.beans.CharacterBean;
import org.treblereel.gwt.yaml.tests.beans.CharacterBean_YAMLMapperImpl;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 3/26/20
 */
@J2clTestInput(CharacterTest.class)
public class CharacterTest {

    CharacterBean_YAMLMapperImpl mapper = CharacterBean_YAMLMapperImpl.INSTANCE;

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals('e', CharacterBean_YAMLMapperImpl.INSTANCE.read("charVal: e").getCharVal());
    }

    @Test
    public void testSerializeValue() throws IOException {
        CharacterBean test = new CharacterBean();
        test.setCharVal('c');
        assertEquals("charVal: c", mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
    }
}
