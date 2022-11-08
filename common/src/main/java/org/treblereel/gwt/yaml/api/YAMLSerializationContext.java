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

import org.treblereel.gwt.yaml.api.exception.YAMLSerializationException;
import org.treblereel.gwt.yaml.api.stream.YAMLWriter;

/**
 * YAMLSerializationContext interface.
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLSerializationContext extends YAMLMappingContext {
  /**
   * isSerializeNulls.
   *
   * @return a boolean.
   */
  boolean isSerializeNulls();

  /**
   * isWriteDatesAsTimestamps.
   *
   * @return a boolean.
   */
  boolean isWriteDatesAsTimestamps();

  /**
   * isWriteDateKeysAsTimestamps.
   *
   * @return a boolean.
   */
  boolean isWriteDateKeysAsTimestamps();

  /**
   * isWriteNullMapValues.
   *
   * @return a boolean.
   */
  boolean isWriteNullMapValues();

  /**
   * isWriteEmptyYAMLArrays.
   *
   * @return a boolean.
   */
  boolean isWriteEmptyYAMLArrays();

  /**
   * isOrderMapEntriesByKeys.
   *
   * @return a boolean.
   */
  boolean isOrderMapEntriesByKeys();

  /**
   * isMapKeyAndValueCanonical.
   *
   * @return a boolean.
   */
  boolean isMapKeyAndValueCanonical();

  /**
   * isWrapCollections.
   *
   * @return a boolean.
   */
  boolean isWrapCollections();

  /**
   * newYAMLWriter.
   *
   * @return a {@link YAMLWriter} object.
   */
  YAMLWriter newYAMLWriter();

  /**
   * traceError.
   *
   * @param value a {@link java.lang.Object} object.
   * @param message a {@link java.lang.String} object.
   * @return a {@link YAMLSerializationException} object.
   */
  YAMLSerializationException traceError(Object value, String message);

  /**
   * traceError.
   *
   * @param value a {@link java.lang.Object} object.
   * @param message a {@link java.lang.String} object.
   * @param writer a {@link YAMLWriter} object.
   * @return a {@link YAMLSerializationException} object.
   */
  YAMLSerializationException traceError(Object value, String message, YAMLWriter writer);

  /**
   * traceError.
   *
   * @param value a {@link java.lang.Object} object.
   * @param cause a {@link java.lang.RuntimeException} object.
   * @return a {@link java.lang.RuntimeException} object.
   */
  RuntimeException traceError(Object value, RuntimeException cause);

  /**
   * traceError.
   *
   * @param value a {@link java.lang.Object} object.
   * @param cause a {@link java.lang.RuntimeException} object.
   * @param writer a {@link YAMLWriter} object.
   * @return a {@link java.lang.RuntimeException} object.
   */
  RuntimeException traceError(Object value, RuntimeException cause, YAMLWriter writer);

  /**
   * defaultParameters.
   *
   * @return a {@link YAMLSerializerParameters} object.
   */
  YAMLSerializerParameters defaultParameters();
}
