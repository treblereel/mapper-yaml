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

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * <p>InstanceBuilder interface.</p>
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public interface InstanceBuilder<T> {

    /**
     * <p>newInstance</p>
     *
     * @param reader                   a {@link YAMLReader} object.
     * @param ctx                      a {@link YAMLDeserializationContext} object.
     * @param params                   a {@link YAMLDeserializerParameters} object.
     * @return a {@link deser.bean.Instance} object.
     */
    Instance<T> newInstance(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params);

    /**
     * <p>getParametersDeserializer</p>
     *
     * @return a {@link deser.bean.MapLike} object.
     */
    MapLike<HasDeserializerAndParameters> getParametersDeserializer();

}
