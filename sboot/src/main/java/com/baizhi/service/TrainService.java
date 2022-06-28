package com.baizhi.service;

import com.baizhi.entity.Train;

import java.io.UnsupportedEncodingException;

public interface TrainService {
    //测试正常topic_routing
    public void testTopicConfirm(String time) throws UnsupportedEncodingException;

    //添加任务
    public Train addTrain(String name,String startTime);

    //删除任务
    public void delelteTrain(String id);

    //修改任务
    public  void updateTrain(String id,String name,String startTime);




    //通过训练ID，定义训练的自动发送报文的接口
    public void orderTrain(String id);
    //通过训练ID，定义训练的自动发送报文的接口
    public void testDlx(Train train);

    //测试查date
    public void testTrainDate();


}
