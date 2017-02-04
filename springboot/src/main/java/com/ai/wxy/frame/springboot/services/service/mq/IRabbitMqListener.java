package com.ai.wxy.frame.springboot.services.service.mq;

public interface IRabbitMqListener{
    public static final String ROUTINGKEY = "TestMsg";
    public void onMessage(String msg);
}
