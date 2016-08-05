package com.ai.frame.test.springmvc.facade;

import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

public interface IServiceFacade<T>{
    /**调用service统入口方法**/
    public JsonResult excute(InputObject<T> inobj);
}
