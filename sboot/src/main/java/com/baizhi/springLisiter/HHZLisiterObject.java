package com.baizhi.springLisiter;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class HHZLisiterObject extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public HHZLisiterObject(Object source) {
        super(source);
        System.out.println("我是被监听的");
    }
}
