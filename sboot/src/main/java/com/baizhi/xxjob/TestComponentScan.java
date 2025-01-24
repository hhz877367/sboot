package com.baizhi.xxjob;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springscan.Mao;

import javax.annotation.Resource;

@ComponentScan("springscan")
@Component
public class TestComponentScan {
    @Resource
    private Mao mao;

    public  void test(){
        System.out.println(mao.toString());
    }
}
