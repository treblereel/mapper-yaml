/*
 * Copyright 2014 Nicolas Morel
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

package org.treblereel.gwt.yaml.api.stream.impl;

import com.amihaiemil.eoyaml.*;
import java.util.Collection;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * DefaultYAMLWriter class.
 *
 * @author nicolasmorel
 * @version $Id: $
 */
public class DefaultYAMLWriter implements YAMLWriter {

  private YamlMappingBuilder writer = Yaml.createYamlMappingBuilder();

  @Override
  public YAMLWriter value(String name, String value) {
    writer = writer.add(name, value);
    return this;
  }

  @Override
  public YAMLWriter value(String name, YamlMapping value) {
    writer = writer.add(name, value);
    return this;
  }

  @Override
  public YAMLWriter collectionOfString(String name, Collection<String> values) {
    YamlSequenceBuilder builder = Yaml.createYamlSequenceBuilder();

    for (String val : values) {
      builder = builder.add(val);
    }

    writer = writer.add(name, builder.build());
    return this;
  }

  @Override
  public YAMLWriter collectionOfYamlNode(String name, Collection<YamlNode> values) {
    YamlSequenceBuilder builder = Yaml.createYamlSequenceBuilder();

    for (YamlNode val : values) {
      builder = builder.add(val);
    }

    writer = writer.add(name, builder.build());
    return this;
  }

  @Override
  public String getOutput() {
    return writer.build().toString();
  }

  @Override
  public void nullValue(String name) {
    writer = writer.add(name, "~");
  }

  @Override
  public YamlMappingBuilder getWriter() {
    return writer;
  }
}
