package com.ai.wxy.frame.springboot.services.service.mq.ext;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.wxy.frame.springboot.services.service.mq.IQueueFactory;
@Component("springbootMqSend")
public class AmqpMsgSend implements IQueueFactory{
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private TopicExchange topicExchangeDurable;
    @Autowired
    public AmqpMsgSend(RabbitTemplate rabbitTemplate){
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
    public void sendMsg(String msg,String exchange,String msgType){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());  
        rabbitTemplate.convertAndSend(exchange, msgType, msg, correlationId);
//        Queue queue = new Queue(UUID.randomUUID().toString().replace("-", ""),false);
//        BindingBuilder.bind(queue).to(topicExchangeDurable).with(AmqpConfig.TOPICROUTINGKEY);
    }

}
