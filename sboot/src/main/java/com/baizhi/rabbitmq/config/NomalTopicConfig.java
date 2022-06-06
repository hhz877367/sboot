package com.baizhi.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.CorrelationDataPostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/*
Topics模式  交换机类型 topic
* */
@Configuration
public class NomalTopicConfig {


	public static final String TOPIC_NAME = "direct_queue";
	public static final String TOPIC_EXCHANGGE = "direct_exchangge";
	public static final String TOPIC_ROUTING_KEY = "confirm";

	@Bean
	DirectExchange directDlExchange() {
		return new DirectExchange(TOPIC_EXCHANGGE);
	}

	@Bean
	Queue directQueue() {
		return new Queue(TOPIC_NAME);
	}

	@Bean
	Binding directBind() {
		return BindingBuilder.bind(directQueue()).to(directDlExchange()).with(TOPIC_ROUTING_KEY);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		//设置全局处理参数
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			/**
			 * @param correlationData 相关配置信息
			 * @param ack   exchange交换机 是否成功收到了消息。true 成功，false代表失败
			 * @param cause 失败原因*/
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				System.out.println(correlationData);
				System.out.println(correlationData.toString());
				System.out.println("confirm方法被执行了....");
				//ack 为  true表示 消息已经到达交换机
				if (ack) {
					//接收成功
					System.out.println("接收成功消息" + cause);
				} else {
					//接收失败
					System.out.println("接受消息失败" + cause);
					//做一些处理，让消息再次发送。
					//进行消息发送
					for (int i = 0; i < 5; i++) {
						System.out.println("发送消息");
						rabbitTemplate.convertAndSend(NomalTopicConfig.TOPIC_EXCHANGGE,"confirm1","message Confirm...");
					}
				}
			}
		});
		return rabbitTemplate;
	}


}
