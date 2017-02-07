package com.ai.wxy.frame.springboot.services.service.mq;

public interface IQueueFactory{
    public void sendMsg(String msg,String exchange,String msgType);
//    public Connection newConnection() throws IOException, TimeoutException;
}
