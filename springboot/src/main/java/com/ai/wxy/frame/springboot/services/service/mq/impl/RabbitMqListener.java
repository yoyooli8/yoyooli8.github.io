/*package com.ai.wxy.frame.springboot.services.service.mq.impl;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.wxy.frame.springboot.services.service.mq.IRabbitMqListener;
import com.rabbitmq.client.Channel;

@Configuration
public class RabbitMqListener implements IRabbitMqListener{
    @Bean  
    public Binding binding(DirectExchange defaultExchange,Queue queue) {  
        return BindingBuilder.bind(queue).to(defaultExchange).with(ROUTINGKEY);  
    } 
    @Bean  
    public SimpleMessageListenerContainer rabbitMessageContainer(ConnectionFactory connectionFactory ,Queue queue) {
        final RabbitMqListener thisListener = this;
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);  
        container.setConcurrentConsumers(1);  
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        
        container.setMessageListener(new ChannelAwareMessageListener(){
            @Override
            public void onMessage(Message message,Channel channel) throws Exception{
                byte[] body = message.getBody();
                
                thisListener.onMessage(new String(body)); 
                
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }
    public void onMessage(String msg){
        System.out.println("RabbitMqListener receive msg : " + msg); 
    }
}
*/