package com.ai.frame.test.springmvc.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtil{
    public static Method[] getClassMethods(Class<?> cls){
        Method[] cmethods = cls.getDeclaredMethods();
        List<Method> methods = new ArrayList<Method>();
        for (Method m : cmethods) {
            if (m != null && !StringUtil.isEmpty(m.getName())) {
                methods.add(m);
            }
        }
        return methods.toArray(new Method[methods.size()]);
    }
    public static Method[] getClassMethodWithSuper(Class<?> cls){
        List<Method> methods = new ArrayList<Method>();
        if (cls != null) {
            getClassMethodWithSuper(cls,methods);
        }
        return methods.toArray(new Method[methods.size()]);
    }
    public static Method getMethodWithSuper(Class<?> cls,String method,Class<?>[]paramcls){
        boolean isFind =false;
        try{
            Method mthd = cls.getDeclaredMethod(method, paramcls);
            if(mthd !=null){
                isFind = true;
                return mthd;
            }
        }catch(Exception e){
            isFind = false;
        }
        Class<?> upsercls = cls.getSuperclass();
        if (upsercls != Object.class && isFind == false) {
            return getMethodWithSuper(upsercls,method,paramcls);
        }
        return null;
    }
    private static void getClassMethodWithSuper(Class<?> cls,List<Method> methods){
        Method[] cmethods = getClassMethods(cls);
        List<Method> fieldList = Arrays.asList(cmethods);
        methods.addAll(fieldList);
        
        Class<?> upsercls = cls.getSuperclass();
        if (upsercls != Object.class) {
            getClassMethodWithSuper(upsercls,methods);
        }
    }
}
