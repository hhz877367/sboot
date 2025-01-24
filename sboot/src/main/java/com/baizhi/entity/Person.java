package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Person implements Serializable {
  private String name;
  private String id;
  private Integer age;
  private List<Person> list;
  private Integer listZize;

  public Person(String name, String id, Integer age) {
    this.name = name;
    this.id = id;
    this.age = age;
  }
  public Person() {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Person person = (Person) o;

    if (!name.equals(person.name)) {
      return false;
    }
    if (!id.equals(person.id)) {
      return false;
    }
    return age.equals(person.age);
  }

  @Override
  public int hashCode() {
    int result=0;
    result = Integer.parseInt(id);
    return result;
  }

}
