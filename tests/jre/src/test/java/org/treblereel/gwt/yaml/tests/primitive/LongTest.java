package org.treblereel.gwt.yaml.tests.primitive;

import java.io.IOException;
import java.util.Objects;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 4/22/20
 */
@J2clTestInput(LongTest.class)
public class LongTest {

    private static final String YAML_0 = "value: 0";
    private static final String YAML_17222 = "value: 17222";
    private static final String YAML__17222 = "value: \"-17222\"";

    private LongTest_LongType_MapperImpl mapper = LongTest_LongType_MapperImpl.INSTANCE;

    @Test
    public void testSerializeValue() throws IOException {
        LongTest.LongType test = new LongTest.LongType();
        assertEquals(YAML_0, mapper.write(test));
        test.setValue(17222l);
        assertEquals(YAML_17222, mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
        test.setValue(-17222l);
        assertEquals(YAML__17222, mapper.write(test));
        assertEquals(test, mapper.read(mapper.write(test)));
    }

    @Test
    public void testDeserializeValue() throws IOException {
        assertEquals(0, mapper.read(YAML_0).getValue());
        assertEquals(17222l, mapper.read(YAML_17222).getValue());
        assertEquals(-17222l, mapper.read(YAML__17222).getValue());
    }

    @YAMLMapper
    public static class LongType {

        private long value;

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof LongType)) {
                return false;
            }
            LongType longType = (LongType) o;
            return getValue() == longType.getValue();
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }
}



