package com.ai.frame.test.springmvc.security;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IUserService;
import com.ai.frame.test.springmvc.util.CollectionUtil;
import com.ai.frame.test.springmvc.util.StringUtil;
import com.ai.frame.test.springmvc.util.UserStatus;
@Component(value="userRealm")
public class UserRealm extends AuthorizingRealm{
    @Autowired
    @Qualifier(value="userService")
    private IUserService userService;
    @Autowired
    @Qualifier(value="credentialsMatcher")
    private PwdCredentialsMatcher pwdCredentialsMatcher;
    
    @PostConstruct
    protected void postConstruct(){
        setCredentialsMatcher(pwdCredentialsMatcher);
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        String username = StringUtil.obj2str(principals.getPrimaryPrincipal());
        User user = new User();
        user.setUserName(username);
        InputObject<User> inobj = new InputObject<User>();
        inobj.setParam(user);
        
        User dbuser = userService.getUserByNameWithRoles(inobj);
        List<Permissions> perms = userService.getUserPermissionsByUserName(inobj);
        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(CollectionUtil.list2Set(dbuser.getRoles(),"roleName",String.class));
        authorizationInfo.setStringPermissions(CollectionUtil.list2Set(perms,"permName",String.class));
        
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        String username = StringUtil.obj2str(token.getPrincipal());
        User user = new User();
        user.setUserName(username);
        InputObject<User> inobj = new InputObject<User>();
        inobj.setParam(user);
        
        User dbuser = userService.getUserByName(inobj);
        
        if(dbuser == null){
            throw new UnknownAccountException();//没找到帐号
        }
        
        if(UserStatus.LOCKED.getVal() == dbuser.getStatus()){
            throw new LockedAccountException(); //帐号锁定
        }
        if(UserStatus.INVALIDATE.getVal() == dbuser.getStatus()){
            throw new DisabledAccountException(); //帐号无效
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = 
                new SimpleAuthenticationInfo(dbuser.getUserName(),
                        dbuser.getUserpwd(),
                        ByteSource.Util.bytes(dbuser.getSalt()),
                        getName());
        
        return authenticationInfo;
    }

}
