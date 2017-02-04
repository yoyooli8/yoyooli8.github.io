package com.ai.wxy.frame.springboot.services.service.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;

public interface IQueueFactory{
    public void sendMsg(String msg,String msgType);
    public Connection newConnection() throws IOException, TimeoutException;
}
