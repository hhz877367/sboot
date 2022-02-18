package com.baizhi.entity;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DePeInjectionTest {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(
        AppConfig.class);
    AppConfig appConfig = (AppConfig)config.getBean("appConfig");

    appConfig.test();



/*    if(appConfig instanceof InitializingBean){
      System.out.println("实现-----");
    }else {
      System.out.println("未实现");
    }
    System.out.println(appConfig.toString());
    Field[] fields = appConfig.getClass().getDeclaredFields();
    System.out.println();
    appConfig.test();;
    //查看 AppConfig 中有哪些注解
    for (Field field : appConfig.getClass().getDeclaredFields()) {
       if(field.isAnnotationPresent(Resource.class)){
         System.out.println("存在Resource这个注解");
       }
    }*/


  }



}
