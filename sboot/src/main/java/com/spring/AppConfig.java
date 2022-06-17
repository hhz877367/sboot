package com.spring;

import com.baizhi.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.spring")
public class AppConfig {


/*  @PostConstruct
  public  void a(){

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("执行初始化对象后的方法");
  }*/
}
