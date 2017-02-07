package com.ai.wxy.frame.springboot.services.service.mq.ext;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class AmqpConfig{
//    public static final String EXCHANGE   = "spring-boot-exchange";
//    public static final String QUEUENAME  = "spring-boot-queue";
//    public static final String ROUTINGKEY = "spring-boot-routingKey";
    
    public static final String TOPICEXCHANGE   = "spring-boot1-topic-Exchange";
    public static final String TOPICQUEUENAME  = "spring-boot1-topicqueue";
    public static final String TOPICROUTINGKEY = "spring-boot-topicRouting.*";
    
    @Value("${sp.rabbitmq.host}")
    private String mqhost;
    @Value("${sp.rabbitmq.port}")
    private int port;
    @Value("${sp.rabbitmq.username}")
    private String username;
    @Value("${sp.rabbitmq.password}")
    private String password;
    @Value("${sp.rabbitmq.virtualHost}")
    private String virtualHost; 
    @Bean 
    public ConnectionFactory connectionFactory() {  
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();   
        connectionFactory.setHost(mqhost);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);  
        connectionFactory.setPassword(password);  
        connectionFactory.setVirtualHost(virtualHost);  
        connectionFactory.setPublisherConfirms(true); //必须要设置
        
        return connectionFactory;  
    }
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
  
    @Bean
    TopicExchange topicExchangeDurable(RabbitAdmin rabbitAdmin) {
        TopicExchange contractTopicExchange = new TopicExchange(TOPICEXCHANGE);
        rabbitAdmin.declareExchange(contractTopicExchange);
        return contractTopicExchange;
    }
    @Bean  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    public RabbitTemplate rabbitTemplate() {  
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(TOPICEXCHANGE);
        template.setRoutingKey(TOPICROUTINGKEY);
        return template;
    }
    @Bean
    Queue queueTenant(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(TOPICQUEUENAME, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    @Bean
    Binding bindingExchangeContract(Queue queueTenant, TopicExchange topicExchangeDurable, RabbitAdmin rabbitAdmin) {
       Binding binding = BindingBuilder.bind(queueTenant).to(topicExchangeDurable).with(TOPICROUTINGKEY);
       rabbitAdmin.declareBinding(binding);
       return binding;
    }
    
//    @Bean  
//    public ConnectionFactory connectionFactory() {  
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
//        connectionFactory.setHost(mqhost);
//        connectionFactory.setPort(port);
//        connectionFactory.setUsername(username);  
//        connectionFactory.setPassword(password);  
//        connectionFactory.setVirtualHost(virtualHost);  
//        connectionFactory.setPublisherConfirms(true); //必须要设置  
//        
//        return connectionFactory;  
//    }
    
//    @Bean  
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
//    //必须是prototype类型  
//    public RabbitTemplate rabbitTemplate() {  
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());  
//        return template;  
//    }
    
//    @Bean
//    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//    
//    @Bean
//    TopicExchange contractTopicExchangeDurable(RabbitAdmin rabbitAdmin) {
//        TopicExchange contractTopicExchange = new TopicExchange(TOPICEXCHANGE);
//        rabbitAdmin.declareExchange(contractTopicExchange);
//        return contractTopicExchange;
//    }
    
//    @Bean
//    Binding bindingExchangeContract(Queue queueContract, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
//        Binding binding = BindingBuilder.bind(queueContract).to(exchange).with(TOPICROUTINGKEY);
//        rabbitAdmin.declareBinding(binding);
//        return binding;
//    }
    
//    @Bean
//    Queue queueContract(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(TOPICQUEUENAME, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
    
//    @Bean
//    DirectExchange contractDirectExchange(RabbitAdmin rabbitAdmin) {
//        DirectExchange contractDirectExchange = new DirectExchange(EXCHANGE);
//        rabbitAdmin.declareExchange(contractDirectExchange);
//        return contractDirectExchange;
//    }
//    
//    @Bean
//    Binding bindingExchangeTenant(Queue queueTenant, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
//        Binding binding = BindingBuilder.bind(queueTenant).to(exchange).with(ROUTINGKEY);
//        rabbitAdmin.declareBinding(binding);
//        return binding;
//    }
//    @Bean
//    Queue queueTenant(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(QUEUENAME, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
}
