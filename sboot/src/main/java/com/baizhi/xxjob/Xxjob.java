package com.baizhi.xxjob;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Xxjob {
    private String name;

    public static void main(String[] args) {
        Xxjob xxjob = new Xxjob();
    }
   // @Scheduled(cron = " */1 * * * * *")
    public  void lianxi(){
        System.out.println("aaa");
    }


}
