package com.test.web.vo;

import org.springframework.stereotype.Component;

@Component
public class WebServiceCaller implements ServiceCaller{
    private ServiceCaller coreServiceCaller;
    @Override
    public <M, T> ResponseResult<M> execute(RequestParam<T> param){
        ResponseResult<M> result = new ResponseResult<M>();
        try{
            result = coreServiceCaller.execute(param);
            result.setRtnCode(RTN_SUC);
        }catch(Exception e){
            result.setRtnCode(RTN_ERR);
        }
        
        return result;
    }

}
