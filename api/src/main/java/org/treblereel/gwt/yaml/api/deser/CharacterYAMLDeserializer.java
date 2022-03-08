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
 * Default {@link YAMLDeserializer} implementation for {@link java.lang.Character}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class CharacterYAMLDeserializer extends YAMLDeserializer<Character> {

    private static final CharacterYAMLDeserializer INSTANCE = new CharacterYAMLDeserializer();

    private CharacterYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link CharacterYAMLDeserializer}
     */
    public static CharacterYAMLDeserializer getInstance() {
        return INSTANCE;
    }

    @Override
    public Character deserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) throws
            YAMLDeserializationException {
        if (value.isEmpty()) {
            return '\u0000';
        }
        return value.charAt(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Character doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        String value = reader.getValue(params.getTypeInfo().getPropertyName());
        if (value == null || value.isEmpty()) {
            return null;
        }
        return value.charAt(0);
    }
}
