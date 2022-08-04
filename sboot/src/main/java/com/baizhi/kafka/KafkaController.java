package com.baizhi.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController*/
public class KafkaController {



    private final static String TOPIC_NAME = "topic_woainiznn";

    private final static String TOPIC_NAME1 = "topic.quick.initial";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public void send()
    {
        System.out.println("进入send方法");
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg").addCallback(new ListenableFutureCallback(){

            @Override
            public void onSuccess(Object result) {
                System.err.println("发送消息成功" +result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex.getMessage());
            }
        } );
    }

    @GetMapping("/send2")
    public void send2(){
        for(int i=0;i<100;i++){
            int partition=i%8;
            kafkaTemplate.send(TOPIC_NAME1, partition, "key"+i, "this is a msg,我在partition"+partition+"我是消息第"+i+"个").addCallback(new ListenableFutureCallback(){

                @Override
                public void onSuccess(Object result)
                {
                  /*  System.err.println("发送消息成功：" +result.toString());*/
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println(ex.getMessage());
                }
            } );;
        }
    }

}
