package com.ai.frame.test.springmvc.facade.impl;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.frame.test.springmvc.exception.FrameException;
import com.ai.frame.test.springmvc.facade.IServiceFacade;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.ClassUtil;
import com.ai.frame.test.springmvc.util.DateUtil;
import com.ai.frame.test.springmvc.util.JsonResult;
import com.ai.frame.test.springmvc.util.SpringContextUtil;

public class CommonServiceFacade<T> implements IServiceFacade<T>{
    private Logger log = LoggerFactory.getLogger(CommonServiceFacade.class);
    
    @Override
    public JsonResult excute(InputObject<T> inobj){
        if(inobj == null){
            return JsonResult.getError("入参inobj不能为空", null);
        }
        if(inobj.getService() == null){
            return JsonResult.getError("调用service的ID不能为空", inobj);
        }
        if(inobj.getMethod() == null){
            return JsonResult.getError("调用service的方法不能为空", inobj);
        }
        String startTime = DateUtil.date2Str(DateUtil.now());
        log.info("call the [{}] service's method[{}] start at:{}.", inobj.getService(),inobj.getMethod(),startTime);
        DateUtil.setStartTime();
        String rtnmsg = "";
        try{
            Object service = SpringContextUtil.getBean(inobj.getService());
            
            Class<?>[] param = new Class[]{InputObject.class};
            Method method = ClassUtil.getMethodWithSuper(service.getClass(), inobj.getMethod(), param);
            method.setAccessible(true);
            
            Object rtnVal = method.invoke(service, inobj);
            rtnmsg = "调用成功";
            return JsonResult.getSuccess(rtnmsg, rtnVal);
        }catch(Exception e){
            String errmsg = "调用失败";
            if(e instanceof FrameException){
                errmsg = e.getMessage();
            }else if(e.getCause() instanceof FrameException){
                errmsg = e.getCause().getMessage();
            }
            rtnmsg = errmsg;
            return JsonResult.getError(errmsg, e);
        }finally{
            long usedTime = DateUtil.endTime();
            
            log.info("call the [{}] service's method[{}] {} used:{} msec.", inobj.getService(),inobj.getMethod(),rtnmsg,usedTime);
        }
        
    }

}
