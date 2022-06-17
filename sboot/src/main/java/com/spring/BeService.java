package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeService {
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
}
