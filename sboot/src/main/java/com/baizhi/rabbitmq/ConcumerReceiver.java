package com.baizhi.rabbitmq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/*@Component*/
public class ConcumerReceiver {
	/*@RabbitListener(queues="delay_queue")*/
	public void topicReceiveqPeople(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
			System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}


	/*@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople12(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople11(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople10(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople9(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople8(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople7(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople6(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople5(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople4(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople3(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
		//	System.out.println("定时器1111---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1);
			channel.basicAck(deliveryTag,false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 判断数据库里的任务是否已过期
	}

	@RabbitListener(queues="delay_queue")
	public void topicReceiveqPeople2(String message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		try {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//	System.out.println("定时器2222---------消费delay_queue队列中的消息"+message);
			channel.basicQos(1,false);
			channel.basicAck(deliveryTag,true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
