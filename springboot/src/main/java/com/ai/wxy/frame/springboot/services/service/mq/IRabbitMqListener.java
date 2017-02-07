package com.ai.wxy.frame.springboot.services.service.mq;

public interface IRabbitMqListener{
    public void onMessage(String msg);
}
