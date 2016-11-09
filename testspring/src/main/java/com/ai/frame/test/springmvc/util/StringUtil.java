package com.ai.frame.test.springmvc.util;

import java.util.Date;

public class StringUtil{
    public static final String EMPTY = "";
    public static boolean isNull(String str){
        return str == null;
    }
    public static boolean isEmpty(String str){
        return str ==null || str.trim().length() == 0 ? true:false;
    }
    
    public static String obj2str(Object obj){
        if(obj!=null){
            if(obj instanceof String){
                return obj.toString().trim();
            }else if(obj instanceof Integer || obj instanceof Double ||
                      obj instanceof Float || obj instanceof Long){
                return String.valueOf(obj);
            }else if(obj instanceof Date){
                String tmp = DateUtil.date2Str((Date)obj, DateUtil.DATETIME);
                if(isNull(tmp)){
                    tmp = DateUtil.date2Str((Date)obj, DateUtil.DATE);
                }
                if(isNull(tmp)){
                    tmp = DateUtil.date2Str((Date)obj, DateUtil.TIME);
                }
                return tmp;
            }
        }
        return EMPTY;
    }
}
