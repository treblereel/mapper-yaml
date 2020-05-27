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
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.lang.Void}.
 * @author Nicolas Morel
 * @version $Id: $
 */
public class VoidYAMLDeserializer extends YAMLDeserializer<Void> {

    private static final VoidYAMLDeserializer INSTANCE = new VoidYAMLDeserializer();

    private VoidYAMLDeserializer() {
    }

    /**
     * <p>getInstance</p>
     * @return an instance of {@link VoidYAMLDeserializer}
     */
    public static VoidYAMLDeserializer getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void doDeserialize(YAMLReader reader, YAMLDeserializationContext ctx, YAMLDeserializerParameters params) {
        // we should never be here, the null value is already handled and it's the only possible value for Void
        throw new UnsupportedOperationException();
    }
}
