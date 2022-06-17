package com.hhz.jvm;

import com.baizhi.entity.Student;
// -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations

public class GCTest {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for(int i=0;i<1000000000;i++){
            alloc();
        }
        long l1= System.currentTimeMillis();
        System.out.println(l1-l);
    }
    public static void alloc(){
        Student student = new Student();
        student.setAge("zs");
        student.setSname("ls");
    }
}
