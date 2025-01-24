package com.baizhi.util;

import com.baizhi.entity.Student;

public class TestCreateSingleObject {
    static Student student=null;
    public static void main(String[] args) {



        for(int i=0;i<100;i++){
            new Thread(()->{
                if(student==null){
                    student=new Student();
                    student.setSname("zs");
                }
                System.out.println(student.getSname());
            }).start();

        }
    }
}
