package com.ai.frame.test.springmvc.facade.dto;

import java.util.Date;
import java.util.List;

/**
 * 统一调用service的入参封装
 * @author 王向阳
 * @param <T>
 */
public class InputObject<T>{
    /**service 在spring中的ID*/
    private String service;
    /**调用具体service中的方法*/
    private String method;
    /**页面入参*/
    private T param;
    /**页面入参*/
    private List<T> params;
    /**调用者的服务器IP*/
    private String callerIp;
    /**调用时间*/
    private Date callerTime;
    /**系统登录用户ID*/
    private Integer loginUserId;
    /**系统登录用户名称*/
    private String LoginUserName;
    /**是否要记录调用日志*/
    private Boolean isRecoredLog;
    public String getService(){
        return service;
    }
    public void setService(String service){
        this.service = service;
    }
    public String getMethod(){
        return method;
    }
    public void setMethod(String method){
        this.method = method;
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
    public String getCallerIp(){
        return callerIp;
    }
    public void setCallerIp(String callerIp){
        this.callerIp = callerIp;
    }
    public Date getCallerTime(){
        return callerTime;
    }
    public void setCallerTime(Date callerTime){
        this.callerTime = callerTime;
    }
    public Integer getLoginUserId(){
        return loginUserId;
    }
    public void setLoginUserId(Integer loginUserId){
        this.loginUserId = loginUserId;
    }
    public String getLoginUserName(){
        return LoginUserName;
    }
    public void setLoginUserName(String loginUserName){
        LoginUserName = loginUserName;
    }
    public Boolean getIsRecoredLog(){
        return isRecoredLog;
    }
    public void setIsRecoredLog(Boolean isRecoredLog){
        this.isRecoredLog = isRecoredLog;
    }
}
