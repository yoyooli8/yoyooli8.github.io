package com.ai.frame.test.springmvc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ai.frame.test.springmvc.exception.FrameException;

public class DateUtil{
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE     = "yyyy-MM-dd";
    public static final String TIME     = "HH:mm:ss";
    private static ThreadLocal<Date> threadLocalDate = new ThreadLocal<Date>();
    private static ThreadLocal<Map<String,SimpleDateFormat>> formats = new ThreadLocal<Map<String,SimpleDateFormat>>();
    
    public static void setStartTime(){
        Date now = now();
        threadLocalDate.set(now);
    }
    public static long endTime(){
        Date startTime = threadLocalDate.get();
        if(startTime==null){
            throw new FrameException("未调用setStartTime来设置开始统计计时时间","统计消耗时间");
        }
        Date endTime   = now();
        
        return endTime.getTime() - startTime.getTime();
    }
    public static Date now(){
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }
    public static String date2Str(Date date){
        return date2Str(date,null);
    }
    public static String date2Str(Date date,String pattern){
        return date2Str(date,pattern,null);
    }
    public static String date2Str(Date date,String pattern,String defaultDate){
        if(StringUtil.isEmpty(pattern)){
            pattern = DATETIME;
        }
        Map<String,SimpleDateFormat> mpformats = formats.get();
        if(mpformats == null){
            mpformats = new HashMap<String,SimpleDateFormat>();
            formats.set(mpformats);
        }
        SimpleDateFormat format = mpformats.get(pattern);
        if(format == null){
            format = new SimpleDateFormat(pattern);
            mpformats.put(pattern, format);
        }
        try{
            return format.format(date);
        }catch(Exception e){
            return defaultDate;
        }
    }
}
