package com.baizhi.rabbitmq;

import com.baizhi.dao.TrainDao;
import com.baizhi.entity.Train;
import com.baizhi.rabbitmq.config.DelayConfigPeople;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ConcumerReceiver {
	@RabbitListener(queues="direct_queue")
	public void topicReceiveqPeople(String message) {
		// 判断数据库里的任务是否已过期
		System.out.println("开始统计训练数据----redis---最终结果");
	}

	@RabbitListener(queues="direct_queue")
	public void topicReceiveqPeople2(String message) {
		// 判断数据库里的任务是否已过期
		System.out.println("开始统计训练数据----redis---最终结果222");
	}
}
