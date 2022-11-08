package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.deser.bean.DefaultMapLike;
import org.treblereel.gwt.yaml.api.utils.DefaultDateFormat;

/**
 * <p>ServerYAMLContext class.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public class ServerYAMLContext extends JsYAMLContext {

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLContext.DateFormat dateFormat() {
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
        return ServerYAMLSerializerParameters.DEFAULT;
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLDeserializerParameters defaultDeserializerParameters() {
        return ServerYAMLDeserializerParameters.DEFAULT;
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLSerializerParameters newSerializerParameters() {
        return new ServerYAMLSerializerParameters();
    }

    /** {@inheritDoc} */
    @GwtIncompatible
    @Override
    public YAMLDeserializerParameters newDeserializerParameters() {
        return new ServerYAMLDeserializerParameters();
    }

}
