package com.ai.frame.test.springmvc.util;

public enum UserStatus{
    VALIDATE(1),
    INVALIDATE(2),
    LOCKED(3);
    private int status;
    private UserStatus(int status){
        this.status = status;
    }
    public int getVal(){
        return status;
    }
}
