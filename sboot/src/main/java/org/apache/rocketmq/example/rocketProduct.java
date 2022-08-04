package org.apache.rocketmq.example;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

public class rocketProduct {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessageInTransaction(String topic, String msg) throws InterruptedException {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(msg).build();
            String destination = topic + ":" + tags[i % tags.length];
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, destination);
            System.out.printf("%s%n", sendResult);
            Thread.sleep(10);
        }
    }
    public void sendMessageInTransaction2(String topic, String msg) throws InterruptedException {

    }
}
