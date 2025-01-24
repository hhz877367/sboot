package com.rabitmq.pubsub;

import com.baizhi.util.RabbitConstant;
import com.baizhi.util.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author 白起老师
 * 发布者
 */
public class WeatherBureau {


    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();

        //第一个参数交换机名字   其他参数和之前的一样
        //参数一 交换机名称
        //参数二 routing key
        //参数三  额外参数
        //参数4   字符串bytes字节数组
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"" , null , input.getBytes());

        channel.close();
        connection.close();
    }
}
