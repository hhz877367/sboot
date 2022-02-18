package com.baizhi.entity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HHZaspect {
    @Before("execution(public void com.baizhi.entity.AppConfig.test())")
    public  void zhouyuBefore(JoinPoint joinPoint){
      System.out.println("zhouyuBefore");
    }
}
