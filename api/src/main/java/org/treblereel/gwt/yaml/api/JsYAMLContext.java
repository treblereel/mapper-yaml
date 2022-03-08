package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.deser.bean.JsMapLike;
import org.treblereel.gwt.yaml.api.utils.JsDateFormat;

/**
 * <p>JsJacksonContext class.</p>
 * @author vegegoku
 * @version $Id: $Id
 */
public class JsYAMLContext implements YAMLContext {

    /**
     * {@inheritDoc}
     */
    @Override
    public DateFormat dateFormat() {
        return new JsDateFormat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapLikeFactory mapLikeFactory() {
        return JsMapLike::new;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLSerializerParameters defaultSerializerParameters() {
        return GwtYAMLSerializerParameters.DEFAULT;
    }

    @Override
    public YAMLSerializerParameters newSerializerParameters() {
        return new GwtYAMLSerializerParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLDeserializerParameters defaultDeserializerParameters() {
        return GwtYAMLDeserializerParameters.DEFAULT;
    }

    @Override
    public YAMLDeserializerParameters newDeserializerParameters() {
        return new GwtYAMLDeserializerParameters();
    }
}
