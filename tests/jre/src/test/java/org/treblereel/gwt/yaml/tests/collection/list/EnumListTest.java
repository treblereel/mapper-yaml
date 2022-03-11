package org.treblereel.gwt.yaml.tests.collection.list;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.tests.enums.SimpleEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@J2clTestInput(EnumListTest.class)
public class EnumListTest {

    private static final EnumListTest_EnumListBeanTest_YAMLMapperImpl mapper = EnumListTest_EnumListBeanTest_YAMLMapperImpl.INSTANCE;
    private static final String YAML = "id: \"-1\"\n" +
            "values:\n" +
            "  - ONE\n" +
            "  - TWO\n" +
            "  - THREE\n" +
            "  - FOUR\n" +
            "  - FIVE";

    @Test
    public void testSerializeValue() throws IOException {

        EnumListBeanTest bean = getListBeanTest();

        String yaml = mapper.write(bean);
        assertEquals(YAML, yaml);
    }

    private EnumListBeanTest getListBeanTest() {

        List<SimpleEnum> list = new ArrayList<>();
        EnumListBeanTest bean = new EnumListBeanTest();
        bean.setId(-1);
        bean.setValues(list);
        list.add(SimpleEnum.ONE);
        list.add(SimpleEnum.TWO);
        list.add(SimpleEnum.THREE);
        list.add(SimpleEnum.FOUR);
        list.add(SimpleEnum.FIVE);
        return bean;
    }

    @Test
    public void tesDeSerializeValue() throws IOException {
        EnumListBeanTest temp = mapper.read(YAML);
        assertEquals(getListBeanTest(), temp);
        assertEquals(YAML, mapper.write(temp));
    }

    @YAMLMapper
    public static class EnumListBeanTest {

        private int id;

        private List<SimpleEnum> values;

        public List<SimpleEnum> getValues() {
            return values;
        }

        public void setValues(List<SimpleEnum> values) {
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
            EnumListBeanTest that = (EnumListBeanTest) o;
            return id == that.id && Objects.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, values);
        }
    }

}
