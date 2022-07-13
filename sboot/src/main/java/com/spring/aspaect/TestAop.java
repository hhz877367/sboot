package com.spring.aspaect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {

    @Before( "execution( public void com.spring.UserService.*(..))")
    public  void testBefor(JoinPoint joinPoint){
        System.out.println("testBefor之前");
    }
    @Around( "execution( public void com.spring.UserService.*(..))")
    public  void testBefo1r(JoinPoint joinPoint){
        System.out.println("testBefor——————Around");
    }
    @After( "execution( public void com.spring.UserService.*(..))")
    public  void testBefor2(JoinPoint joinPoint){
        System.out.println("testBefor之后");
    }

}
