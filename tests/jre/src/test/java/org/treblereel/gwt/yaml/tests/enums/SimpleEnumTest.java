package org.treblereel.gwt.yaml.tests.enums;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@J2clTestInput(SimpleEnumTest.class)
public class SimpleEnumTest {

    private static final SimpleEnumTest_SimpleEnumTestBean_YAMLMapperImpl mapper = SimpleEnumTest_SimpleEnumTestBean_YAMLMapperImpl.INSTANCE;
    private static final String YAML = "value: FIVE";

    @Test
    public void testSerializeValue() throws IOException {

        SimpleEnumTestBean bean = new SimpleEnumTestBean();
        bean.setValue(SimpleEnum.FIVE);
        String yaml = mapper.write(bean);
        assertEquals(YAML, yaml);
    }

    @Test
    public void tesDeSerializeValue() throws IOException {
        SimpleEnumTestBean bean = new SimpleEnumTestBean();
        bean.setValue(SimpleEnum.FIVE);

        SimpleEnumTestBean temp = mapper.read(YAML);
        assertEquals(bean, temp);
        assertEquals(YAML, mapper.write(temp));
    }

    @YAMLMapper
    public static class SimpleEnumTestBean {
        private SimpleEnum value;

        public SimpleEnum getValue() {
            return value;
        }

        public void setValue(SimpleEnum value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimpleEnumTestBean that = (SimpleEnumTestBean) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
