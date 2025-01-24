/*
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


*/
/*
Topics模式  交换机类型 topic
* *//*

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



}
*/
