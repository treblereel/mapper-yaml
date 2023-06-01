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
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;
import org.treblereel.gwt.yaml.api.annotation.YamlPropertyOrder;

@YAMLMapper
@JsType
@YamlPropertyOrder({
  "name",
  "type",
  "actions",
  "usedForCompensation",
  "transition",
  "end",
  "onErrors",
  "compensatedBy",
  "stateDataFilter",
  "timeouts",
  "eventTimeout",
  "actionMode",
  "metadata"
})
public class OperationState extends State {

  public static final String TYPE_OPERATION = "operation";

  public String actionMode;

  public ActionNode[] actions;

  public Boolean usedForCompensation;

  public OperationState() {
    this.type = TYPE_OPERATION;
  }

  public String getActionMode() {
    return actionMode;
  }

  public OperationState setActionMode(String actionMode) {
    this.actionMode = actionMode;
    return this;
  }

  public ActionNode[] getActions() {
    return actions;
  }

  public OperationState setActions(ActionNode[] actions) {
    this.actions = actions;
    return this;
  }

  public Boolean isUsedForCompensation() {
    return usedForCompensation;
  }

  public OperationState setUsedForCompensation(Boolean usedForCompensation) {
    this.usedForCompensation = usedForCompensation;
    return this;
  }
}
