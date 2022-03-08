package org.treblereel.gwt.yaml.tests.primitive;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@J2clTestInput(StringTest.class)
public class StringTest {

    private static final String YAML_0 = "value: ~";
    private static final String YAML_1 = "value: testSerializeValue";

    private StringTest_StringType_YAMLMapperImpl mapper = StringTest_StringType_YAMLMapperImpl.INSTANCE;

    @Test
    public void testSerializeValue() throws IOException {
        StringTest.StringType test = new StringTest.StringType();
        test.setValue("testSerializeValue");
        assertEquals(YAML_1, mapper.write(test));
    }

    @Test
    public void testDeserializeValue() throws IOException {
        assertNull(mapper.read(YAML_0).getValue());
        assertEquals("testSerializeValue", mapper.read(YAML_1).getValue());
    }

    @YAMLMapper
    public static class StringType {

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
