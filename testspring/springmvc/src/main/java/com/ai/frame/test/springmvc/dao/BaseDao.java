package com.ai.frame.test.springmvc.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.ai.frame.test.springmvc.mybatis.util.PageRecord;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.common.Mapper;

public class BaseDao<T>{
    protected Mapper<T> mapper;
    @SuppressWarnings("unchecked")
    protected <M extends Mapper<T>> M getMapper(){
        return (M)mapper;
    }
    
    @Cacheable(value="defaultCache", key="#key")
    public T selectByKey(Object key){
        return mapper.selectByPrimaryKey(key);
    }
    @CacheEvict(value="accountCache",key="#entity.getId()")
    public int save(T entity){
        return mapper.insert(entity);
    }
    @CacheEvict(value="accountCache",key="#key")
    public int delete(Object key){
        return mapper.deleteByPrimaryKey(key);
    }
    public int deleteByExample(Object example){
        return mapper.deleteByExample(example);
    }
    @CacheEvict(value="accountCache",key="#entity.getId()")
    public int updateAll(T entity){
        return mapper.updateByPrimaryKey(entity);
    }
    @CacheEvict(value="accountCache",key="#entity.getId()")
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
