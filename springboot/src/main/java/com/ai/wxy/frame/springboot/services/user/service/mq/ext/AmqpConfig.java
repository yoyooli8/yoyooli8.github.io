package com.ai.wxy.frame.springboot.services.user.service.mq.ext;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration("amqpConfig2")
public class AmqpConfig{
//    public static final String EXCHANGE   = "spring-boot2-exchange";
//    public static final String QUEUENAME  = "spring-boot2-queue";
//    public static final String ROUTINGKEY = "spring-boot2-routingKey";
    
    public static final String TOPICEXCHANGE   = "spring-boot1-topic-Exchange";
    public static final String TOPICQUEUENAME  = "spring-boot1-topicqueue";
    public static final String TOPICROUTINGKEY = "spring-boot-topicRouting.*";
    
//    @Value("${sp.rabbitmq.host}")
//    private String mqhost;
//    @Value("${sp.rabbitmq.port}")
//    private int port;
//    @Value("${sp.rabbitmq.username}")
//    private String username;
//    @Value("${sp.rabbitmq.password}")
//    private String password;
//    @Value("${sp.rabbitmq.virtualHost}")
//    private String virtualHost; 
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
//    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
  
    /**
      * ===============================================
      * ampq-->topic-->route
      * @brief   ampq-->topic-->route
      * @details ampq-->topic-->route
      * @param rabbitAdmin
      * @author wangxiangyang
      * @date 2017年2月8日 下午4:53:40
      * @note wangxiangyang@ 2017年2月8日添加了此方法
      * ===============================================
     */
    @Bean
    TopicExchange topicExchangeDurable(RabbitAdmin rabbitAdmin) {
        TopicExchange contractTopicExchange = new TopicExchange(TOPICEXCHANGE);
        rabbitAdmin.declareExchange(contractTopicExchange);
        return contractTopicExchange;
    }
    @Bean(name="rabbitTemplate")  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {  
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
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
    Binding bindingExchangeContract(RabbitAdmin rabbitAdmin) {
       Binding binding = BindingBuilder.bind(queueTenant(rabbitAdmin)).to(topicExchangeDurable(rabbitAdmin)).with(TOPICROUTINGKEY);
       rabbitAdmin.declareBinding(binding);
       return binding;
    }

    /**
     * ===============================================
     * ampq-->topic-->route
     * @brief   ampq-->topic-->route
     * @details ampq-->topic-->route
     * @param rabbitAdmin
     * @author wangxiangyang
     * @date 2017年2月8日 下午4:53:40
     * @note wangxiangyang@ 2017年2月8日添加了此方法
     * ===============================================
    */
//    @Bean
//    DirectExchange directExchangeDurable(RabbitAdmin rabbitAdmin){
//        DirectExchange exchange = new DirectExchange(EXCHANGE);
//        rabbitAdmin.declareExchange(exchange);
//        return exchange;
//    }
//    
//    @Bean  
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
//    public RabbitTemplate directRabbitTemplate() {  
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        template.setExchange(EXCHANGE);
//        template.setRoutingKey(ROUTINGKEY);
//        return template;
//    }
//    
//    @Bean
//    Queue queueDirect(RabbitAdmin rabbitAdmin) {
//        Queue queue = new Queue(QUEUENAME, true);
//        rabbitAdmin.declareQueue(queue);
//        return queue;
//    }
//    
//    @Bean
//    Binding bindingDirectExchange(Queue queueDirect, TopicExchange directExchangeDurable, RabbitAdmin rabbitAdmin) {
//       Binding binding = BindingBuilder.bind(queueDirect).to(directExchangeDurable).with(ROUTINGKEY);
//       rabbitAdmin.declareBinding(binding);
//       return binding;
//    }
}
