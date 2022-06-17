package com.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UserService  {

    public void test(){
        System.out.println("执行Uservice___test");
    }



   /* @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet");
    }*/


/*    @PostConstruct
    public void testPostCounsTract(){
        System.out.println("testPostCounsTract");
    }*/
}
