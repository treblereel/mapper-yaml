package org.treblereel.gwt.yaml.tests.primitive;

import java.io.IOException;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.tests.beans.CharacterBean;
import org.treblereel.gwt.yaml.tests.beans.CharacterBean_MapperImpl;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 3/26/20
 */
@J2clTestInput(CharacterTest.class)
public class CharacterTest {

    CharacterBean_MapperImpl mapper = CharacterBean_MapperImpl.INSTANCE;

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals('e', CharacterBean_MapperImpl.INSTANCE.read("charVal: e").getCharVal());
    }

    @Test
    public void testSerializeValue() throws IOException {
        CharacterBean test = new CharacterBean();
        test.setCharVal('c');
        assertEquals("charVal: c", mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
    }
}
