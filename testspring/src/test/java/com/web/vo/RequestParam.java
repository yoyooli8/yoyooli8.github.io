package com.test.web.vo;

import java.util.Date;
import java.util.List;

public class RequestParam<T>{
    private Date callTime;
    private String clientHost;
    private String remoteServiceId;
    private String remoteServiceMethod;
    private String version;
    private String reqId;
    private T param;
    private List<T> params;
    public Date getCallTime(){
        return callTime;
    }
    public void setCallTime(Date callTime){
        this.callTime = callTime;
    }
    public String getClientHost(){
        return clientHost;
    }
    public void setClientHost(String clientHost){
        this.clientHost = clientHost;
    }
    public String getRemoteServiceId(){
        return remoteServiceId;
    }
    public void setRemoteServiceId(String remoteServiceId){
        this.remoteServiceId = remoteServiceId;
    }
    public String getRemoteServiceMethod(){
        return remoteServiceMethod;
    }
    public void setRemoteServiceMethod(String remoteServiceMethod){
        this.remoteServiceMethod = remoteServiceMethod;
    }
    public String getVersion(){
        return version;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public String getReqId(){
        return reqId;
    }
    public void setReqId(String reqId){
        this.reqId = reqId;
    }
    public T getParam(){
        return param;
    }
    public void setParam(T param){
        this.param = param;
    }
    public List<T> getParams(){
        return params;
    }
    public void setParams(List<T> params){
        this.params = params;
    }
}
