package com.ai.frame.test.springmvc.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

import com.ai.frame.test.springmvc.util.SecurityUtil;
@Component(value="credentialsMatcher")
public class PwdCredentialsMatcher extends SimpleCredentialsMatcher{
    /**
     * @token 用户登录token
     * @info  根据用户查询的数据库用户信息info
     * @des   可以扩展实现密码错误次数限制
     */
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        String userPwd  = String.valueOf(authcToken.getPassword());
        
        SimpleAuthenticationInfo authenticationInfo = (SimpleAuthenticationInfo)info;
        String salt = new String(authenticationInfo.getCredentialsSalt().getBytes());
        
        String tokenCredentials = SecurityUtil.pwd2dbpwd(userPwd,salt);
        Object accountCredentials = getCredentials(info);
        
        return equals(tokenCredentials, accountCredentials);
    }
}
