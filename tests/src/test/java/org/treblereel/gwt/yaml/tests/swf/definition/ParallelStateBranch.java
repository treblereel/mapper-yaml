/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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

package org.treblereel.gwt.yaml.tests.swf.definition;

import jsinterop.annotations.JsType;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeDeserializer;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.WorkflowTimeoutsYamlSerializer;

@JsType
public class ParallelStateBranch {

  public String name;

  public ActionNode[] actions;

  @YamlTypeSerializer(WorkflowTimeoutsYamlSerializer.class)
  @YamlTypeDeserializer(WorkflowTimeoutsYamlSerializer.class)
  private Object timeouts;

  public final String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final ActionNode[] getActions() {
    return actions;
  }

  public final void setActions(ActionNode[] actions) {
    this.actions = actions;
  }

  public final Object getTimeouts() {
    return timeouts;
  }

  public final void setTimeouts(Object timeouts) {
    this.timeouts = timeouts;
  }
}
