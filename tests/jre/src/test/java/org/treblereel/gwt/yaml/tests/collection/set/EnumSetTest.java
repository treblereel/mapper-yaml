package org.treblereel.gwt.yaml.tests.collection.set;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.tests.enums.SimpleEnum;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@J2clTestInput(EnumSetTest.class)
public class EnumSetTest {

    private static final EnumSetTest_EnumSetBeanTest_YAMLMapperImpl mapper = EnumSetTest_EnumSetBeanTest_YAMLMapperImpl.INSTANCE;

    private EnumSetBeanTest getSetBeanTest() {

        Set<SimpleEnum> set = new HashSet<>();
        EnumSetBeanTest bean = new EnumSetBeanTest();
        bean.setId(-1);
        bean.setValues(set);
        set.add(SimpleEnum.ONE);
        set.add(SimpleEnum.TWO);
        set.add(SimpleEnum.THREE);
        set.add(SimpleEnum.FOUR);
        set.add(SimpleEnum.FIVE);
        return bean;
    }

    @Test
    public void testSerializeDeSerializeValue() throws IOException {
        EnumSetBeanTest temp = mapper.read(mapper.write(getSetBeanTest()));
        assertTrue(temp.getValues().contains(SimpleEnum.ONE));
        assertTrue(temp.getValues().contains(SimpleEnum.TWO));
        assertTrue(temp.getValues().contains(SimpleEnum.THREE));
        assertTrue(temp.getValues().contains(SimpleEnum.FOUR));
        assertTrue(temp.getValues().contains(SimpleEnum.FIVE));
    }

    @YAMLMapper
    public static class EnumSetBeanTest {

        private int id;

        private Set<SimpleEnum> values;

        public Set<SimpleEnum> getValues() {
            return values;
        }

        public void setValues(Set<SimpleEnum> values) {
            this.values = values;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EnumSetBeanTest that = (EnumSetBeanTest) o;
            return id == that.id && Objects.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, values);
        }
    }

}
