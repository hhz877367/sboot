package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.service.TrainService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Scope("prototype")
@RequestMapping("/train")
public class TrainController {

    @Resource
    private TrainService trainService;

    //正常topic确认机制测试

    @GetMapping("/testTopicConfirm")
    public AjaxResult testTopicConfirm(String time){
        try {
            trainService.testTopicConfirm(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("添加任务成功");
    }

    //增加任务
    @GetMapping("/addTrain")
    public AjaxResult addTrain(String trainName,String startTime){
        try {
            trainService.addTrain(trainName,startTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("添加任务成功");
    }

    //删除任务
    @GetMapping("/deleteTrain")
    public AjaxResult deleteTrain(String id){
        try {
            trainService.delelteTrain(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("删除成功");
    }

    //修改任务
    @GetMapping("/updateTrain")
    public AjaxResult updateTrain(String id,String name,String startTime){
        try {
            trainService.updateTrain(id,name,startTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success("删除成功");
    }

    //删除任务



    @GetMapping("/trainSet")
    public AjaxResult trainSet(String id){
        try {
            trainService.orderTrain(id);
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
