package com.rabitmq.pubsub;

import com.baizhi.util.RabbitConstant;
import com.baizhi.util.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author 白起老师
 * 消费者
 */
public class Sina {

    public static void main(String[] args) throws IOException {
        //获取TCP长连接
        Connection connection = RabbitUtils.getConnection();
        //获取虚拟连接
        final Channel channel = connection.createChannel();
        //声明队列信息
        channel.queueDeclare(RabbitConstant.QUEUE_SINA, false, false, false, null);

        //queueBind用于将队列与交换机绑定
        //参数1：队列名 参数2：交互机名  参数三：路由key（暂时用不到——路由模式才用到)
        //发布订阅模式，交换机类型是广播模式
        //路由模式下，交换机类型是定定向模式
        //通配符模式下，交换机类型是统配模式
        channel.queueBind(RabbitConstant.QUEUE_SINA, RabbitConstant.EXCHANGE_WEATHER, "");
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