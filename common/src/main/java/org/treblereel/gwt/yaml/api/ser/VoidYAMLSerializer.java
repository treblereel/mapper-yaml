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
 * Default {@link YAMLSerializer} implementation for {@link Void}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class VoidYAMLSerializer extends YAMLSerializer<Void> {

    private static final VoidYAMLSerializer INSTANCE = new VoidYAMLSerializer();

    /**
     * <p>getInstance</p>
     *
     * @return an instance of {@link VoidYAMLSerializer}
     */
    public static VoidYAMLSerializer getInstance() {
        return INSTANCE;
    }

    private VoidYAMLSerializer() {
    }

    /** {@inheritDoc} */
    @Override
    protected void serializeNullValue(YAMLWriter writer, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        writer.nullValue(propertyName);
    }

    /** {@inheritDoc} */
    @Override
    public void doSerialize(YAMLWriter writer, Void value, YAMLSerializationContext ctx, YAMLSerializerParameters params) {
        // we should never be here, the null value is already handled and it's the only possible value for Void
    }
}
