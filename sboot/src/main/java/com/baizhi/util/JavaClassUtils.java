package com.baizhi.util;

import com.baizhi.entity.Student;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaClassUtils {
    public static void main(String[] args) {
        //创建反射的三种方式
        Student stu1 = new Student();
        Class<? extends Student> Stuclass1 = stu1.getClass();
        System.out.println(Stuclass1.getName());
        try {
            Class<?> stuClass2 = Class.forName("com.baizhi.entity.Student");
            System.out.println(stuClass2.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class stuClass3= Student.class;
        System.out.println(stuClass3.getName());

        String simpleName = stuClass3.getSimpleName();
        System.out.println("simpleName------类名字"+simpleName);
        System.out.println("是否有某项注解"+stuClass3.isAnnotationPresent(Resource.class));
        System.out.println("是否是某项注解"+stuClass3.isAnnotation());

        int modifiers = stuClass3.getModifiers();
        System.out.println(modifiers);

        //获取所有的方法
        Method[] declaredMethods = stuClass3.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("方法名"+declaredMethod);
            System.out.println("执行构造方法");
            try {
                if("test".equals(declaredMethod.getName())){
                    System.out.println("父类中的test方法----------------");
                    declaredMethod.invoke(stu1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        System.out.println("getField 只能获取public的，包括从父类继承来的字段。");
        Field[] fieldsAll = stuClass3.getFields();
        for (Field field : fieldsAll) {
            System.out.println(field);
        }
        System.out.println("getField 只能获取public的，包括从父类继承来的字段。");

        System.out.println("只获取本类中的所有字段");
        Field[] declaredFields = stuClass3.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("只获取本类中的所有字段");


        System.out.println("methonds,只包含当前父类和当前类的所有public 方法----");
        Method[] methods = stuClass3.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());

        }



    }

}
