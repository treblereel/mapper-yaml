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

import java.util.UUID;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLDeserializerParameters;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.util.UUID}.
 * @author Nicolas Morel
 * @version $Id: $Id
 */
public class UUIDYAMLDeserializer extends YAMLDeserializer<UUID> {

    public static final UUIDYAMLDeserializer INSTANCE = new UUIDYAMLDeserializer();

    private UUIDYAMLDeserializer() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        String uuid = yaml.string(key);
        return doDeserialize(uuid, ctx, params);
    }

    @Override
    public UUID doDeserialize(String value, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        if (value != null) {
            return UUID.fromString(value);
        }
        return null;
    }
}
