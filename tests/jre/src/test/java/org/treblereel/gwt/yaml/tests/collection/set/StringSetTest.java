package org.treblereel.gwt.yaml.tests.collection.set;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@J2clTestInput(StringSetTest.class)
public class StringSetTest {


    private static final StringSetTest_StringSetBeanTest_YAMLMapperImpl mapper = StringSetTest_StringSetBeanTest_YAMLMapperImpl.INSTANCE;
    private static final String YAML = "id: \"-1\"\n" +
            "values:\n" +
            "  - AAA\n" +
            "  - AAA1\n" +
            "  - AAA2\n" +
            "  - AAA3\n" +
            "  - AAA4";

    @Test
    public void testSerializeValue() throws IOException {

        StringSetBeanTest bean = getListBeanTest();

        String yaml = mapper.write(bean);
        assertEquals(YAML, yaml);
    }

    private StringSetBeanTest getListBeanTest() {

        List<String> list = new ArrayList<>();
        StringSetBeanTest bean = new StringSetBeanTest();
        bean.setId(-1);
        bean.setValues(list);
        list.add("AAA");
        list.add("AAA1");
        list.add("AAA2");
        list.add("AAA3");
        list.add("AAA4");
        return bean;
    }

    @Test
    public void tesDeSerializeValue() throws IOException {
        StringSetBeanTest temp = mapper.read(YAML);
        assertEquals(getListBeanTest(), temp);
        assertEquals(YAML, mapper.write(temp));
    }


    @YAMLMapper
    public static class StringSetBeanTest {

        private int id;

        private List<String> values;

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
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
            StringSetBeanTest that = (StringSetBeanTest) o;
            return id == that.id && Objects.equals(values, that.values);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, values);
        }
    }

}

