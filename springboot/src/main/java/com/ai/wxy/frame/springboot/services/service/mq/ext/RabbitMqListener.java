package com.ai.wxy.frame.springboot.services.service.mq.ext;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.wxy.frame.springboot.services.service.mq.IRabbitMqListener;
import com.rabbitmq.client.Channel;

@Configuration("topicListener")
public class RabbitMqListener implements IRabbitMqListener{

    @Override
    public void onMessage(String msg){
         System.out.println("====>RabbitMqListener receive msg : " + msg);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitTopicMessageContainer(ConnectionFactory connectionFactory){
        final RabbitMqListener thisListener = this;
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        container.setQueueNames(AmqpConfig.TOPICQUEUENAME);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认

        container.setMessageListener(new ChannelAwareMessageListener(){
            @Override
            public void onMessage(Message message,Channel channel) throws Exception{
                byte[] body = message.getBody();

                thisListener.onMessage("Topic-->" + new String(body));
                
                // 确认消息成功消费
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                
            }
        });
        return container;
    }

}
