package com.ai.frame.test.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.UserRoleMapper;
import com.ai.frame.test.springmvc.service.IUserRoleService;

import tk.mybatis.mapper.entity.Example;
@Service(value="userRoleService")
public class UserRoleServiceImpl extends BaseService<UserRole> implements IUserRoleService{
    public int save(InputObject<UserRole> inobj){
        return super.save(inobj.getParam());
    }
    
    public int batchSaves(InputObject<UserRole> inobj){
        UserRoleMapper mapper = super.getMapper();
        return mapper.batchSaves(inobj.getParams());
    }
    
    public int deleteByExample(InputObject<UserRole> inobj){
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("userId", inobj.getParam().getUserId());
        
        return super.deleteByExample(example);
    }
}
