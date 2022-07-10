package com.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Configuration
public class BeService  implements DisposableBean {
    @Bean
    public String strobject(){
      return   new String("123");
    }

    @Bean
    public Integer inObject(){
        String s1 = strobject();
        String s2 = strobject();
        System.out.println(s1==s2);
        return Integer.valueOf(s1);
    }

    @PreDestroy
    public void a(){
        System.out.println("Bean销毁前执行的方法");
    }

    @Override
    public void destroy() throws Exception {

    }
}
