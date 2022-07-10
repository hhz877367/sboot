package com.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserService  {


    private OrderService orderService;

    @Autowired
    private ResourceLoader resourceLoader;


    public void test(){
        System.out.println("orderService="+orderService);
        System.out.println("执行Uservice___test");
    }

    public UserService(OrderService orderService){
        this.orderService=orderService;
    }

 /*   private String aaa;

    private String bbb;*/

/*
    @Resource
    public void aaa(OrderService orderService) {
        this.aaa="测试@Resource";
        System.out.println("测试@Resource");

    }

    @Autowired
    public void bbb(OrderService orderService) {
        this.bbb="测试@Autowired";
        System.out.println("测试 @Autowired");
    }
*/

    /* @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet");
    }*/


/*    @PostConstruct
    public void testPostCounsTract(){
        System.out.println("testPostCounsTract");
    }*/
}
