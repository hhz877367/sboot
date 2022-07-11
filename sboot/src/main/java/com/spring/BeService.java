package com.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

public class BeService  implements DisposableBean {
    public String strobject(){
      return   new String("123");
    }

    public Integer inObject(){
        String s1 = strobject();
        String s2 = strobject();
        System.out.println(s1==s2);
        return Integer.valueOf(s1);
    }

    public void a(){
        System.out.println("Bean销毁前执行的方法");
    }

    public void destroy() throws Exception {

    }
}
