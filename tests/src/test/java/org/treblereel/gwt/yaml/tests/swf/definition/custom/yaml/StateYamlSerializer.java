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

package org.treblereel.gwt.yaml.tests.swf.definition.custom.yaml;

import org.treblereel.gwt.yaml.api.YAMLDeserializer;
import org.treblereel.gwt.yaml.api.YAMLSerializer;
import org.treblereel.gwt.yaml.api.exception.YAMLDeserializationException;
import org.treblereel.gwt.yaml.api.internal.deser.YAMLDeserializationContext;
import org.treblereel.gwt.yaml.api.internal.ser.YAMLSerializationContext;
import org.treblereel.gwt.yaml.api.node.YamlMapping;
import org.treblereel.gwt.yaml.api.node.YamlNode;
import org.treblereel.gwt.yaml.api.node.YamlSequence;
import org.treblereel.gwt.yaml.tests.swf.definition.CallbackState;
import org.treblereel.gwt.yaml.tests.swf.definition.CallbackState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.EventState;
import org.treblereel.gwt.yaml.tests.swf.definition.EventState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.ForEachState;
import org.treblereel.gwt.yaml.tests.swf.definition.ForEachState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.InjectState;
import org.treblereel.gwt.yaml.tests.swf.definition.InjectState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.OperationState;
import org.treblereel.gwt.yaml.tests.swf.definition.OperationState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.ParallelState;
import org.treblereel.gwt.yaml.tests.swf.definition.ParallelState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.SleepState;
import org.treblereel.gwt.yaml.tests.swf.definition.SleepState_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.State;
import org.treblereel.gwt.yaml.tests.swf.definition.State_YamlMapperImpl;
import org.treblereel.gwt.yaml.tests.swf.definition.SwitchState;
import org.treblereel.gwt.yaml.tests.swf.definition.SwitchState_YamlMapperImpl;

public class StateYamlSerializer implements YAMLDeserializer<State>, YAMLSerializer<State> {

  @Override
  public State deserialize(YamlMapping yaml, String key, YAMLDeserializationContext ctx)
      throws YAMLDeserializationException {
    YamlMapping value = yaml.getMappingNode(key);
    if (value != null) {
      String type = value.<String>getScalarNode("type").value();
      switch (type) {
        case CallbackState.TYPE_CALLBACK:
          return CallbackState_YamlMapperImpl.INSTANCE
              .getDeserializer()
              .deserialize(value, key, ctx);
        case EventState.TYPE_EVENT:
          return EventState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, key, ctx);
        case ForEachState.TYPE_FOR_EACH:
          return ForEachState_YamlMapperImpl.INSTANCE
              .getDeserializer()
              .deserialize(value, key, ctx);
        case InjectState.TYPE_INJECT:
          return InjectState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, key, ctx);
        case OperationState.TYPE_OPERATION:
          return OperationState_YamlMapperImpl.INSTANCE
              .getDeserializer()
              .deserialize(value, key, ctx);
        case ParallelState.TYPE_PARALLEL:
          return ParallelState_YamlMapperImpl.INSTANCE
              .getDeserializer()
              .deserialize(value, key, ctx);
        case SleepState.TYPE_SLEEP:
          return SleepState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, key, ctx);
        case SwitchState.TYPE_SWITCH:
          return SwitchState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, key, ctx);
        default:
          return State_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, key, ctx);
      }
    }
    return null;
  }

  @Override
  public State deserialize(YamlNode node, YAMLDeserializationContext ctx) {
    if (node != null) {
      YamlMapping value = node.asMapping();
      String type = value.<String>getScalarNode("type").value();
      switch (type) {
        case CallbackState.TYPE_CALLBACK:
          return CallbackState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case EventState.TYPE_EVENT:
          return EventState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case ForEachState.TYPE_FOR_EACH:
          return ForEachState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case InjectState.TYPE_INJECT:
          return InjectState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case OperationState.TYPE_OPERATION:
          return OperationState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case ParallelState.TYPE_PARALLEL:
          return ParallelState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case SleepState.TYPE_SLEEP:
          return SleepState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        case SwitchState.TYPE_SWITCH:
          return SwitchState_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
        default:
          return State_YamlMapperImpl.INSTANCE.getDeserializer().deserialize(value, ctx);
      }
    }
    return null;
  }

  @Override
  public void serialize(
      YamlMapping writer, String propertyName, State obj, YAMLSerializationContext ctx) {
    if (obj instanceof CallbackState) {
      CallbackState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (CallbackState) obj, ctx);
    } else if (obj instanceof EventState) {
      EventState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (EventState) obj, ctx);
    } else if (obj instanceof ForEachState) {
      ForEachState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (ForEachState) obj, ctx);
    } else if (obj instanceof InjectState) {
      InjectState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (InjectState) obj, ctx);
    } else if (obj instanceof OperationState) {
      OperationState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (OperationState) obj, ctx);
    } else if (obj instanceof ParallelState) {
      ParallelState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (ParallelState) obj, ctx);
    } else if (obj instanceof SleepState) {
      SleepState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (SleepState) obj, ctx);
    } else if (obj instanceof SwitchState) {
      SwitchState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, propertyName, (SwitchState) obj, ctx);
    } else {
      State_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, propertyName, obj, ctx);
    }
  }

  @Override
  public void serialize(YamlSequence writer, State obj, YAMLSerializationContext ctx) {
    if (obj instanceof CallbackState) {
      CallbackState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, (CallbackState) obj, ctx);
    } else if (obj instanceof EventState) {
      EventState_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, (EventState) obj, ctx);
    } else if (obj instanceof ForEachState) {
      ForEachState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, (ForEachState) obj, ctx);
    } else if (obj instanceof InjectState) {
      InjectState_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, (InjectState) obj, ctx);
    } else if (obj instanceof OperationState) {
      OperationState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, (OperationState) obj, ctx);
    } else if (obj instanceof ParallelState) {
      ParallelState_YamlMapperImpl.INSTANCE
          .getSerializer()
          .serialize(writer, (ParallelState) obj, ctx);
    } else if (obj instanceof SleepState) {
      SleepState_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, (SleepState) obj, ctx);
    } else if (obj instanceof SwitchState) {
      SwitchState_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, (SwitchState) obj, ctx);
    } else {
      State_YamlMapperImpl.INSTANCE.getSerializer().serialize(writer, obj, ctx);
    }
  }
}
