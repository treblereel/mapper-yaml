/*
 * Copyright © 2022 Treblereel
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

import java.io.IOException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * YAMLDeserializationContext interface.
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLDeserializationContext {

  /**
   * isReadUnknownEnumValuesAsNull.
   *
   * @return a boolean.
   */
  boolean isReadUnknownEnumValuesAsNull();

  /**
   * isUseBrowserTimezone.
   *
   * @return a boolean.
   */
  boolean isFailOnUnknownProperties();

  /**
   * newYAMLReader.
   *
   * @param input a {@link String} object.
   * @return a {@link YAMLReader} object.
   */
  YAMLReader newYAMLReader(String input) throws IOException;
}
