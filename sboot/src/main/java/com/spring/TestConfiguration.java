package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public  UserService userService(){
        System.out.println("测试TestConfiguration");
        System.out.println(orderService());
        System.out.println(orderService());
        System.out.println("测试");
        return  new UserService();
    }

    @Bean
    public  OrderService orderService(){
        return  new OrderService();
    }

    public void test(){
        System.out.println(orderService());
        System.out.println(orderService());
        System.out.println(orderService());

    }

}
