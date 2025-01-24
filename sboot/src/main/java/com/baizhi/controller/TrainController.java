package com.baizhi.controller;

import ch.qos.logback.core.spi.ContextAware;
import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.HhzTestWait;
import com.baizhi.service.Impl.TrainServiceServiceImpl;
import com.baizhi.service.TrainService;
import com.baizhi.util.HHzTest;
import com.baizhi.util.RefreshUtil;
import com.baizhi.util.RemoveBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/train")
@Scope("prototype")
public class TrainController {


    @Resource
    private TrainService trainService;

    @Resource
    private HhzTestWait hhzTestWait;

    @PostConstruct
    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 提交一个任务给线程池执行
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        RefreshUtil.getImgUrl();
                        RefreshUtil.reFreshPhone();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
