package com.spring;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class TestSpringCicly  implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("spring启动成功了");
    }

    @Override
    public void stop() {
        System.out.println("sprig销毁了");
    }

    @Override
    public boolean isRunning() {
        System.out.println("spring容器正在运行啦--------");
        return false;
    }
}
