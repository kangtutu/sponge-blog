package com.kangtutu.sponge.blog.utils;

import java.util.Calendar;

//时间模板工具类
public class DateUtils {

    //获取年份
    public static Integer getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    //获取月份
    public static Integer getMonth(){
        //Calendar的月份是从0开始的，所以需要+1获取当前月份
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }

    //获取日期
    public static int getDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    //获取小时
    public static int getHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    //获取分钟
    public static int getMinute(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    //获取秒
    public static int getSecond(){
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    //获取当前 年-月-日 时：分：秒 时间格式
    public static String getDate(){
        return getYear()+"-"+getMonth()+"-"+getDay()+" "+getHour()+":"+getMinute()+":"+getSecond();
    }

}
