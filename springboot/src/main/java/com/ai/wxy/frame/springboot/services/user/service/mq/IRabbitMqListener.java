package com.ai.wxy.frame.springboot.services.user.service.mq;

public interface IRabbitMqListener{
    public void onMessage(String msg);
}
