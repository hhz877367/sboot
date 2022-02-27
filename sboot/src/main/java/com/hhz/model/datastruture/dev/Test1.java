package com.hhz.model.datastruture.dev;

import com.baizhi.entity.Student;

public class Test1 {

    public static void main(String[] args) {
        int a=1;
        test1(a);
        System.out.println(a);

        Student student = new Student();
        student.setAge("10");
        test2(student);
        System.out.println(student.getAge());

    }

    public static void test1(int a){
        a=2;
    }
    public static void test2 (Student stu){
        stu.setAge("19");
    }



}
