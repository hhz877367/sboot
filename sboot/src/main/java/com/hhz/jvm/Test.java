package com.hhz.jvm;

import com.baizhi.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        for(int i=0;i<1000000000;i++){
            Student student = new Student();
            double random = Math.random();
            student.setAge(random+"");
            list.add(student);
        }
    }
    public static void add( List<Student> list ){
        Student student = new Student();
        double random = Math.random();
        student.setAge(random+"");
        list.add(student);
    }
}
