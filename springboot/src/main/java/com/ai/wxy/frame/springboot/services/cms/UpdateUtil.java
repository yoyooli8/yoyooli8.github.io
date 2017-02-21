package com.ai.wxy.frame.springboot.services.cms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.data.mongodb.core.query.Update;

import com.ai.wxy.frame.springboot.services.ContentBaseProp;

public class UpdateUtil{
    private static final List<String> BASEPROPS = new ArrayList<String>();
    static{
        BASEPROPS.add(ContentBaseProp.PROP_TPLID.getName());
        BASEPROPS.add(ContentBaseProp.PROP_NAME.getName());
        BASEPROPS.add(ContentBaseProp.PROP_DESC.getName());
        BASEPROPS.add(ContentBaseProp.PROP_CREATERUID.getName());
        BASEPROPS.add(ContentBaseProp.PROP_CREATERUNAME.getName());
        BASEPROPS.add(ContentBaseProp.PROP_CREATETIME.getName());
        BASEPROPS.add(ContentBaseProp.PROP_UPDATEUID.getName());
        BASEPROPS.add(ContentBaseProp.PROP_UPDATEUNAME.getName());
        BASEPROPS.add(ContentBaseProp.PROP_UPDATETIME.getName());
        BASEPROPS.add(ContentBaseProp.PROP_VALIDE.getName());
        BASEPROPS.add(ContentBaseProp.PROP_CONTENTID.getName());
    }
    
    public static boolean isBaseProp(String propName){
        return BASEPROPS.contains(propName);
    }
    public static String getContentId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static Update map2Update(Map<String,Object> data,String prex){
        Update update = new Update();
        
        Iterator<Entry<String, Object>>  entryIt = data.entrySet().iterator();
        while(entryIt.hasNext()){
            Entry<String, Object> entry = entryIt.next();
            update.set(prex + entry.getKey(), entry.getValue());
        }
        
        return update;
    }
    public static <T>Update entity2Update(T t,String prex){
        Update update = new Update();
        
        Class<?> cls = t.getClass();
        Field[] fields = getFields(cls);
        while(fields!=null){
            for(Field field:fields){
                field.setAccessible(true);
                
                try{
                    String name = field.getName();
                    Object val  = field.get(t);
                    
                    update.set(prex + name, val);
                }catch(Exception e){
                }
            }
            
            cls = cls.getSuperclass();
            fields = getFields(cls);
        }
        
        return update;
    }
    
    private static Field[] getFields(Class<?> cls){
        if(cls.equals(Object.class)){
            return null;
        }else{
            return cls.getDeclaredFields();
        }
    }
}
