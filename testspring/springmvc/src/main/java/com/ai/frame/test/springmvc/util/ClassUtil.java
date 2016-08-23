package com.ai.frame.test.springmvc.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil{
    private static Logger log = LoggerFactory.getLogger(ClassUtil.class);
    
    public static <T>T newInstance(Class<T> cls){
        try{
            return cls.newInstance();
        }catch(Exception e){
            log.error("newInstance error", e);
        }
        return null;
    }
    public static String getFieldGetter(String fieldName){
        return "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }
    public static String getFieldSetter(String fieldName){
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }
    public static void setFieldVal(Object target,String fieldName,Object fieldVal){
        String setter = getFieldSetter(fieldName);
        Class<?> settercls = fieldVal.getClass();
        Class<?>[]paramcls = new Class<?>[]{settercls};
        Method method = getMethodWithSuper(target.getClass(),setter,paramcls);
        
        if(method!=null){
            try{
                method.setAccessible(true);
                Object[] args = new Object[]{fieldVal};
                
                method.invoke(target, args);
            }catch(Exception e){
                log.error("set setFieldVal error", e);
            }
        }
        log.error("cant't find the method[{}]", setter);
    }
    public static Object invokeField(Object target,Class<?> cls,String fieldName){
        String getter = getFieldGetter(fieldName);
        Class<?>[]paramcls = new Class<?>[]{};
        
        Method method = getMethodWithSuper(cls,getter,paramcls);
        if(method!=null){
            try{
                method.setAccessible(true);
                Object[] args = new Object[]{};
                
                return method.invoke(target, args);
            }catch(Exception e){
                log.error("invokeField error", e);
                return null;
            }
        }
        log.error("cant't find the method[{}]", getter);
        return null;
    }
    
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
