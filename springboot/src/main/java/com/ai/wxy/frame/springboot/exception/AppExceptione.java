package com.ai.wxy.frame.springboot.exception;

@SuppressWarnings("serial")
public class AppExceptione extends RuntimeException{
    private Integer errorCode;
    public AppExceptione(Integer errorCode,String msg){
        super(msg);
        this.errorCode = errorCode;
    }
    
    public AppExceptione(Integer errorCode,String message, Throwable cause){
        super(message,cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode(){
        return errorCode;
    }
    
}
