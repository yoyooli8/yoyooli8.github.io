package com.ai.wxy.frame.springboot.services.service.mq.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.wxy.frame.springboot.services.service.mq.AmqpConfig;
import com.ai.wxy.frame.springboot.services.service.mq.IQueueFactory;
import com.rabbitmq.client.Connection;
@Component("springbootMqSend")
public class SpringbootMqSend implements IQueueFactory{
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public SpringbootMqSend(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData,boolean ack,String cause){
                System.out.println(" 回调id:" + correlationData);
                if (ack) {  
                    System.out.println("消息成功消费");  
                } else {  
                    System.out.println("消息消费失败:" + cause);  
                }  
            }
            
        }); 
    }
    @Override
    public void sendMsg(String msg,String msgType){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());  
        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, msgType, msg, correlationId);
    }

    @Override
    public Connection newConnection() throws IOException,TimeoutException{
        return null;
    }

}
