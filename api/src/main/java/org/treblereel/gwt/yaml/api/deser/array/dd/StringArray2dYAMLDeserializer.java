package org.treblereel.gwt.yaml.api.deser.array.dd;

import java.util.List;

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.deser.StringYAMLDeserializer;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 3/28/20
 */
public class StringArray2dYAMLDeserializer extends AbstractArray2dYAMLDeserializer<String[][]> {

    private static final StringArray2dYAMLDeserializer INSTANCE = new StringArray2dYAMLDeserializer();

    private StringArray2dYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link StringArray2dYAMLDeserializer}
     */
    public static StringArray2dYAMLDeserializer newInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[][] doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        List<List<String>> list = deserializeIntoList(reader, ctx, StringYAMLDeserializer.getInstance(), params);

        if (list.isEmpty()) {
            return new String[0][0];
        }

        List<String> firstList = list.get(0);
        if (firstList.isEmpty()) {
            return new String[list.size()][0];
        }

        String[][] array = new String[list.size()][firstList.size()];

        int i = 0;
        int j;
        for (List<String> innerList : list) {
            j = 0;
            for (String value : innerList) {
                if (null != value) {
                    array[i][j] = value;
                }
                j++;
            }
            i++;
        }
        return array;
    }
}

