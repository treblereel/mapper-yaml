/*
 * Copyright 2013 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.treblereel.gwt.yaml.api.deser.bean;

import java.util.Map;

/**
 * <p>Instance class.</p>
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class Instance<T> {

    private final T instance;

    /**
     * <p>Constructor for Instance.</p>
     *
     * @param instance a T object.
     */
    public Instance(T instance) {
        this.instance = instance;
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a T object.
     */
    public T getInstance() {
        return instance;
    }

}
