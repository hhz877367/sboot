package com.baizhi.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/*
*
* 自定义消息队列时间
* */

/*@Configuration*/
public class DelayConfig {
    public static final String QUEUE_NAME="delay_queue";
    public static final String EXCHANGGE_NAME="test_delay_exchange";

 /*   @Bean
    public CustomExchange delayExchage(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return  new CustomExchange(EXCHANGGE_NAME,"x-delayed-message",true,false,map);
    }

    @Bean
    public Queue delayQueue(){
        return  new Queue(QUEUE_NAME,true);
    }
    
    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayQueue()).to(delayExchage()).with(QUEUE_NAME).noargs();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //设置全局处理参数
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            *//**
             * @param correlationData 相关配置信息
             * @param ack   exchange交换机 是否成功收到了消息。true 成功，false代表失败
             * @param cause 失败原因*//*
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
               System.out.println(correlationData);
                //ack 为  true表示 消息已经到达交换机
                if (ack) {
                    //接收成功
                   System.out.println("消息成功发送到exchangge成功"+correlationData);
                } else {
                    //接收失败
                    System.out.println("接受消息失败" + cause);
                    //做一些处理，让消息再次发送。
                    //进行消息发送
                    for (int i = 0; i < 5; i++) {
                        System.out.println("发送消息");
                        rabbitTemplate.convertAndSend(DelayConfig.EXCHANGGE_NAME,"confirm","message Confirm...");
                    }
                }
            }
        });
        return rabbitTemplate;
    }*/

}
