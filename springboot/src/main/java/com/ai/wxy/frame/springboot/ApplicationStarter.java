package com.ai.wxy.frame.springboot;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ai.wxy.frame.springboot.services.user.dao.UserCrudRepository;
import com.ai.wxy.frame.springboot.services.user.dao.support.CustomRepositoryFactoryBean;
import com.ai.wxy.frame.springboot.services.user.service.mq.IQueueFactory;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SuppressWarnings("rawtypes")
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
@EnableScheduling
public class ApplicationStarter implements ApplicationListener{
    @Autowired
    UserCrudRepository userCrudRepository;
    @Resource(name="springbootMqSend")
    private IQueueFactory queueSend;
    @Resource(name="springbootMqDirectSend")
    private IQueueFactory directQueueSend;
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
    public static void main(String[] args){
        SpringApplication.run(ApplicationStarter.class, args);
    }
    
    
    @Scheduled(cron = "${job.everysecond.cron}")
    public void everySecond(){
        System.out.println("每秒任务，当前时间：" + nowTime());
    }

    private String nowTime(){
        return DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
    
    //在这里可以监听到Spring Boot的生命周期
    @Override
    public void onApplicationEvent(ApplicationEvent event){
        if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量
            queueSend.sendMsg("======初始化环境变量开始=======", "spring-boot-topicRouting.PreparedEvent");
        }else if (event instanceof ApplicationPreparedEvent) { // 初始化完成
            queueSend.sendMsg("======应用初始化完成=======", "spring-boot-topicRouting.Event");
        } else if (event instanceof ContextRefreshedEvent) { // 应用刷新
            queueSend.sendMsg("======应用刷新开始=======", "spring-boot-topicRouting.RefreshedEvent");
        }else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            queueSend.sendMsg("======应用已启动完成=======", "spring-boot-topicRouting.ReadyEvent");
            
            directQueueSend.sendMsg("======应用已启动完成=======", com.ai.wxy.frame.springboot.services.user.service.mq.AmqpConfig.ROUTINGKEY);
        }else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获
            queueSend.sendMsg("======应用启动开始=======", "spring-boot-topicRouting.StartedEvent");
        }else if (event instanceof ContextStoppedEvent) { // 应用停止
            queueSend.sendMsg("======应用停止=======", "spring-boot-topicRouting.StoppedEvent");
        }else if (event instanceof ContextClosedEvent) { // 应用关闭
            queueSend.sendMsg("======应用关闭=======", "spring-boot-topicRouting.ClosedEvent");
        }else{}
    }
}