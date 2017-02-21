package com.ai.wxy.frame.springboot.exception;

public enum ErroCodes{
    AESENCODING_ERROR(3,"AES解密"),
    AESDECRYPT_ERROR(4, "AES加密"),
    HEXDECODE_ERROR(2,  "HEX加密"),
    ENCODING_ERROR(1,   "base64加密");
    private Integer errorCode;
    private String errorLable;
    private ErroCodes(Integer errorCode,String errorLable){
        this.errorCode  = errorCode;
        this.errorLable = errorLable;
    }
    public Integer getErrorCode(){
        return errorCode;
    }
    public String getErrorLable(){
        return errorLable;
    }
    
}
