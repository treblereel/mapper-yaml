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

package org.treblereel.gwt.yaml.api.deser;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.lang.String}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class StringYAMLDeserializer extends YAMLDeserializer<String> {

    private static final StringYAMLDeserializer INSTANCE = new StringYAMLDeserializer();

    private StringYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link StringYAMLDeserializer}
     */
    public static StringYAMLDeserializer getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return doDeserialize(yaml.string(key), ctx, params);
    }

    @Override
    public String doDeserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        if(value == null || value.equals("~")){
            return null;
        }
        return value;
    }
}
