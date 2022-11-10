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

package org.treblereel.gwt.yaml.api.internal.deser;

import com.amihaiemil.eoyaml.YamlMapping;
import org.treblereel.gwt.yaml.api.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.YAMLDeserializer;

/**
 * Default {@link YAMLDeserializer} implementation for {@link java.lang.Boolean}.
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public class BooleanYAMLDeserializer extends YAMLDeserializer<Boolean> {

  private static final BooleanYAMLDeserializer INSTANCE = new BooleanYAMLDeserializer();

  private BooleanYAMLDeserializer() {}

  /**
   * getInstance
   *
   * @return an instance of {@link BooleanYAMLDeserializer}
   */
  public static BooleanYAMLDeserializer getInstance() {
    return INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public Boolean doDeserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx) {
    return doDeserialize(yaml.string(key), ctx);
  }

  @Override
  public Boolean doDeserialize(String value, YAMLDeserializationContext ctx) {
    return Boolean.valueOf(value);
  }
}
