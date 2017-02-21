package com.ai.wxy.frame.springboot.services.user.service.mq.ext;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class MqApplicationEvent extends ApplicationEvent{
    private Integer msgType;
    public MqApplicationEvent(Integer msgType,Object source){
        super(source);
        this.msgType = msgType;
    }
    public Integer getMsgType(){
        return msgType;
    }

}
