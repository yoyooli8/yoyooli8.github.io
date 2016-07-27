package com.mogoroom.test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate{
    @Test
    public void testDateDiff(){
        String date1 = "2017-09-15";
        String date2 = "2015-08-14";
        
        int diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        int diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        int diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2017-06-07";
        date2 = "2015-08-03";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2017-06-07";
        date2 = "2015-08-14";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2016-09-15";
        date2 = "2015-08-14";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2016-06-07";
        date2 = "2015-08-03";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2016-06-07";
        date2 = "2015-08-14";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2015-09-17";
        date2 = "2015-08-14";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
        date1 = "2015-09-07";
        date2 = "2015-08-14";
        
        diffYear  = getDateDiff(parseDate(date1),parseDate(date2),Calendar.YEAR);
        diffMonth = getDateDiff(parseDate(date1),parseDate(date2),Calendar.MONTH);
        diffDay   = getDateDiff(parseDate(date1),parseDate(date2),Calendar.DAY_OF_MONTH);
        
        System.out.println(diffYear+"年"+diffMonth+"月"+diffDay+"日");
        
    }
    private Date parseDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateStr,new ParsePosition(0));
    }
    private int getDateDiff(Date date1,Date date2,int diffType){
        Calendar cdate1 = Calendar.getInstance();
        cdate1.setTime(date1);
        Calendar cdate2 = Calendar.getInstance();
        cdate2.setTime(date2);
        
        if(cdate1.getTime().getTime() - cdate2.getTime().getTime() <=0 ){
            return 0;
        }
        int year1  = cdate1.get(Calendar.YEAR);
        int month1 = cdate1.get(Calendar.MONTH);
        int day1   = cdate1.get(Calendar.DAY_OF_MONTH);
        
        int year2  = cdate2.get(Calendar.YEAR);
        int month2 = cdate2.get(Calendar.MONTH);
        int day2   = cdate2.get(Calendar.DAY_OF_MONTH);
        
        if(Calendar.YEAR == diffType){//计算相差的年份数据
            if(year1 - year2 >= 1){
                return getDiffYear(year1,year2,month1,month2,day1,day2);
            }else{
                return 0;
            }
        }else if(Calendar.MONTH == diffType){//计算相差的月份数据,除去相差的年份数据
            int diffYear = getDateDiff(date1,date2,Calendar.YEAR);
            if(diffYear > 0){
                Calendar cdate3 = Calendar.getInstance();
                cdate3.set(Calendar.YEAR, year1 + diffYear);
                cdate3.set(Calendar.MONTH, month2);
                cdate3.set(Calendar.DAY_OF_MONTH, day2);
                
                int month3 = cdate3.get(Calendar.MONTH);
                int day3   = cdate3.get(Calendar.DAY_OF_MONTH);
                
                return getDiffMonth(month1,month3,day1,day3);
            }else{
                return getDiffMonth(month1,month2,day1,day2);
            }
            
        }else if(Calendar.DAY_OF_MONTH == diffType){
            int diffYear  = getDateDiff(date1,date2,Calendar.YEAR);
            int diffMonth = getDateDiff(date1,date2,Calendar.MONTH);
            Calendar cdate3 = Calendar.getInstance();
            cdate3.set(Calendar.YEAR, year2 + diffYear);
            cdate3.set(Calendar.MONTH, month2 + diffMonth);
            cdate3.set(Calendar.DAY_OF_MONTH, day2);
            
            long diffDay = 0;
            if((cdate1.getTime().getTime() - cdate3.getTime().getTime())%(24 * 3600000) == 0){
                diffDay = (cdate1.getTime().getTime() - cdate3.getTime().getTime())/(24 * 3600000);
            }else{
                diffDay = (cdate1.getTime().getTime() - cdate3.getTime().getTime())/(24 * 3600000) + 1;
            }
            return Long.valueOf(diffDay).intValue();
        }else{
            return 0;
        }
    }
    private int getDiffYear(int year1,int year2,int month1,int month2,int day1,int day2){
        if((month1 > month2) || (month1 == month2 && day1 > day2)){
            return year1 - year2;
        }else{
            return year1 - year2 - 1;
        }
    }
    private int getDiffMonth(int month1,int month2,int day1,int day2){
        if(month2 > month1){
            if(day1 >= day2){
                return 12 - month2 + month1;
            }else{
                return 12 - month2 + month1 - 1;
            }
        }else{
            if(month1 > month2 && day1 > day2){
                return month1 - month2;
            }else if(month1 > month2 && day1 < day2){
                int diffmonth = month1 - month2 -1;
                return diffmonth > 0 ? diffmonth : 0;
            }else{
                if(day1 > day2){
                    return 1;
                }else{
                    return 0;
                }
            }
        }
    }
}
