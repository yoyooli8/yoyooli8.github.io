package com.ai.wxy.frame.springboot.services.user.service.mq.impl;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.wxy.frame.springboot.services.user.service.mq.AmqpConfig;
import com.ai.wxy.frame.springboot.services.user.service.mq.IQueueFactory;
@Component("springbootMqDirectSend")
public class SpringbootMqSend implements IQueueFactory{
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public SpringbootMqSend(@Qualifier("directRabbitTemplate")RabbitTemplate directRabbitTemplate){
        this.rabbitTemplate = directRabbitTemplate;
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


}