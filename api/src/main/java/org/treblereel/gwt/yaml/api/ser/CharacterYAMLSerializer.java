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

package org.treblereel.gwt.yaml.api.ser;

import org.treblereel.gwt.yaml.api.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.YAMLSerializerParameters;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Default {@link YAMLSerializer} implementation for {@link Character}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class CharacterYAMLSerializer extends YAMLSerializer<Character> {

    private static final CharacterYAMLSerializer INSTANCE = new CharacterYAMLSerializer();

    private CharacterYAMLSerializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link CharacterYAMLSerializer}
     */
    public static CharacterYAMLSerializer getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSerialize(YAMLWriter writer, Character value, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        writer.value(value.toString());
    }
}
