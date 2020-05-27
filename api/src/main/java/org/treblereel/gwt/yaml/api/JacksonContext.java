package org.treblereel.gwt.yaml.api;

import java.util.Date;

import org.treblereel.gwt.yaml.api.deser.bean.MapLike;
import org.treblereel.gwt.yaml.api.stream.Stack;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * <p>JacksonContext interface.</p>
 * @author vegegoku
 * @version $Id: $Id
 */
public interface JacksonContext {

    /**
     * <p>dateFormat.</p>
     * @return a {@link JacksonContext.DateFormat} object.
     */
    DateFormat dateFormat();

    /**
     * <p>mapLikeFactory.</p>
     * @return a {@link JacksonContext.MapLikeFactory} object.
     */
    MapLikeFactory mapLikeFactory();

    /**
     * <p>defaultSerializerParameters.</p>
     * @return a {@link YAMLSerializerParameters} object.
     */
    YAMLSerializerParameters defaultSerializerParameters();

    /**
     * <p>newSerializerParameters</p>
     * @return a new instance of {@link YAMLSerializerParameters} object
     */
    YAMLSerializerParameters newSerializerParameters();

    /**
     * <p>defaultDeserializerParameters.</p>
     * @return a {@link YAMLDeserializerParameters} object.
     */
    YAMLDeserializerParameters defaultDeserializerParameters();

    /**
     * <p>newDeserializerParameters</p>
     * @return a new instance of {@link YAMLDeserializerParameters} object
     */
    YAMLDeserializerParameters newDeserializerParameters();

    interface DateFormat {

        String format(Date date);

        String format(YAMLSerializerParameters params, Date date);

        Date parse(boolean useBrowserTimezone, String pattern, Boolean hasTz, String date);

    }

    interface IntegerStackFactory {

        Stack<Integer> make();
    }

    interface ValueStringifier {

        String stringify(String value);
    }

    interface MapLikeFactory {

        <T> MapLike<T> make();
    }

    interface StringArrayReader {

        String[] readArray(YAMLReader reader);
    }

    interface ShortArrayReader {

        short[] readArray(YAMLReader reader);
    }

    interface IntegerArrayReader {

        int[] readArray(YAMLReader reader);
    }

    interface DoubleArrayReader {

        double[] readArray(YAMLReader reader);
    }
}
