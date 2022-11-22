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

package org.treblereel.gwt.yaml.api.internal.ser.bean;

import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * Interface hiding the actual implementation doing the bean serialization.
 *
 * @author Nicolas Morel.
 */
public interface InternalSerializer<T> {

  /**
   * serializeInternally
   *
   * @param writer a {@link YAMLWriter} object.
   * @param value a T object.
   * @param ctx a {@link YAMLSerializationContext} object.
   */
  void serializeInternally(YAMLWriter writer, T value, YAMLSerializationContext ctx);
}
