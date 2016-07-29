package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.mybatis.util.PageRecord;

/**
 * service 基础接口
 * @author 王向阳
 * @param <T>
 * @date  20160724
 */
public interface IService<T> {
    /**根据主键查询**/
    T selectByKey(Object key);
    /**添加并返回主键信息*/
    int save(T entity);
    /**根据主键删除*/
    int delete(Object key);
    /**全部属性都会更新*/
    int updateAll(T entity);
    /**更新不为空的字段**/
    int updateNotNull(T entity);
    /**条件查询**/
    List<T> selectByExample(Object example);
    /**查询结果总数*/
    int selectCountByExample(Object example);
    /**分页条件查询**/
    PageRecord<T> selectByExampleForpage(Object example,int startNum,int limit);
}
