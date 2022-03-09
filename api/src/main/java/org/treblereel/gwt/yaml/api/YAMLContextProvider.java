package org.treblereel.gwt.yaml.api;

import static java.util.Objects.isNull;

/**
 * <p>JacksonContextProvider class.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public class YAMLContextProvider {

    static YAMLContext yamlContext;

    /**
     * <p>get.</p>
     *
     * @return a {@link YAMLContext} object.
     */
    public static YAMLContext get() {
        if (isNull(yamlContext))
            initContext();
        return yamlContext;
    }

    private static void initContext() {
        yamlContext = new ServerYAMLContext();
    }
}
