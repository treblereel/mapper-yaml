/*
 * Copyright 2017 Ahmad Bawaneh
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
package org.treblereel.gwt.yaml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.Vector;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.treblereel.gwt.yaml.api.deser.BooleanYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.CharacterYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.EnumYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.StringArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.StringYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.UUIDYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveBooleanArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveByteArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveCharacterArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveDoubleArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveFloatArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveIntegerArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveLongArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.PrimitiveShortArrayYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.Array2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveBooleanArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveByteArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveCharacterArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveDoubleArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveFloatArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveIntegerArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveLongArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.array.dd.PrimitiveShortArray2dYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.AbstractCollectionYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.AbstractListYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.AbstractQueueYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.AbstractSequentialListYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.AbstractSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.ArrayListYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.CollectionYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.EnumSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.HashSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.IterableYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.LinkedHashSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.LinkedListYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.ListYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.PriorityQueueYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.QueueYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.SetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.SortedSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.StackYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.TreeSetYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.collection.VectorYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.AbstractMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.EnumMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.HashMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.IdentityHashMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.LinkedHashMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.MapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.SortedMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.deser.map.TreeMapYAMLDeserializer;
import org.treblereel.gwt.yaml.api.ser.BooleanYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.CharacterYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.CollectionYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.EnumYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.IterableYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.StringYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.UUIDYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.VoidYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.ArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveBooleanArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveByteArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveCharacterArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveDoubleArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveFloatArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveIntegerArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveLongArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.PrimitiveShortArrayYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.Array2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveBooleanArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveByteArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveCharacterArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveDoubleArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveFloatArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveIntegerArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveLongArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.array.dd.PrimitiveShortArray2dYAMLSerializer;
import org.treblereel.gwt.yaml.api.ser.map.MapYAMLSerializer;
import org.treblereel.gwt.yaml.context.GenerationContext;

import static java.util.Objects.nonNull;
import static org.treblereel.gwt.yaml.api.deser.BaseDateYAMLDeserializer.DateYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseDateYAMLDeserializer.SqlDateYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseDateYAMLDeserializer.SqlTimeYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseDateYAMLDeserializer.SqlTimestampYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.BigDecimalYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.BigIntegerYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.ByteYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.DoubleYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.FloatYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.IntegerYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.LongYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.deser.BaseNumberYAMLDeserializer.ShortYAMLDeserializer;
import static org.treblereel.gwt.yaml.api.ser.BaseDateYAMLSerializer.DateYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseDateYAMLSerializer.SqlDateYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseDateYAMLSerializer.SqlTimeYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseDateYAMLSerializer.SqlTimestampYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.BigDecimalYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.BigIntegerYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.ByteYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.DoubleYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.FloatYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.IntegerYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.LongYAMLSerializer;
import static org.treblereel.gwt.yaml.api.ser.BaseNumberYAMLSerializer.ShortYAMLSerializer;

/**
 * <p>TypeRegistry class.</p>
 * @author vegegoku
 * @version $Id: $Id
 */
public final class TypeRegistry {

    private Map<String, ClassMapper> simpleTypes = new HashMap<>();
    private Map<String, ClassMapper> basicTypes = new HashMap<>();
    private Map<String, Class> collectionsDeserializers = new HashMap<>();
    private Map<String, Class> mapDeserializers = new HashMap<>();
    private Map<String, ClassMapper> customMappers = new HashMap<>();
    private Set<String> inActiveGenSerializers = new HashSet<>();
    private Set<String> inActiveGenDeserializers = new HashSet<>();
    private final ClassMapperFactory MAPPER = new ClassMapperFactory();
    private final Types types;
    private final Elements elements;
    private final GenerationContext context;

