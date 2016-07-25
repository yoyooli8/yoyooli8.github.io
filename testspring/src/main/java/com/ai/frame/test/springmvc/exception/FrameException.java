package com.ai.frame.test.springmvc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class FrameException extends RuntimeException{
    private Logger log = LoggerFactory.getLogger(FrameException.class);
    private String errName;
    public FrameException() {
        super();
    }
    public FrameException(String message,String errName) {
        super(message);
        this.errName = errName;
    }
    public FrameException(String message, Throwable cause,String errName) {
        super(message, cause);
        this.errName = errName;
    }
    public FrameException(Throwable cause,String errName) {
        super(cause);
        this.errName = errName;
    }
    
    public String getMessage() {
        String msg = super.getMessage();
        
        log.error("{}'s error is:{}", errName,msg);
        return msg;
    }
}
