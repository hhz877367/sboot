package com.hhz.model.datastruture;

import java.util.HashSet;

public class TestSet {

  public static void main(String[] args) {
    HashSet<String> sets = new HashSet<>();
    sets.stream().distinct();
    sets.add("aaa");
    sets.add("bb");
    sets.add("cc");
    sets.add("cc");
    sets.add("cc");
    for (String set : sets) {
      System.out.println(set);
    }

  }
}
