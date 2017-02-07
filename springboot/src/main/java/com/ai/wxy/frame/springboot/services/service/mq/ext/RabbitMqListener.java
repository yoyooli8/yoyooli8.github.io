package com.ai.wxy.frame.springboot.services.service.mq.ext;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.wxy.frame.springboot.services.service.mq.IRabbitMqListener;
import com.rabbitmq.client.Channel;

@Configuration
public class RabbitMqListener implements IRabbitMqListener{
//    private AmqpConfig config;
//    private QueueingConsumer consumer;
//    @Autowired
//    public RabbitMqListener(AmqpConfig config){
//        this.config = config;
//    }

    @Override
    public void onMessage(String msg){
         System.out.println("====>RabbitMqListener receive msg : " + msg);
//        listenerThread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                try{
//                    Channel channel = config.connectionFactory().newConnection().createChannel();
//                    // 声明转发器
//                    channel.exchangeDeclare(AmqpConfig.TOPICEXCHANGE, "topic");
//                    // 随机生成一个队列
//                    String queueName = channel.queueDeclare().getQueue();
//                    // 接收所有与kernel相关的消息
//                    channel.queueBind(queueName, AmqpConfig.TOPICEXCHANGE, AmqpConfig.TOPICROUTINGKEY);
//
//                    QueueingConsumer consumer = new QueueingConsumer(channel);
//                    channel.basicConsume(queueName, false, consumer);
//
//                    while(true){
//                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//                        String message = new String(delivery.getBody());
//                        String routingKey = delivery.getEnvelope().getRoutingKey();
//
//                        System.out.println(" [x] Received routingKey = " + routingKey + ",msg = " + message + ".");
//                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 确认消息成功消费
//                    }
//
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        listenerThread.run();
    }
//    public void startLisenter(){
//        try{
//            Channel channel = config.connectionFactory().newConnection().createChannel();
//            // 声明转发器
//            channel.exchangeDeclare(AmqpConfig.TOPICEXCHANGE, "topic");
//            // 随机生成一个队列
//            String queueName = channel.queueDeclare().getQueue();
//            // 接收所有与kernel相关的消息
//            channel.queueBind(queueName, AmqpConfig.TOPICEXCHANGE, AmqpConfig.TOPICROUTINGKEY);
//
//            consumer = new QueueingConsumer(channel);
//            channel.basicConsume(queueName, false, consumer);
//
////            while(true){
////                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
////                String message = new String(delivery.getBody());
////                String routingKey = delivery.getEnvelope().getRoutingKey();
////
////                System.out.println(" [x] Received routingKey = " + routingKey + ",msg = " + message + ".");
////                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 确认消息成功消费
////            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    
    // public void messageListener(){
    // new Thread(new Runnable(){
    // @Override
    // public void run(){
    // try{
    // Channel channel =
    // config.connectionFactory().newConnection().createChannel();
    // // 声明转发器
    // channel.exchangeDeclare(AmqpConfig.TOPICEXCHANGE, "topic");
    // // 随机生成一个队列
    // String queueName = channel.queueDeclare().getQueue();
    // //接收所有与kernel相关的消息
    // channel.queueBind(queueName, AmqpConfig.TOPICEXCHANGE,
    // AmqpConfig.TOPICROUTINGKEY);
    //
    // QueueingConsumer consumer = new QueueingConsumer(channel);
    // channel.basicConsume(queueName, false, consumer);
    //
    // while (true){
    // QueueingConsumer.Delivery delivery = consumer.nextDelivery();
    // String message = new String(delivery.getBody());
    // String routingKey = delivery.getEnvelope().getRoutingKey();
    //
    // System.out.println(" [x] Received routingKey = " + routingKey
    // + ",msg = " + message + ".");
    //// channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
    // //确认消息成功消费
    // }
    //
    // }catch(Exception e){
    // e.printStackTrace();
    // }
    // }
    // }).run();
    // }
    @Bean
    public SimpleMessageListenerContainer rabbitTopicMessageContainer(ConnectionFactory connectionFactory,TopicExchange topicExchangeDurable,Queue queueTenant){
        final RabbitMqListener thisListener = this;
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new Object(){
//            @SuppressWarnings("unused")
//            public void handleMessage(String msg) {
//                thisListener.onMessage("Topic-->" + msg);
//            }
//            @SuppressWarnings("unused")
//            public void onMessage(Message message,Channel channel){
//                System.out.println("------------------------>");
//            }
//        });
//        container.setMessageListener(adapter);
        container.setQueueNames(queueTenant.getName());
//        container.start();
//        container.setQueues(queueTenant);
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
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

    // @Bean
    // public SimpleMessageListenerContainer
    // rabbitDirectMessageContainer(ConnectionFactory connectionFactory ,Queue
    // queueTenant) {
    // final RabbitMqListener thisListener = this;
    // SimpleMessageListenerContainer container = new
    // SimpleMessageListenerContainer(connectionFactory);
    // container.setQueues(queueTenant);
    // container.setExposeListenerChannel(true);
    // container.setMaxConcurrentConsumers(1);
    // container.setConcurrentConsumers(1);
    // container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
    //
    // container.setMessageListener(new ChannelAwareMessageListener(){
    // @Override
    // public void onMessage(Message message,Channel channel) throws Exception{
    // byte[] body = message.getBody();
    //
    // thisListener.onMessage("direct-->"+new String(body));
    //
    // channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    // //确认消息成功消费
    // }
    // });
    // return container;
    // }
}
