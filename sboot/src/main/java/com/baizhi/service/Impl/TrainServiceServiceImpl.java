package com.baizhi.service.Impl;

import com.baizhi.constant.StringUtils;
import com.baizhi.dao.TrainDao;
import com.baizhi.entity.Train;
import com.baizhi.rabbitmq.config.DelayConfig;
import com.baizhi.rabbitmq.config.NomalTopicConfig;
import com.baizhi.service.TrainService;
import com.baizhi.util.Utils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;


@Transactional
@Service
@Scope("prototype")
public class TrainServiceServiceImpl implements TrainService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private TrainDao trainDao;

    @Override
    public void testTopicConfirm(String time) throws AmqpException, UnsupportedEncodingException  {
        System.out.println("执行testConfirm");
        //进行消息发送

        for (int i = 0; i < 5; i++) {
            System.out.println("发送消息");
            rabbitTemplate.convertAndSend(NomalTopicConfig.TOPIC_EXCHANGGE,"confirm","message Confirm...",new CorrelationData("全局变量哈哈哈"));
        }
/*
        //进行睡眠操作
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public Train addTrain(String name, String startTime) {
        Train train = new Train();
        if(StringUtils.isNotEmpty(name)){
            train.setProject(name);
        }
        if(StringUtils.isNotEmpty(startTime)){
            train.setStartTime(startTime);
        }
        train.setEndTime("2021-04-21 15:30:00");
        int trainID = trainDao.insertTrain(train);
        if(trainID>0){
            testDlx(train);
            return train;
        }else {
            final Exception exception = new Exception();
            exception.printStackTrace();
            return train;
        }

    }

    @Override
    public void delelteTrain(String id) {
        int i = trainDao.deleteById(id);
        if(i>0){
            System.out.println("删除任务成功");
        }
    }

    @Override
    public void updateTrain(String id, String name, String startTime) {
        //把原任务删除，新创建一个任务
        int i = trainDao.deleteById(id);
        if(i>0){
            Train train = addTrain(name, startTime);
            if(train!=null){
                System.out.println("修改成功");
            }
        }
    }

    @Override
    public void orderTrain(String id) {
      /*  Train train = trainDao.getTrainById(id);
        if(train!=null){
            String startTime = train.getStartTime();
            System.out.println("得到Train表中的startTime为"+startTime);
            // 消息后处理对象,设置一些消息的参数信息
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //1.设置message的信息
                    // 第二个方法：消息的过期时间 ,5秒之后过期
                    Date date = new Date();
                    String newDate = Utils.getStringDateByFormDefin(date, "YYYY-MM-dd HH:mm:ss");
                    System.out.println("当前时间为:"+newDate);
                    long timeDiffByStr = Utils.getTimeDiffByStr(train.getStartTime(), newDate, "YYYY-MM-dd HH:mm:ss");
                    System.out.println("当前时间差为:"+timeDiffByStr);
                    message.getMessageProperties().setExpiration(timeDiffByStr+"");
                    message.getMessageProperties().setContentEncoding("UTF-8");
                    //message.getMessageProperties().setHeader("x-delay",timeDiffByStr);
                    //2.返回该消息
                    return message;
                }
            };
            MsgSendConfirmCallBack msgSendConfirmCallBack = new MsgSendConfirmCallBack();
            rabbitTemplate.setConfirmCallback(msgSendConfirmCallBack);
            rabbitTemplate.convertAndSend("topicExchange","changsha.01",startTime,messagePostProcessor);
        }*/
    }

    //发送train对象到消息队列中
    @Override
    public void testDlx(Train train) {
      // 给单个消息动态设置过期时间
    /*    if(StringUtils.isNotEmpty(train.getStartTime())){
            Date date = new Date();
            String newDate = Utils.getStringDateByFormDefin(date, "YYYY-MM-dd HH:mm:ss");
            System.out.println("当前时间为:"+newDate);
            long timeDiffByStr = Utils.getTimeDiffByStr(train.getStartTime(), newDate, "YYYY-MM-dd HH:mm:ss");
            System.out.println("设置的延迟时间搓为:"+timeDiffByStr);

          //  MsgSendConfirmCallBack msgSendConfirmCallBack = new MsgSendConfirmCallBack();
         //   rabbitTemplate.setConfirmCallback(msgSendConfirmCallBack);
            rabbitTemplate.convertAndSend(DelayConfig.EXCHANGGE_NAME, DelayConfig.QUEUE_NAME,train, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    //使用延迟插件只需要在消息的header中添加x-delay属性，值为过期时间，单位毫秒
                    message.getMessageProperties().setHeader("x-delay",timeDiffByStr);
                    return message;
                }
            });
        }
*/


    }

    //通过训练ID，定义训练的自动发送报文的接口

}
