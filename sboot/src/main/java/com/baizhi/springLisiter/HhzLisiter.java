package com.baizhi.springLisiter;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HhzLisiter   {
    @Async
    @EventListener(HHZLisiterObject.class)
    public void onApplicationEvent(HHZLisiterObject hhzLisiterObject) {
        Object source = hhzLisiterObject.getSource();
        System.out.println(source.toString());
    }
}
