package com.test.web.vo;

public interface ServiceCaller{
    public int RTN_SUC = 1;
    public int RTN_ERR = 0;
    public <M,T>ResponseResult<M> execute(RequestParam<T> param);
}
