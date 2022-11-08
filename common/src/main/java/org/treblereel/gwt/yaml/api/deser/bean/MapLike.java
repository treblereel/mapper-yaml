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

import java.util.Set;

/**
 * <p>MapLike interface.</p>
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface MapLike<T> {

    /**
     * <p>get.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @return a T object.
     */
    T get(String key);

    /**
     * <p>put.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a T object.
     */
    void put(String key, T value);

    Set<String> keys();
}
