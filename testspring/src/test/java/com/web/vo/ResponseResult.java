package com.test.web.vo;

import java.util.List;

public class ResponseResult<T>{
    public static final int RTN_SUC = 1;
    public static final int RTN_ERR = 0;
    private int rtnCode;
    private String rtnMsg;
    private T data;
    private List<T> datas;
    
    public ResponseResult(){}
    private ResponseResult(int rtnCode,String rtnMsg,T data,List<T> datas){
        this.rtnCode = rtnCode;
        this.rtnMsg  = rtnMsg;
        this.data    = data;
        this.datas   = datas;
    }
    public static <T>ResponseResult<T> getSuccess(String rtnMsg){
        ResponseResult<T> result = new ResponseResult<T>(RTN_SUC,rtnMsg,null,null);
        return result;
    }
    public static <T>ResponseResult<T> getSuccess(String rtnMsg,T data){
        ResponseResult<T> result = new ResponseResult<T>(RTN_SUC,rtnMsg,data,null);
        return result;
    }
    public static <T>ResponseResult<T> getSuccess(String rtnMsg,List<T> datas){
        ResponseResult<T> result = new ResponseResult<T>(RTN_SUC,rtnMsg,null,datas);
        return result;
    }
    public static <T>ResponseResult<T> getSuccess(String rtnMsg,T data,List<T> datas){
        ResponseResult<T> result = new ResponseResult<T>(RTN_SUC,rtnMsg,data,datas);
        return result;
    }
    public static <T>ResponseResult<T> getError(String rtnMsg){
        ResponseResult<T> result = new ResponseResult<T>(RTN_ERR,rtnMsg,null,null);
        return result;
    }
    public static <T>ResponseResult<T> getError(String rtnMsg,T data){
        ResponseResult<T> result = new ResponseResult<T>(RTN_ERR,rtnMsg,data,null);
        return result;
    }
    public static <T>ResponseResult<T> getError(String rtnMsg,T data,List<T> datas){
        ResponseResult<T> result = new ResponseResult<T>(RTN_ERR,rtnMsg,data,datas);
        return result;
    }
    public int getRtnCode(){
        return rtnCode;
    }
    public void setRtnCode(int rtnCode){
        this.rtnCode = rtnCode;
    }
    public String getRtnMsg(){
        return rtnMsg;
    }
    public void setRtnMsg(String rtnMsg){
        this.rtnMsg = rtnMsg;
    }
    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data = data;
    }
    public List<T> getDatas(){
        return datas;
    }
    public void setDatas(List<T> datas){
        this.datas = datas;
    }
    
}
