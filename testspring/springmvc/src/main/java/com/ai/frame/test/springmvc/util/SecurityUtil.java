package com.ai.frame.test.springmvc.util;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class SecurityUtil{
    private static SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();
    private final static String ENCODING = "utf-8";
    
    public static String getRandomStr(String seed){
        if(!StringUtil.isEmpty(seed)){
            random.setSeed(CodecSupport.toBytes(seed, ENCODING));
        }
        return random.nextBytes().toHex(); 
    }
    
    public static String pwdMd5Hash(String pwd,String salt){
        String md5 = new Md5Hash(pwd, salt).toHex();
        return md5;
    }
    
    public static String pwd2dbpwd(String pwd,String salt){
        return pwdMd5Hash(pwd, pwd + ":" + salt);
    }
}
