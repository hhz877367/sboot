package com.baizhi.controller;

import ch.qos.logback.core.spi.ContextAware;
import com.baizhi.constant.AjaxResult;
import com.baizhi.service.Impl.TrainServiceServiceImpl;
import com.baizhi.service.TrainService;
import com.baizhi.util.RemoveBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/train")
public class TrainController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    @Resource
    private RemoveBean removeBean;
    @GetMapping("/testTopicConfirm")
    public AjaxResult testTopicConfirm(String time){
        System.out.println(time);
        try {
           // trainService.testTopicConfirm(time);
            removeBean.addBean("trainServiceServiceImpl",TrainServiceServiceImpl.class);
            TrainServiceServiceImpl trainServiceServiceImpl = applicationContext.getBean("trainServiceServiceImpl",TrainServiceServiceImpl.class);
            System.out.println(trainServiceServiceImpl.toString());
            trainServiceServiceImpl.delelteTrain("1");
            removeBean.removeBean("trainServiceServiceImpl");
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("添加任务成功");
    }

    //增加任务
    @GetMapping("/addTrain")
    public AjaxResult addTrain(String trainName,String startTime){
        try {
           // trainService.addTrain(trainName,startTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("添加任务成功");
    }

    //删除任务
    @GetMapping("/deleteTrain")
    public AjaxResult deleteTrain(String id){
        try {
          //  trainService.delelteTrain(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("删除成功");
    }

    //修改任务
    @GetMapping("/updateTrain")
    public AjaxResult updateTrain(String id,String name,String startTime){
        try {
        //    trainService.updateTrain(id,name,startTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("删除成功");
    }

    //删除任务



    @GetMapping("/trainSet")
    public AjaxResult trainSet(String id){
        try {
        //    trainService.orderTrain(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("发送MQ成功");
    }





/*    @GetMapping("/testDlx")
    public AjaxResult testDlx(String time){
        try {
            trainService.testDlx(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("发送MQ成功");
    }*/




}