    public TypeRegistry(GenerationContext context) {
        this.types = context.getProcessingEnv().getTypeUtils();
        this.elements = context.getProcessingEnv().getElementUtils();
        this.context = context;

        initBasicMappers();
        initCommonMappers();
        initNumberMappers();
        initDataMappers();
        initIterableMappers();
        initMapMappers();
        initPrimitiveArraysMappers();
        initPrimitive2DArraysMappers();
        initCollectionsDeserializersMappers();
        initMapsDeserializersMappers();
    }

    private void initBasicMappers() {
        MAPPER
                .forType(boolean.class)
                .serializer(BooleanYAMLSerializer.class)
                .deserializer(BooleanYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(char.class)
                .serializer(CharacterYAMLSerializer.class)
                .deserializer(CharacterYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(byte.class)
                .serializer(ByteYAMLSerializer.class)
                .deserializer(ByteYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(double.class)
                .serializer(DoubleYAMLSerializer.class)
                .deserializer(DoubleYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(float.class)
                .serializer(FloatYAMLSerializer.class)
                .deserializer(FloatYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(int.class)
                .serializer(IntegerYAMLSerializer.class)
                .deserializer(IntegerYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(long.class)
                .serializer(LongYAMLSerializer.class)
                .deserializer(LongYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(short.class)
                .serializer(ShortYAMLSerializer.class)
                .deserializer(ShortYAMLDeserializer.class)
                .register(basicTypes);
    }

    private void initCommonMappers() {
        // Common mappers
        MAPPER
                .forType(String.class)
                .serializer(StringYAMLSerializer.class)
                .deserializer(StringYAMLDeserializer.class)
                .register(basicTypes);
        MAPPER
                .forType(Boolean.class)
                .serializer(BooleanYAMLSerializer.class)
                .deserializer(BooleanYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Character.class)
                .serializer(CharacterYAMLSerializer.class)
                .deserializer(CharacterYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(UUID.class)
                .serializer(UUIDYAMLSerializer.class)
                .deserializer(UUIDYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Enum.class)
                .serializer(EnumYAMLSerializer.class)
                .deserializer(EnumYAMLDeserializer.class)
                .register(basicTypes);
    }

    private void initNumberMappers() {
        MAPPER
                .forType(BigDecimal.class)
                .serializer(BigDecimalYAMLSerializer.class)
                .deserializer(BigDecimalYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(BigInteger.class)
                .serializer(BigIntegerYAMLSerializer.class)
                .deserializer(BigIntegerYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Byte.class)
                .serializer(ByteYAMLSerializer.class)
                .deserializer(ByteYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Double.class)
                .serializer(DoubleYAMLSerializer.class)
                .deserializer(DoubleYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Float.class)
                .serializer(FloatYAMLSerializer.class)
                .deserializer(FloatYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Integer.class)
                .serializer(IntegerYAMLSerializer.class)
                .deserializer(IntegerYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Long.class)
                .serializer(LongYAMLSerializer.class)
                .deserializer(LongYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Short.class)
                .serializer(ShortYAMLSerializer.class)
                .deserializer(ShortYAMLDeserializer.class)
                .register(basicTypes);

    }

    private void initDataMappers() {
        MAPPER
                .forType(Date.class)
                .serializer(DateYAMLSerializer.class)
                .deserializer(DateYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(java.sql.Date.class)
                .serializer(SqlDateYAMLSerializer.class)
                .deserializer(SqlDateYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Time.class)
                .serializer(SqlTimeYAMLSerializer.class)
                .deserializer(SqlTimeYAMLDeserializer.class)
                .register(basicTypes);

        MAPPER
                .forType(Timestamp.class)
                .serializer(SqlTimestampYAMLSerializer.class)
                .deserializer(SqlTimestampYAMLDeserializer.class)
                .register(basicTypes);
    }

    private void initIterableMappers() {
        MAPPER
                .forType(Iterable.class)
                .serializer(IterableYAMLSerializer.class)
                .deserializer(IterableYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Collection.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(CollectionYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractCollection.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(AbstractCollectionYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractList.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(AbstractListYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractQueue.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(AbstractQueueYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractSequentialList.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(AbstractSequentialListYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(AbstractSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(AbstractSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(ArrayList.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(ArrayListYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(EnumSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(EnumSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(HashSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(HashSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedHashSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(LinkedHashSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedList.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(LinkedListYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(List.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(ListYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(PriorityQueue.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(PriorityQueueYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Queue.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(QueueYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Set.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(SetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(SortedSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(SortedSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Stack.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(StackYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(TreeSet.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(TreeSetYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(Vector.class)
                .serializer(CollectionYAMLSerializer.class)
                .deserializer(VectorYAMLDeserializer.class)
                .register(simpleTypes);
    }

    private void initMapMappers() {
        MAPPER
                .forType(Map.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(MapYAMLDeserializer.class)
                .register(simpleTypes);
        MAPPER
                .forType(AbstractMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(AbstractMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(EnumMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(EnumMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(HashMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(HashMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(IdentityHashMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(IdentityHashMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(LinkedHashMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(LinkedHashMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(SortedMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(SortedMapYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(TreeMap.class)
                .serializer(MapYAMLSerializer.class)
                .deserializer(TreeMapYAMLDeserializer.class)
                .register(simpleTypes);
    }

    private void initPrimitiveArraysMappers() {
        MAPPER
                .forType(boolean[].class)
                .serializer(PrimitiveBooleanArrayYAMLSerializer.class)
                .deserializer(PrimitiveBooleanArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(byte[].class)
                .serializer(PrimitiveByteArrayYAMLSerializer.class)
                .deserializer(PrimitiveByteArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(char[].class)
                .serializer(PrimitiveCharacterArrayYAMLSerializer.class)
                .deserializer(PrimitiveCharacterArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(double[].class)
                .serializer(PrimitiveDoubleArrayYAMLSerializer.class)
                .deserializer(PrimitiveDoubleArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(float[].class)
                .serializer(PrimitiveFloatArrayYAMLSerializer.class)
                .deserializer(PrimitiveFloatArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(int[].class)
                .serializer(PrimitiveIntegerArrayYAMLSerializer.class)
                .deserializer(PrimitiveIntegerArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(long[].class)
                .serializer(PrimitiveLongArrayYAMLSerializer.class)
                .deserializer(PrimitiveLongArrayYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(short[].class)
                .serializer(PrimitiveShortArrayYAMLSerializer.class)
                .deserializer(PrimitiveShortArrayYAMLDeserializer.class)
                .register(simpleTypes);
    }

    private void initPrimitive2DArraysMappers() {
        MAPPER
                .forType(boolean[][].class)
                .serializer(PrimitiveBooleanArray2dYAMLSerializer.class)
                .deserializer(PrimitiveBooleanArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(byte[][].class)
                .serializer(PrimitiveByteArray2dYAMLSerializer.class)
                .deserializer(PrimitiveByteArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(char[][].class)
                .serializer(PrimitiveCharacterArray2dYAMLSerializer.class)
                .deserializer(PrimitiveCharacterArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(double[][].class)
                .serializer(PrimitiveDoubleArray2dYAMLSerializer.class)
                .deserializer(PrimitiveDoubleArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(float[][].class)
                .serializer(PrimitiveFloatArray2dYAMLSerializer.class)
                .deserializer(PrimitiveFloatArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(int[][].class)
                .serializer(PrimitiveIntegerArray2dYAMLSerializer.class)
                .deserializer(PrimitiveIntegerArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(long[][].class)
                .serializer(PrimitiveLongArray2dYAMLSerializer.class)
                .deserializer(PrimitiveLongArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(short[][].class)
                .serializer(PrimitiveShortArray2dYAMLSerializer.class)
                .deserializer(PrimitiveShortArray2dYAMLDeserializer.class)
                .register(simpleTypes);

        MAPPER
                .forType(String[].class)
                .serializer(ArrayYAMLSerializer.class)
                .deserializer(StringArrayYAMLDeserializer.class)
                .register(simpleTypes);
        MAPPER
                .forType(String[][].class)
                .serializer(Array2dYAMLSerializer.class)
                .deserializer(Array2dYAMLDeserializer.class)
                .register(simpleTypes);
    }

    private void initCollectionsDeserializersMappers() {
        collectionsDeserializers.put(AbstractCollection.class.getCanonicalName(), AbstractCollectionYAMLDeserializer.class);
        collectionsDeserializers.put(AbstractList.class.getCanonicalName(), AbstractListYAMLDeserializer.class);
        collectionsDeserializers.put(AbstractQueue.class.getCanonicalName(), AbstractQueueYAMLDeserializer.class);
        collectionsDeserializers.put(AbstractSequentialList.class.getCanonicalName(), AbstractSequentialListYAMLDeserializer.class);
        collectionsDeserializers.put(AbstractSet.class.getCanonicalName(), AbstractSetYAMLDeserializer.class);
        collectionsDeserializers.put(ArrayList.class.getCanonicalName(), ArrayListYAMLDeserializer.class);
        collectionsDeserializers.put(Collection.class.getCanonicalName(), CollectionYAMLDeserializer.class);
        collectionsDeserializers.put(EnumSet.class.getCanonicalName(), EnumSetYAMLDeserializer.class);
        collectionsDeserializers.put(HashSet.class.getCanonicalName(), HashSetYAMLDeserializer.class);
        collectionsDeserializers.put(Iterable.class.getCanonicalName(), IterableYAMLDeserializer.class);
        collectionsDeserializers.put(LinkedHashSet.class.getCanonicalName(), LinkedHashSetYAMLDeserializer.class);
        collectionsDeserializers.put(LinkedList.class.getCanonicalName(), LinkedListYAMLDeserializer.class);
        collectionsDeserializers.put(List.class.getCanonicalName(), ListYAMLDeserializer.class);
        collectionsDeserializers.put(PriorityQueue.class.getCanonicalName(), PriorityQueueYAMLDeserializer.class);
        collectionsDeserializers.put(Queue.class.getCanonicalName(), QueueYAMLDeserializer.class);
        collectionsDeserializers.put(Set.class.getCanonicalName(), SetYAMLDeserializer.class);
        collectionsDeserializers.put(SortedSet.class.getCanonicalName(), SortedSetYAMLDeserializer.class);
        collectionsDeserializers.put(Stack.class.getCanonicalName(), StackYAMLDeserializer.class);
        collectionsDeserializers.put(TreeSet.class.getCanonicalName(), TreeSetYAMLDeserializer.class);
        collectionsDeserializers.put(Vector.class.getCanonicalName(), VectorYAMLDeserializer.class);
    }

    private void initMapsDeserializersMappers() {
        mapDeserializers.put(Map.class.getName(), MapYAMLDeserializer.class);
        mapDeserializers.put(AbstractMap.class.getName(), AbstractMapYAMLDeserializer.class);
        mapDeserializers.put(EnumMap.class.getName(), EnumMapYAMLDeserializer.class);
        mapDeserializers.put(HashMap.class.getName(), HashMapYAMLDeserializer.class);
        mapDeserializers.put(IdentityHashMap.class.getName(), IdentityHashMapYAMLDeserializer.class);
        mapDeserializers.put(LinkedHashMap.class.getName(), LinkedHashMapYAMLDeserializer.class);
        mapDeserializers.put(SortedMap.class.getName(), SortedMapYAMLDeserializer.class);
        mapDeserializers.put(TreeMap.class.getName(), TreeMapYAMLDeserializer.class);
    }

    public void addInActiveGenSerializer(TypeMirror typeMirror) {
        inActiveGenSerializers.add(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    public void addInActiveGenDeserializer(TypeMirror typeMirror) {
        inActiveGenDeserializers.add(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    public void removeInActiveGenSerializer(TypeMirror typeMirror) {
        inActiveGenSerializers.remove(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    public void removeInActiveGenDeserializer(TypeMirror typeMirror) {
        inActiveGenDeserializers.remove(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    public boolean isInActiveGenSerializer(TypeMirror typeMirror) {
        return inActiveGenSerializers.contains(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    public boolean isInActiveGenDeserializer(TypeMirror typeMirror) {
        return inActiveGenDeserializers.contains(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    /**
     * <p>resetTypeRegistry</p>
     * <p>
     * Helper method to clean (reset) state of TypeRegistry. This action should be
     * performed on every APT run, since in some environments (such as Eclipse),
     * the processor is instantiated once and used multiple times. Without some cleanup
     * we may end up with some serializer/deserializers not generated due to TypeRegistry internal
     * state saying that they already exists.
     */
    public void resetTypeRegistry() {
        customMappers.clear();
    }

    /**
     * <p>register.</p>
     * @param mapper a {@link TypeRegistry.ClassMapper} object.
     */
    public void register(ClassMapper mapper) {
        mapper.register(simpleTypes);
    }

    /**
     * <p>isBasicType.</p>
     * @param type a {@link String} object.
     * @return a boolean.
     */
    public boolean isBasicType(String type) {
        return basicTypes.containsKey(type);
    }

    /**
     * <p>registerSerializer.</p>
     * @param type a {@link String} object.
     * @param serializer a {@link TypeElement} object.
     */
    public void registerSerializer(String type, TypeElement serializer) {
        if (customMappers.containsKey(type)) {
            customMappers.get(type).serializer = serializer;
        } else {
            ClassMapper classMapper = new ClassMapper(type);
            classMapper.serializer = serializer;
            customMappers.put(type, classMapper);
        }
    }

    /**
     * <p>registerDeserializer.</p>
     * @param type a {@link String} object.
     * @param deserializer a {@link TypeElement} object.
     */
    public void registerDeserializer(String type, TypeElement deserializer) {
        if (customMappers.containsKey(type)) {
            customMappers.get(type).deserializer = deserializer;
        } else {
            ClassMapper classMapper = new ClassMapper(type);
            classMapper.deserializer = deserializer;
            customMappers.put(type, classMapper);
        }
    }

    /**
     * <p>getCustomSerializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getCustomSerializer(TypeMirror typeMirror) {
        return getCustomSerializer(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    /**
     * <p>getCustomSerializer.</p>
     * @param type a {@link String} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getCustomSerializer(String type) {
        if (containsSerializer(type)) {
            return customMappers.get(type).serializer;
        }
        throw new TypeSerializerNotFoundException(type);
    }

    /**
     * <p>containsSerializer.</p>
     * @param typeName a {@link String} object.
     * @return a boolean.
     */
    public boolean containsSerializer(String typeName) {
        return nonNull(customMappers.get(typeName)) && nonNull(customMappers.get(typeName).serializer);
    }

    /**
     * <p>getCustomDeserializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getCustomDeserializer(TypeMirror typeMirror) {
        return getCustomDeserializer(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    /**
     * <p>getCustomDeserializer.</p>
     * @param type a {@link String} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getCustomDeserializer(String type) {
        if (containsDeserializer(type)) {
            return customMappers.get(type).deserializer;
        }
        throw new TypeDeserializerNotFoundException(type);
    }

    /**
     * <p>containsDeserializer.</p>
     * @param typeName a {@link String} object.
     * @return a boolean.
     */
    public boolean containsDeserializer(String typeName) {
        return nonNull(customMappers.get(typeName)) && nonNull(customMappers.get(typeName).deserializer);
    }

    /**
     * <p>getSerializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getSerializer(TypeMirror typeMirror) {
        return getSerializer(typeMirror.toString());
    }

    public TypeElement getSerializer(String typeName) {
        if (basicTypes.containsKey(typeName)  || simpleTypes.containsKey(typeName)) {
            return get(typeName).serializer;
        }
        throw new TypeSerializerNotFoundException(typeName);
    }

    /**
     * <p>get.</p>
     * @param typeName a {@link String} object.
     * @return a {@link TypeRegistry.ClassMapper} object.
     */
    public ClassMapper get(String typeName) {
        if (isSimpleType(typeName)) {
            return simpleTypes.get(typeName);
        }
        return basicTypes.get(typeName);
    }

    public boolean isSimpleType(String type) {
        return simpleTypes.containsKey(type);
    }

    /**
     * <p>getDeserializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link TypeElement} object.
     */
    public TypeElement getDeserializer(TypeMirror typeMirror) {
        return getDeserializer(typeMirror.toString());
    }

    public TypeElement getDeserializer(String typeName) {
        if (basicTypes.containsKey(typeName) || simpleTypes.containsKey(typeName)) {
            return get(typeName).deserializer;
        }
        throw new TypeDeserializerNotFoundException(typeName);
    }

    /**
     * <p>getCollectionDeserializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link Class} object.
     */
    public Class<?> getCollectionDeserializer(TypeMirror typeMirror) {
        return getCollectionDeserializer(asNoneGeneric(typeMirror));
    }

    private Class<?> getCollectionDeserializer(String collectionType) {
        if (collectionsDeserializers.containsKey(collectionType)) {
            return collectionsDeserializers.get(collectionType);
        }
        throw new TypeDeserializerNotFoundException(collectionType);
    }

    private String asNoneGeneric(TypeMirror type) {
        return types.asElement(type).toString().replace("<E>", "");
    }

    /**
     * <p>getMapDeserializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a {@link Class} object.
     */
    public Class<?> getMapDeserializer(TypeMirror typeMirror) {
        return getMapDeserializer(asNoneGeneric(typeMirror));
    }

    private Class<?> getMapDeserializer(String mapType) {
        if (mapDeserializers.containsKey(mapType)) {
            return mapDeserializers.get(mapType);
        }
        throw new TypeDeserializerNotFoundException(mapType);
    }

    /**
     * <p>containsSerializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a boolean.
     */
    public boolean containsSerializer(TypeMirror typeMirror) {
        return containsSerializer(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    /**
     * <p>containsDeserializer.</p>
     * @param typeMirror a {@link TypeMirror} object.
     * @return a boolean.
     */
    public boolean containsDeserializer(TypeMirror typeMirror) {
        return containsDeserializer(context.getTypeUtils().stringifyTypeWithPackage(typeMirror));
    }

    private static class TypeSerializerNotFoundException extends RuntimeException {

        TypeSerializerNotFoundException(String typeName) {
            super(typeName);
        }
    }

    private static class TypeDeserializerNotFoundException extends RuntimeException {

        TypeDeserializerNotFoundException(String typeName) {
            super(typeName);
        }
    }

    class ClassMapperFactory {

        ClassMapper forType(Class<?> clazz) {
            return new ClassMapper(clazz);
        }
    }

    public class ClassMapper {

        private final String clazz;

        private TypeElement serializer;

        private TypeElement deserializer;

        private ClassMapper(Class clazz) {
            this.clazz = clazz.getCanonicalName();
        }

        private ClassMapper(String type) {
            this.clazz = type;
        }

        private ClassMapper serializer(Class serializer) {
            this.serializer = elements.getTypeElement(serializer.getCanonicalName());
            return this;
        }

        private ClassMapper deserializer(Class deserializer) {
            this.deserializer = elements.getTypeElement(deserializer.getCanonicalName());
            return this;
        }

        private ClassMapper register(Map<String, ClassMapper> registry) {
            registry.put(this.clazz, this);
            return this;
        }

        @Override
        public String toString() {
            return "ClassMapper{" +
                    "clazz='" + clazz + '\'' +
                    ", serializer=" + serializer != null ? serializer.toString() : "" +
                    ", deserializer=" + deserializer != null ? deserializer.toString() : "" +
                    '}';
        }
    }
}
