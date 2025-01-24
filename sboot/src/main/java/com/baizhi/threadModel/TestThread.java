package com.baizhi.threadModel;

import com.baizhi.service.Impl.ExtGaSourceServiceImpl;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThread extends    Thread  {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
