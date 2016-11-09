package com.ai.frame.test.springmvc.mybatis.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义Mapper基类 
 * @author 王向阳
 *
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>{
}
