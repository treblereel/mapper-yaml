//@formatter:off
/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.treblereel.gwt.yaml.api.stream.impl;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

public class DefaultYAMLReader implements YAMLReader {

    private final YamlMapping reader;
    private final String in;

    public DefaultYAMLReader(String in) throws IOException {
        if (in == null) {
            throw new NullPointerException("in == null");
        }
        this.in = in;
        reader = Yaml.createYamlInput(in).readYamlMapping();
    }

    @Override
    public Set<String> getProperties() {
        return reader.keys().stream().map(prop -> prop.toString()).collect(Collectors.toSet());
    }

    @Override
    public String getInput() {
        return in;
    }

    @Override
    public String getValue(String key) {
        String value = reader.string(key);
        if(value.equals("~")) {
            return null;
        }
        return value;
    }

    @Override
    public String getArray(String key) {
        YamlSequence sequence = reader.yamlSequence(key);
        for (YamlNode yamlNode : sequence) {
            System.out.println("YamlNode " + yamlNode);
        }

        return null;
    }
}
