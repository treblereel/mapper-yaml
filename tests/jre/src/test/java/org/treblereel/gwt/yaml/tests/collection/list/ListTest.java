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

package org.treblereel.gwt.yaml.tests.collection.list;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@J2clTestInput(ListTest.class)
public class ListTest {

  private static final ListTest_ListBeanTest_YAMLMapperImpl mapper =
      ListTest_ListBeanTest_YAMLMapperImpl.INSTANCE;
  private static final String YAML =
      "id: \"-1\""
          + System.lineSeparator()
          + "values:"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA"
          + System.lineSeparator()
          + "    secondName: BBB"
          + System.lineSeparator()
          + "    age: 99"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC"
          + System.lineSeparator()
          + "      city: DDD"
          + System.lineSeparator()
          + "      street: EEE"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA2"
          + System.lineSeparator()
          + "    secondName: BBB2"
          + System.lineSeparator()
          + "    age: 992"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC2"
          + System.lineSeparator()
          + "      city: DDD2"
          + System.lineSeparator()
          + "      street: EEE2"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA3"
          + System.lineSeparator()
          + "    secondName: BBB3"
          + System.lineSeparator()
          + "    age: 993"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC3"
          + System.lineSeparator()
          + "      city: DDD3"
          + System.lineSeparator()
          + "      street: EEE3"
          + System.lineSeparator()
          + "  -"
          + System.lineSeparator()
          + "    firstName: AAA4"
          + System.lineSeparator()
          + "    secondName: BBB4"
          + System.lineSeparator()
          + "    age: 994"
          + System.lineSeparator()
          + "    address:"
          + System.lineSeparator()
          + "      country: CCC4"
          + System.lineSeparator()
          + "      city: DDD4"
          + System.lineSeparator()
          + "      street: EEE4";

  @Test
  public void testSerializeValue() throws IOException {

    ListBeanTest bean = getListBeanTest();

    String yaml = mapper.write(bean);
    assertEquals(YAML, yaml);
  }

  private ListBeanTest getListBeanTest() {
    Person test = new Person("AAA", "BBB", 99, new Address("CCC", "DDD", "EEE"));
    Person test2 = new Person("AAA2", "BBB2", 992, new Address("CCC2", "DDD2", "EEE2"));
    Person test3 = new Person("AAA3", "BBB3", 993, new Address("CCC3", "DDD3", "EEE3"));
    Person test4 = new Person("AAA4", "BBB4", 994, new Address("CCC4", "DDD4", "EEE4"));

    List<Person> list = new ArrayList<>();
    ListBeanTest bean = new ListBeanTest();
    bean.setId(-1);
    bean.setValues(list);
    list.add(test);
    list.add(test2);
    list.add(test3);
    list.add(test4);
    return bean;
  }

  @Test
  public void tesDeSerializeValue() throws IOException {
    ListBeanTest temp = mapper.read(YAML);
    assertEquals(getListBeanTest(), temp);
    assertEquals(YAML, mapper.write(temp));
  }

  @YAMLMapper
  public static class ListBeanTest {

    private int id;

    private List<ListTest.Person> values;

    public List<Person> getValues() {
      return values;
    }

    public void setValues(List<Person> values) {
      this.values = values;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ListBeanTest that = (ListBeanTest) o;
      return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
      return Objects.hash(values);
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }
  }

  public static class Person {

    private String firstName;
    private String secondName;
    private int age;
    private ListTest.Address address;

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getSecondName() {
      return secondName;
    }

    public void setSecondName(String secondName) {
      this.secondName = secondName;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public ListTest.Address getAddress() {
      return address;
    }

    public void setAddress(ListTest.Address address) {
      this.address = address;
    }

    public Person() {}

    public Person(String firstName, String secondName, int age, ListTest.Address address) {
      this.firstName = firstName;
      this.secondName = secondName;
      this.age = age;
      this.address = address;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return age == person.age
          && Objects.equals(firstName, person.firstName)
          && Objects.equals(secondName, person.secondName)
          && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
      return Objects.hash(firstName, secondName, age, address);
    }
  }

  public static class Address {

    private String country;
    private String city;
    private String street;

    public Address() {}

    public Address(String country, String city, String street) {
      this.country = country;
      this.city = city;
      this.street = street;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public String getCountry() {
      return country;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Address address = (Address) o;
      return Objects.equals(country, address.country)
          && Objects.equals(city, address.city)
          && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
      return Objects.hash(country, city, street);
    }

    public void setCountry(String country) {
      this.country = country;
    }
  }
}
