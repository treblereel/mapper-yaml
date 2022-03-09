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

    /**
     * {@inheritDoc}
     */
    @Override
    public Character doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        String value = yaml.string(key);
        return doDeserialize(value, ctx, params);
    }

    @Override
    public Character doDeserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        if (value == null || value.isEmpty()) {
            return '\u0000';
        }
        return value.charAt(0);
    }
}
