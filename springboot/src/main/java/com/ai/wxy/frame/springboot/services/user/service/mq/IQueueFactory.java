package com.ai.wxy.frame.springboot.services.user.service.mq;

public interface IQueueFactory{
    public void sendMsg(String msg,String msgType);
//    public Connection newConnection() throws IOException, TimeoutException;
}
