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

package org.treblereel.gwt.yaml.api;

import org.treblereel.gwt.yaml.api.internal.deser.bean.MapLike;

/**
 * YAMLContext interface.
 *
 * @author vegegoku
 * @version $Id: $Id
 */
public interface YAMLContext {

  /**
   * mapLikeFactory.
   *
   * @return a {@link YAMLContext.MapLikeFactory} object.
   */
  MapLikeFactory mapLikeFactory();

  /**
   * defaultSerializerParameters.
   *
   * @return a {@link YAMLSerializerParameters} object.
   */
  YAMLSerializerParameters defaultSerializerParameters();

  /**
   * newSerializerParameters
   *
   * @return a new instance of {@link YAMLSerializerParameters} object
   */
  YAMLSerializerParameters newSerializerParameters();

  /**
   * defaultDeserializerParameters.
   *
   * @return a {@link YAMLDeserializerParameters} object.
   */
  YAMLDeserializerParameters defaultDeserializerParameters();

  /**
   * newDeserializerParameters
   *
   * @return a new instance of {@link YAMLDeserializerParameters} object
   */
  YAMLDeserializerParameters newDeserializerParameters();

  interface MapLikeFactory {

    <T> MapLike<T> make();
  }
}
