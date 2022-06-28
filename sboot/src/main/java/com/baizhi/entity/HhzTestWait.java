package com.baizhi.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
public class HhzTestWait {
    private String name;
    private String age;
    public static HhzTestWait hhz= new HhzTestWait();

    public void test(){
        System.out.println(hhz);
    }
}
