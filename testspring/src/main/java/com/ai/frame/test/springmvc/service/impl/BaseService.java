package com.ai.frame.test.springmvc.service.impl;

import java.util.List;

import com.ai.frame.test.springmvc.mybatis.util.PageRecord;
import com.ai.frame.test.springmvc.service.IService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<T> implements IService<T>{
    protected Mapper<T> mapper;
    @SuppressWarnings("unchecked")
    protected <M extends Mapper<T>> M getMapper(){
        return (M)mapper;
    }
    public T selectByKey(Object key){
        return mapper.selectByPrimaryKey(key);
    }
    
    public int save(T entity){
        return mapper.insert(entity);
    }
    
    public int delete(Object key){
        return mapper.deleteByPrimaryKey(key);
    }
    public int deleteByExample(Object example){
        return mapper.deleteByExample(example);
    }
    public int updateAll(T entity){
        return mapper.updateByPrimaryKey(entity);
    }
    
    public int updateNotNull(T entity){
        return mapper.updateByPrimaryKeySelective(entity);
    }
    public int updateByExample(T entity,Object example){
        return mapper.updateByExample(entity, example);
    }
    public List<T> selectByExample(Object example){
        return mapper.selectByExample(example);
    }
    public int selectCountByExample(Object example){
        return mapper.selectCountByExample(example);
    }
    public PageRecord<T> selectByExampleForpage(Object example,int startNum,int limit){
        int totalCount = selectCountByExample(example);
        
        //分页查询
        int pageNum = (startNum + limit)/limit;
        PageHelper.startPage(pageNum, limit);
        List<T> pageData = selectByExample(example);
        
        PageRecord<T> pageRecord = new PageRecord<T>(pageData,totalCount,limit,pageNum);
        
        return pageRecord;
    }
}
