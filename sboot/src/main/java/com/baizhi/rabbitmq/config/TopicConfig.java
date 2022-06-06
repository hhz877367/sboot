package com.baizhi.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/*
Topics模式  交换机类型 topic
* */
@Configuration
public class TopicConfig {
	public static final String DIRECT_EXCHANGE_NAME = "direct.exchange.name";
	public static final String DIRECT_QUEUE_NAME = "direct.queue.name";
	public static final String DIRECT_ROUTING_KEY_NAME = "direct.routing.key.name";

	public static final String DIRECT_DL_EXCHANGE_NAME = "direct.dl.exchange.name";
	public static final String DIRECT_DL_QUEUE_NAME = "direct.dl.queue.name";
	public static final String DIRECT_DL_ROUTING_KEY_NAME = "direct.dl.routing.key.name";

/*
	@Bean
	DirectExchange directExchangeName() {
		return new DirectExchange(DIRECT_EXCHANGE_NAME);
	}

	*/
/**
	 * 初始化Queue，设置私信交换机、路右键、过期时间
	 * QueueBuilder.durable(DIRECT_QUEUE_NAME)
	 *                 .withArgument("x-dead-letter-exchange",DIRECT_DL_EXCHANGE_NAME)
	 *                 .withArgument("x-dead-letter-routing-key",DIRECT_DL_ROUTING_KEY_NAME)
	 *                 .withArgument("x-message-ttl",10000)
	 *                 .build();
	 *//*

	@Bean
	Queue directQueueName() {
		Map<String, Object> map = new HashMap<>();
		map.put("x-dead-letter-exchange", DIRECT_DL_EXCHANGE_NAME);
		map.put("x-dead-letter-routing-key", DIRECT_DL_ROUTING_KEY_NAME);
		map.put("x-max-priority", 200);
		return new Queue(DIRECT_QUEUE_NAME, true, false, false, map);
	}

	@Bean
	Binding directBindingName() {
		return BindingBuilder.bind(directQueueName()).to(directExchangeName()).with(DIRECT_ROUTING_KEY_NAME);
	}

	@Bean
	DirectExchange directDlExchange() {
		return new DirectExchange(DIRECT_DL_EXCHANGE_NAME);
	}

	@Bean
	Queue directDLQueue() {
		return new Queue(DIRECT_DL_QUEUE_NAME);
	}

	@Bean
	Binding directDLBinding() {
		return BindingBuilder.bind(directDLQueue()).to(directDlExchange()).with(DIRECT_DL_ROUTING_KEY_NAME);
	}

*/



	/*String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
	*//**
	 * 声明了这些死信在转发时携带的 routing-key 名称
	 *//*
	String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	*//**
	 * 消息过期后会转到该队列中，需要消费者监听并消费
	 *//*
	String CANCELORDER_CONSUME_QUEUE = "cancelOrderConsumeQueue";
	*//**
	 * 声明了生产者在发送消息时携带的 routing-key 名称
	 *//*
	String CANCELORDER_SEND_ROUTINGKEY = "cancelOrderSendRoutingKey";
	*//**
	 * 声明了生产者在发送消息时携带的 Queue 名称
	 *//*
	String CANCELORDER_QUEUE = "train_normal_queue";


	//声明正常队列
	@Bean
	public Queue topicQ1() {
		Map<String, Object> params = new HashMap<>();
		// x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
		params.put("x-dead-letter-exchange","x_dead_changge");
		// x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
		params.put("x-dead-letter-routing-key","dlxchangsha.cancel");
		return new Queue(CANCELORDER_QUEUE, true, false, false,params);
	}

	//声明正常exchange
	@Bean
	public TopicExchange setTopicExchange() {
		return new TopicExchange("topicExchange");
	}

	//声明正常binding，需要声明一个roytingKey
	@Bean
	public Binding bindTopicHebei1() {
		return BindingBuilder.bind(topicQ1()).to(setTopicExchange()).with("changsha.#");
	}


	//声明死信队列
	@Bean
	public Queue dlxTopicQ1() {
		return new Queue("dlx_topic_sb_mq_q1");
	}

	//声明死信exchange
	@Bean
	public TopicExchange setdlxTopicExchange() {

		return new TopicExchange("x_dead_changge");
	}

	//死信队列绑定死信交换机
	@Bean
	public Binding binddlxTopicHebei1() {
		return BindingBuilder.bind(dlxTopicQ1()).to(setdlxTopicExchange()).with("dlxchangsha.#");
	}

*/



}
