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

import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

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

    @Override
    public String deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
            YAMLDeserializationException {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        return reader.value();
    }
}
