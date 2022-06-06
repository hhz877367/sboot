package com.baizhi.rabbitmq.config;

import com.alibaba.druid.sql.visitor.functions.Bin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Configuration
public class DelayConfig {
  /*  public static final String QUEUE_NAME="delay_queue";
    public static final String EXCHANGGE_NAME="test_delay_exchange";

    @Bean
    public CustomExchange delayExchage(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return  new CustomExchange(EXCHANGGE_NAME,"x-delayed-message",true,false,map);
    }

    @Bean
    public Queue delayQueue(){

        return  new Queue(QUEUE_NAME,true);
    }
    
    @Bean
    public Binding delayBinding(){

        return BindingBuilder.bind(delayQueue()).to(delayExchage()).with(QUEUE_NAME).noargs();
    }
*/
}
