package com.ai.frame.test.springmvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ai.frame.test.springmvc.bo.MenuPerm;
import com.ai.frame.test.springmvc.bo.Menus;
import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.MenusMapper;
import com.ai.frame.test.springmvc.service.IMenuPermService;
import com.ai.frame.test.springmvc.service.IPermissionsService;
import com.ai.frame.test.springmvc.service.ImenuService;
import com.ai.frame.test.springmvc.util.CollectionUtil;

import tk.mybatis.mapper.entity.Example;

public class MenuServiceImpl extends BaseService<Menus> implements ImenuService{
    @Autowired
    @Qualifier(value="permissionsService")
    private IPermissionsService permissionsService;
    private IMenuPermService menuPermService;
    
    public List<Menus> getAllMenus(InputObject<Menus> inobj){
        Example example = new Example(Menus.class);
        example.createCriteria().andEqualTo("domain", inobj.getParam().getDomain());
        example.createCriteria().andEqualTo("status", inobj.getParam().getStatus());
        
        List<Menus> menus = super.selectByExample(example);
        
        if(menus!=null && menus.size() >0){
            Map<Integer,List<Permissions>> menuPermList = getMenusPermissions(menus);
            concactMenuPerms(menus,menuPermList);
        }
        
        return menus;
    }
    
    private Map<Integer,List<Permissions>> getMenusPermissions(List<Menus> menus){
        //获取菜单对应的角色信息
        Set<Menus> menuIds = CollectionUtil.list2Set(menus, "menuId", Menus.class);
        InputObject<Menus> menuInobj = new InputObject<Menus>();
        menuInobj.setParams(new ArrayList<Menus>(menuIds));
        List<Permissions> menuPermissions = permissionsService.getPermissionsByMenuIds(menuInobj);
        //获取菜单权限对应关系
        Set<MenuPerm> menuPermMenuIds = CollectionUtil.list2Set(menus, "menuId", MenuPerm.class);
        InputObject<MenuPerm> menuPermInobj = new InputObject<MenuPerm>();
        menuPermInobj.setParams(new ArrayList<MenuPerm>(menuPermMenuIds));
        List<MenuPerm> menuPerms = menuPermService.getMenuPermByMenuIds(menuPermInobj);
        
        Map<Integer,List<Permissions>> menuPermList = convertMenuPerms(menuPermissions,menuPerms);
        return menuPermList;
    }
    private void concactMenuPerms(List<Menus> menus,Map<Integer,List<Permissions>> menuPermList){
        if(menus!=null){
            for(Menus menu:menus){
                Integer menuId = menu.getMenuId();
                List<Permissions> perms = menuPermList.get(menuId);
                menu.setPerms(perms);
            }
        }
    }
    private Map<Integer,List<Permissions>> convertMenuPerms(List<Permissions> permissions,List<MenuPerm> menuPerms){
        Map<Integer,List<Permissions>> menuPermMap = new HashMap<Integer,List<Permissions>>();
        Map<Integer,Permissions> permsMap = CollectionUtil.list2Map(permissions, "permId", Integer.class);
        if(menuPerms!=null){
            for(MenuPerm menuPerm:menuPerms){
                Integer menuId = menuPerm.getMenuId();
                List<Permissions> menuPermList = menuPermMap.get(menuId);
                if(menuPermList == null){
                    menuPermList = new ArrayList<Permissions>();
                    menuPermMap.put(menuId, menuPermList);
                }
                
                Permissions perm = permsMap.get(menuPerm.getPermId());
                if(perm!=null){
                    menuPermList.add(perm);
                }
            }
        }
        
        return menuPermMap;
    }
    public Menus getMenuById(InputObject<Menus> inobj){
        Menus menu = super.selectByKey(inobj.getParam().getMenuId());
        
        if(menu!=null){
            //获取菜单对应的角色信息
            List<Menus> menuIds = new ArrayList<Menus>();
            menuIds.add(menu);
            InputObject<Menus> menuInobj = new InputObject<Menus>();
            menuInobj.setParams(menuIds);
            List<Permissions> perms = permissionsService.getPermissionsByMenuIds(menuInobj);
            menu.setPerms(perms);
        }
        
        return menu;
    }
    public List<Menus> getChildMenusByPid(InputObject<Menus> inobj){
        Example example = new Example(Menus.class);
        example.createCriteria().andEqualTo("pmenuIds", inobj.getParam().getpMenuId());
        example.createCriteria().andEqualTo("status",   inobj.getParam().getStatus());
        
        List<Menus> menus = super.selectByExample(example);
        
        if(menus!=null && menus.size() >0){
            Map<Integer,List<Permissions>> menuPermList = getMenusPermissions(menus);
            concactMenuPerms(menus,menuPermList);
        }
        
        return menus;
    }
    @Autowired
    public void setMenusMapper(MenusMapper mapper){
        this.mapper = mapper;
    }
}
