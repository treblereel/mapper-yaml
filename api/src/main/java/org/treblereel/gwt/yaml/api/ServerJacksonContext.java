package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.deser.bean.DefaultMapLike;
import org.treblereel.gwt.yaml.api.utils.DefaultDateFormat;

/**
 * <p>ServerJacksonContext class.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public class ServerJacksonContext extends JsJacksonContext{

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public JacksonContext.DateFormat dateFormat() {
        return new DefaultDateFormat();
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public MapLikeFactory mapLikeFactory() {
        return DefaultMapLike::new;
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLSerializerParameters defaultSerializerParameters() {
        return ServerJacksonYAMLSerializerParameters.DEFAULT;
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLDeserializerParameters defaultDeserializerParameters() {
        return ServerJacksonYAMLDeserializerParameters.DEFAULT;
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLSerializerParameters newSerializerParameters() {
        return new ServerJacksonYAMLSerializerParameters();
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLDeserializerParameters newDeserializerParameters() {
        return new ServerJacksonYAMLDeserializerParameters();
    }

}
