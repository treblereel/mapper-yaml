/*
 * Copyright 2017 Ahmad Bawaneh
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


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.treblereel.gwt.yaml.api.GwtIncompatible;

/**
 * <p>DefaultMapLike class.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
@GwtIncompatible
public class DefaultMapLike<T> implements MapLike<T> {

    private Map<String, T> map = new HashMap<>();

    /** {@inheritDoc} */
    @Override
    public T get(String key) {
        return map.get(key);
    }

    /** {@inheritDoc} */
    @Override
    public void put(String key, T value) {
        map.put(key, value);
    }

    @Override
    public Set<String> keys() {
        return map.keySet();
    }
}
