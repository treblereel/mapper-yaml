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

package org.treblereel.gwt.yaml.api;

import java.io.IOException;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLReader;

/**
 * YAMLDeserializationContext interface.
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLDeserializationContext extends YAMLMappingContext {

  /**
   * isFailOnUnknownProperties.
   *
   * @return a boolean.
   */
  boolean isFailOnUnknownProperties();

  /**
   * isAcceptSingleValueAsArray.
   *
   * @return a boolean.
   */
  boolean isAcceptSingleValueAsArray();

  /**
   * isUseSafeEval.
   *
   * @return a boolean.
   */
  boolean isUseSafeEval();

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
  boolean isUseBrowserTimezone();

  /**
   * newYAMLReader.
   *
   * @param input a {@link String} object.
   * @return a {@link YAMLReader} object.
   */
  YAMLReader newYAMLReader(String input) throws IOException;

  /**
   * traceError.
   *
   * @param message a {@link String} object.
   * @return a {@link YAMLDeserializationException} object.
   */
  YAMLDeserializationException traceError(String message);

  /**
   * traceError.
   *
   * @param message a {@link String} object.
   * @param reader a {@link YAMLReader} object.
   * @return a {@link YAMLDeserializationException} object.
   */
  YAMLDeserializationException traceError(String message, YAMLReader reader);

  /**
   * traceError.
   *
   * @param cause a {@link RuntimeException} object.
   * @return a {@link RuntimeException} object.
   */
  RuntimeException traceError(RuntimeException cause);

  /**
   * traceError.
   *
   * @param cause a {@link RuntimeException} object.
   * @param reader a {@link YAMLReader} object.
   * @return a {@link RuntimeException} object.
   */
  RuntimeException traceError(RuntimeException cause, YAMLReader reader);

  /**
   * defaultParameters.
   *
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters defaultParameters();
}
