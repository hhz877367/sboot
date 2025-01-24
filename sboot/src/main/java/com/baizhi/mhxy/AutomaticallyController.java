package com.baizhi.mhxy;

import com.baizhi.entity.HhzTestWait;
import com.baizhi.service.TrainService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@RequestMapping("/train")
@Scope("prototype")
public class AutomaticallyController {


    @Resource
    private TrainService trainService;

    @Resource
    private HhzTestWait hhzTestWait;

    @PostConstruct
    public void init() {
        // 你想在启动时执行的方法
        System.out.println("Application has started!");
    }
}
