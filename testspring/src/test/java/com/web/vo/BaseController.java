package com.test.web.vo;

import java.util.Map;

public class BaseController{
    private ServiceCaller serviceCaller;
    public ResponseResult<Map<String,String>> commExecute(RequestParam<Map<String,String>> requestParam){
        return serviceCaller.execute(requestParam);
    }
}
