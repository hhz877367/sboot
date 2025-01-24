package com.baizhi.enums;

import com.baizhi.entity.Student;

public class Test {
    public static void main(String[] args) {
       String s1 = new String("he") + new String("llo"); //导致常量池没有 hello 这个对象
        String s2 = s1.intern();
        System.out.println(s1 == s2);

    }

}
