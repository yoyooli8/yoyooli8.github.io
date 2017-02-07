package com.ai.wxy.springboot.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.support.CorrelationData;

import com.ai.wxy.frame.springboot.services.service.mq.ext.AmqpConfig;


public class TopicExchangeTest{
    public static void main(String[] args){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");  
        connectionFactory.setUsername("tpuser");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(32771);
        connectionFactory.setPublisherConfirms(true);
        
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("myTopicTestQueue",true);
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange(AmqpConfig.TOPICEXCHANGE);
        admin.declareExchange(exchange);
        admin.declareBinding(
            BindingBuilder.bind(queue).to(exchange).with(AmqpConfig.TOPICROUTINGKEY));
        
        
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(connectionFactory);
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println("-------handleMessage-------------"+foo);
            }
        };
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myTopicTestQueue");
        container.start();
        
        
        try{
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
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
            template.convertAndSend(AmqpConfig.TOPICEXCHANGE, "spring-boot-topicRouting.bar", "======>Hello, world!");
            Thread.sleep(1000);
            RabbitTemplate template1 = new RabbitTemplate(connectionFactory);
            template1.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
                @Override
                public void confirm(CorrelationData correlationData,boolean ack,String cause){
                    System.out.println(" 回调id:" + correlationData);
                    if (ack) {  
                        System.out.println("1消息成功消费");  
                    } else {  
                        System.out.println("1消息消费失败:" + cause);  
                    }  
                }
                
            }); 
            template1.convertAndSend(AmqpConfig.TOPICEXCHANGE, "spring-boot-topicRouting.bar", "======>Hello, world!");
            Thread.sleep(500);
            RabbitTemplate template2 = new RabbitTemplate(connectionFactory);
            template2.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
                @Override
                public void confirm(CorrelationData correlationData,boolean ack,String cause){
                    System.out.println(" 回调id:" + correlationData);
                    if (ack) {  
                        System.out.println("2消息成功消费");  
                    } else {  
                        System.out.println("2消息消费失败:" + cause);  
                    }  
                }
                
            }); 
            template2.convertAndSend(AmqpConfig.TOPICEXCHANGE, "spring-boot-topicRouting.bar", "======>Hello, world!");
            Thread.sleep(1000);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        container.stop();
    }
}
