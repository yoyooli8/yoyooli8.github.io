package com.ai.frame.test.springmvc.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil{
    @SuppressWarnings("unchecked")
    public static <M,T>Map<M,T> list2Map(List<T> source,String fieldName,Class<M> keycls){
        Map<M,T> rtnMap = new HashMap<M,T>();
        if(source!=null){
            for(T t:source){
                Object val = ClassUtil.invokeField(t, t.getClass(), fieldName);
                if(val!=null){
                    if(val.getClass().isAssignableFrom(keycls)){
                        rtnMap.put((M)val, t);
                    }
                }
            }
        }
        return rtnMap;
    }
    @SuppressWarnings("unchecked")
    public static <T,M> Set<T> list2Set(List<M> source,String fieldName,Class<T> rtncls){
        Set<T> rtnset = new HashSet<T>();
        if(source!=null){
            for(M m:source){
                Object val = ClassUtil.invokeField(m, m.getClass(), fieldName);
                if(val!=null){
                    if(val.getClass().isAssignableFrom(rtncls)){
                        rtnset.add(((T)val));
                    }else{
                        T t = ClassUtil.newInstance(rtncls);
                        if(t!=null){
                            ClassUtil.setFieldVal(t, fieldName, val);
                            rtnset.add(t);
                        }
                    }
                }
            }
        }
        return rtnset;
    }
}
