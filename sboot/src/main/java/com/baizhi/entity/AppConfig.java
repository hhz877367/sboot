package com.baizhi.entity;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@ComponentScan("com.baizhi.entity")
@EnableAspectJAutoProxy
public class AppConfig {
  @Autowired
  private Person person;

  private String name;
  private String age;

  public void test(){
    person.setName("zs");
    System.out.println(person.toString());
  }
/*  @PostConstruct
  public  void a(){

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("执行初始化对象后的方法");
  }*/
}
