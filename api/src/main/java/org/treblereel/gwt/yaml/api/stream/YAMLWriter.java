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

package org.treblereel.gwt.yaml.api.stream;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;

import java.util.Collection;

/**
 * <p>YAMLWriter interface.</p>
 * @author nicolasmorel
 * @version $Id: $
 */
public interface YAMLWriter {

    YAMLWriter value(String name, String value);

    YAMLWriter value(String name, YamlMapping value);

    YAMLWriter value(String name, Collection<String> values);

    String getOutput();

    void nullValue(String propertyName);

    YamlMappingBuilder getWriter();
}
