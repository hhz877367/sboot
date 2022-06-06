package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestRabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/helloWorldqueue")
    public AjaxResult helloWorldqueue(){
        String message="测试发送简单模式Mq";
        //设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        try {
            rabbitTemplate.send("helloWorldqueue",new Message(message.getBytes("UTF-8"),messageProperties));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("发送成功");
    }

}
