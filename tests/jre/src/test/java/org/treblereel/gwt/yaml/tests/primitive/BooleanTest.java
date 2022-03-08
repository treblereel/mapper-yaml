package org.treblereel.gwt.yaml.tests.primitive;

import java.io.IOException;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.tests.beans.BooleanBean;
import org.treblereel.gwt.yaml.tests.beans.BooleanBean_YAMLMapperImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/22/20
 */
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
