package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.deser.bean.JsMapLike;
import org.treblereel.gwt.yaml.api.utils.JsDateFormat;

/**
 * <p>JsJacksonContext class.</p>
 * @author vegegoku
 * @version $Id: $Id
 */
public class JsJacksonContext implements JacksonContext {

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
        return GwtJacksonYAMLSerializerParameters.DEFAULT;
    }

    @Override
    public YAMLSerializerParameters newSerializerParameters() {
        return new GwtJacksonYAMLSerializerParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public YAMLDeserializerParameters defaultDeserializerParameters() {
        return GwtJacksonYAMLDeserializerParameters.DEFAULT;
    }

    @Override
    public YAMLDeserializerParameters newDeserializerParameters() {
        return new GwtJacksonYAMLDeserializerParameters();
    }
}
