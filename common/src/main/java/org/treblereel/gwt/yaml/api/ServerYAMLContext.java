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

import org.treblereel.gwt.jakarta.utils.GwtIncompatible;
import org.treblereel.gwt.yaml.api.internal.deser.bean.DefaultMapLike;

/** ServerYAMLContext class. */
public class ServerYAMLContext extends JsYAMLContext {

  /** {@inheritDoc} */
  @GwtIncompatible
  @Override
  public MapLikeFactory mapLikeFactory() {
    return DefaultMapLike::new;
  }

  /** {@inheritDoc} */
  @GwtIncompatible
  @Override
  public YAMLSerializerParameters defaultSerializerParameters() {
    return ServerYAMLSerializerParameters.DEFAULT;
  }

  /** {@inheritDoc} */
  @GwtIncompatible
  @Override
  public YAMLDeserializerParameters defaultDeserializerParameters() {
    return ServerYAMLDeserializerParameters.DEFAULT;
  }

  /** {@inheritDoc} */
  @GwtIncompatible
  @Override
  public YAMLSerializerParameters newSerializerParameters() {
    return new ServerYAMLSerializerParameters();
  }

  /** {@inheritDoc} */
  @GwtIncompatible
  @Override
  public YAMLDeserializerParameters newDeserializerParameters() {
    return new ServerYAMLDeserializerParameters();
  }
}
