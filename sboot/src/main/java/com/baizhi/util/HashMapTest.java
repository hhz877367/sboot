package com.baizhi.util;

import com.baizhi.entity.Person;
import java.util.HashMap;

public class HashMapTest {

  public static void main(String[] args) {
    HashMap<Object, Object> map = new HashMap<>();
    Person person1 = new Person("zs","1",22);
    person1.setName("zs");
    Person perso2 = new Person("zs1","1",22);
    perso2.setName("ls");
    map.put(person1,"aaa");
    map.put(perso2,"bbb");
    Object o = map.get(perso2);
    System.out.println(o.toString());
    Object o1 = map.get(person1);
    System.out.println(o1.toString());

  }
}
