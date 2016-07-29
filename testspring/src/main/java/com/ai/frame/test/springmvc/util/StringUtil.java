package com.ai.frame.test.springmvc.util;

public class StringUtil{
    public static boolean isNull(String str){
        return str == null;
    }
    public static boolean isEmpty(String str){
        return str ==null || str.trim().length() == 0 ? true:false;
    }
}
