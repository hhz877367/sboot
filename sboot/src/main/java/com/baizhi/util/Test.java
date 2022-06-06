package com.baizhi.util;

import com.baizhi.entity.Person;
import com.baizhi.entity.Student;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();
        Student student = new Student();
        student.setSname("zs");
        student.setAge("321312");
        Student s2=new Student();
        s2.setSname("ls");
        list.add(student);
        list.add(student);
        list.add(s2);
        list.add(s2);
        list.add(s2);

        List<Student> list2 = list.stream().map(e ->{
            Student stu2 = new Student();
            stu2.setSname(e.getSname());
            stu2.setAge(e.getAge());
            return stu2;
        }).collect(Collectors.toList());
        for(Student s:list2){
            System.out.println(s);
        }
    }
}
