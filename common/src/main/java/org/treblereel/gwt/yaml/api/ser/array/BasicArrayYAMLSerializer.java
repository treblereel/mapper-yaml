package org.treblereel.gwt.yaml.api.ser.array;

import org.treblereel.gwt.yaml.api.YAMLSerializer;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 3/28/20
 */
public abstract class BasicArrayYAMLSerializer<T> extends YAMLSerializer<T> {

    protected String propertyName;

    public BasicArrayYAMLSerializer<T> setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }
}
