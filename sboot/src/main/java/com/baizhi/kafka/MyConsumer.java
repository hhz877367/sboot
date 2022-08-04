package com.baizhi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/*@Component*/
public class MyConsumer {

    /**
     * @KafkaListener(groupId = "testGroup", topicPartitions = {
     *             @TopicPartition(topic = "topic1", partitions = {"0", "1"}),
     *             @TopicPartition(topic = "topic2", partitions = "0",
     *                     partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
     *     },concurrency = "6")
     *  //concurrency就是同组下的消费者个数，就是并发消费数，必须小于等于分区总数
     * @param record
     */
    @KafkaListener(topics = "topic_aaa",groupId = "zhugeGroup" )
    public void listenZhugeGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("消费者1"+value);
        //手动提交offset
        ack.acknowledge();
    }

    @KafkaListener(topics = "topic_aaa",groupId = "zhugeGroup" )
    public void listenZhugeGroup1(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("消费者2"+value);
        //手动提交offset
        ack.acknowledge();
    }

    @KafkaListener(topics = "topic_aaa",groupId = "zhugeGroup" )
    public void listenZhugeGroup2(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("消费者3"+value);
        //手动提交offset
        ack.acknowledge();
    }

    @KafkaListener(topics = "topic_aaa",groupId = "zhugeGroup0000" )
    public void listenZhugeGroupA(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("消费者A"+value);
        //手动提交offset
        ack.acknowledge();
    }

/*    @KafkaListener(topics = "my-replicated-topic",groupId = "zhugeGroup0000" )
    public void listenZhugeGroup1(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        String topic = record.topic();
        System.out.println(record);
        //手动提交offset
        ack.acknowledge();
    }*/

    //配置多个消费组
    /*@KafkaListener(topics = "my-replicated-topic",groupId = "tulingGroup")
    public void listenTulingGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        ack.acknowledge();
    }*/
}
