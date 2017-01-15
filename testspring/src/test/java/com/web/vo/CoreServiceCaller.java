package com.test.web.vo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class CoreServiceCaller implements ServiceCaller{
    private static final String DEFMETHOD = "execute";
    @Autowired
    private SpringApplicationContextHelp context;
    @SuppressWarnings({"rawtypes", "unchecked" })
    public <M, T> ResponseResult<M> execute(RequestParam<T> param){
        if(param == null){
            return ResponseResult.getError("调用参数不能为空.");
        }
        String serviceId = param.getRemoteServiceId();
        String methodName = param.getRemoteServiceMethod();
        if(serviceId == null || serviceId.length() == 0){
            return ResponseResult.getError("调用的服务ID不能为空.");
        }
        if(methodName == null || methodName.length() == 0){
            methodName = DEFMETHOD;
        }
        
        try{
            Object serviceBean = context.getBean(serviceId);
            if(serviceBean == null){
                return ResponseResult.getError("服务"+serviceId+"不存在.");
            }
            Object rtnObj = ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(serviceBean.getClass(), methodName), serviceBean,param);
            if(rtnObj instanceof ResponseResult){
                return ((ResponseResult)rtnObj);
            }
            return ResponseResult.getError("调用服务["+serviceId+"."+methodName+"]失败.");
        }catch(BeansException e){
            return ResponseResult.getError("服务"+serviceId+"不存在.");
        }catch(Exception e){
            return ResponseResult.getError("调用服务["+serviceId+"."+methodName+"]失败.");
        }
    }

}
