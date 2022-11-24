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

package org.treblereel.gwt.yaml.tests.beans;

import java.util.Objects;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@YAMLMapper
public class BooleanBean {

  private boolean check;

  public boolean isCheck() {
    return check;
  }

  public void setCheck(boolean check) {
    this.check = check;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BooleanBean)) {
      return false;
    }
    BooleanBean that = (BooleanBean) o;
    return isCheck() == that.isCheck();
  }

  @Override
  public int hashCode() {
    return Objects.hash(isCheck());
  }
}
