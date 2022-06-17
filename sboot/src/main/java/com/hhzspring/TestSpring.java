package com.hhzspring;

import com.baizhi.entity.TestReflact;
import com.baizhi.entity.Train;
import com.hhzspring.config.HhzAnnotationConfigApplicationContext;
import com.hhzspring.config.HhzAutowired;
import lombok.Data;

import java.lang.reflect.Modifier;

public class TestSpring {
    public static void main(String[] args) {
     /*   HhzAnnotationConfigApplicationContext context = new HhzAnnotationConfigApplicationContext(HHzAppCnfig.class);
        UserService personService = (UserService)context.getBean("userService");
        personService.test();*/

       //@lookup("user")
        Class<? extends TestReflact> clazz = TestReflact.class;
        boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
        System.out.println(isAbstract);
        //
        Class<?>[] interfaces = clazz.getInterfaces();


    }
}
