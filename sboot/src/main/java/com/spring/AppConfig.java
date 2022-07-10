package com.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;

@ComponentScan("com.spring")
public class AppConfig {
/*  @PostConstruct
  public  void a(){

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("执行初始化对象后的方法");
  }*/

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        return messageSource;
    }


}
