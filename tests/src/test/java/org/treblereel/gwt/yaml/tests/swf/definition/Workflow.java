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
import org.treblereel.gwt.yaml.api.annotation.YamlTypeDeserializer;
import org.treblereel.gwt.yaml.api.annotation.YamlTypeSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.ErrorYamlSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.EventYamlTypeSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.StartDefinitionYamlTypeSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.StateYamlSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.ValueHolderYamlTypeSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.WorkflowFunctionsYamlSerializer;
import org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml.WorkflowTimeoutsYamlSerializer;

@YAMLMapper
@JsType
@YamlPropertyOrder({"id", "version", "specVersion", "name", "description", "start", "states"})
public class Workflow {

  public String id;

  public String key;

  public String name;

  public String description;

  public String specVersion;

  public String version;

  @YamlTypeSerializer(ValueHolderYamlTypeSerializer.class)
  @YamlTypeDeserializer(ValueHolderYamlTypeSerializer.class)
  private ValueHolder constants;

  @YamlTypeSerializer(StartDefinitionYamlTypeSerializer.class)
  @YamlTypeDeserializer(StartDefinitionYamlTypeSerializer.class)
  private Object start;

  @YamlTypeSerializer(EventYamlTypeSerializer.class)
  @YamlTypeDeserializer(EventYamlTypeSerializer.class)
  private Object events; // TODO array or string

  @YamlTypeSerializer(StateYamlSerializer.class)
  @YamlTypeDeserializer(StateYamlSerializer.class)
  private State[] states;

  public Boolean keepActive;

  @YamlTypeSerializer(WorkflowFunctionsYamlSerializer.class)
  @YamlTypeDeserializer(WorkflowFunctionsYamlSerializer.class)
  private Object functions;

  public Boolean autoRetries;

  @YamlTypeSerializer(WorkflowTimeoutsYamlSerializer.class)
  @YamlTypeDeserializer(WorkflowTimeoutsYamlSerializer.class)
  private Object timeouts;

  @YamlTypeSerializer(ErrorYamlSerializer.class)
  @YamlTypeDeserializer(ErrorYamlSerializer.class)
  private Object errors;

  public Retry[] retries;

  public Workflow() {}

  public String getId() {
    return id;
  }

  public Workflow setId(String id) {
    this.id = id;
    return this;
  }

  public String getKey() {
    return key;
  }

  public Workflow setKey(String key) {
    this.key = key;
    return this;
  }

  public String getName() {
    return name;
  }

  public Workflow setName(String name) {
    this.name = name;
    return this;
  }

  public Object getStart() {
    return start;
  }

  public Workflow setStart(Object start) {
    this.start = start;
    return this;
  }

  public Object getEvents() {
    return events;
  }

  public Workflow setEvents(Object events) {
    this.events = events;
    return this;
  }

  public State[] getStates() {
    return states;
  }

  public Workflow setStates(State[] states) {
    this.states = states;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSpecVersion() {
    return specVersion;
  }

  public void setSpecVersion(String specVersion) {
    this.specVersion = specVersion;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Boolean getKeepActive() {
    return keepActive;
  }

  public void setKeepActive(Boolean keepActive) {
    this.keepActive = keepActive;
  }

  public Object getFunctions() {
    return functions;
  }

  public void setFunctions(Object functions) {
    this.functions = functions;
  }

  public Boolean getAutoRetries() {
    return autoRetries;
  }

  public void setAutoRetries(Boolean autoRetries) {
    this.autoRetries = autoRetries;
  }

  public Object getTimeouts() {
    return timeouts;
  }

  public void setTimeouts(Object timeouts) {
    this.timeouts = timeouts;
  }

  public ValueHolder getConstants() {
    return constants;
  }

  public void setConstants(ValueHolder constants) {
    this.constants = constants;
  }

  public Object getErrors() {
    return errors;
  }

  public void setErrors(Object errors) {
    this.errors = errors;
  }

  public Retry[] getRetries() {
    return retries;
  }

  public void setRetries(Retry[] retries) {
    this.retries = retries;
  }
}
