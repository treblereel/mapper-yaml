package org.treblereel.gwt.yaml.tests.array;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@J2clTestInput(BooleanArrayTest.class)
public class BooleanArrayTest {

    private static final BooleanArrayTest_BooleanArray_YAMLMapperImpl mapper = BooleanArrayTest_BooleanArray_YAMLMapperImpl.INSTANCE;

    //@Test
    public void testSerializeValue() throws IOException {
        BooleanArray booleanArray = new BooleanArray();
        booleanArray.setValues(new boolean[] {true, false, true, false});
        String yaml = mapper.write(booleanArray);

        assertEquals("values:\n" +
                "  - true\n" +
                "  - false\n" +
                "  - true\n" +
                "  - false", yaml);

    }

    //@Test
    public void tesDeSerializeValue() throws IOException {
        BooleanArray booleanArray = new BooleanArray();
        boolean[] value = new boolean[] {true, false, true, false};
        booleanArray.setValues(value);
        String yaml = mapper.write(booleanArray);
        System.out.println("YAML\n" + yaml);
        assertArrayEquals(value, mapper.read(yaml).getValues());
    }


    @YAMLMapper
    public static class BooleanArray {

        private boolean[] values;

        public boolean[] getValues() {
            return values;
        }

        public void setValues(boolean[] values) {
            this.values = values;
        }
    }
}
