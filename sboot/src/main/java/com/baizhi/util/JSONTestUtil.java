package com.baizhi.util;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Student;

import java.util.Date;

public class JSONTestUtil {
    public static void main(String[] args) {
        Student student = new Student();
        student.setSname("张三");
        student.setId(1);
        student.setAge("11");
        student.setScore("11");
        student.setVersion(1);
        student.setCount(1);
        student.setSex(1);
        student.setBirth(new Date());
        //java 对象转JSON字符串
        System.out.println(student.toString());
        String jsonObjectstr= JSONObject.toJSONString(student);

        System.out.println(jsonObjectstr);
        //java 对象转JSON对象
        JSONObject jsonObject =(JSONObject) JSONObject.toJSON(student);

        System.out.println(jsonObject);
        //JSON对象转java对象
        Student student1 = jsonObject.toJavaObject(Student.class);
        System.out.println(student1);
        //json字符串转换成java对象
        Student student2 = JSONObject.parseObject(jsonObjectstr, Student.class);
        System.out.println(student2);




    }
}
