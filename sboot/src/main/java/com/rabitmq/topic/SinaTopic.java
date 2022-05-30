package com.rabitmq.topic;

import com.baizhi.util.RabbitConstant;
import com.baizhi.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 白起老师
 * 消费者
 */
public class SinaTopic {

    public static void main(String[] args) throws IOException {
        //获取TCP长连接
        Connection connection = RabbitUtils.getConnection();
        //获取虚拟连接
        final Channel channel = connection.createChannel();
        //声明队列信息
        channel.queueDeclare(RabbitConstant.QUEUE_SINA, false, false, false, null);

        //指定队列与交换机以及routing key之间的关系
        //# 可以匹配一个或者多个，而* 只能匹配一个
        channel.queueBind(RabbitConstant.QUEUE_SINA, RabbitConstant.EXCHANGE_WEATHER_TOPIC, "");
        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA , false , new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        });
    }

}
