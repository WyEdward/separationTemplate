package cn.wyedward.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtils
 *
 * @author bobbi
 * @date 2018/10/20 13:26
 * @email
 * @description 日期工具类
 */
public class DateUtils {
    /**
     * 对日期的分钟进行加/减
     * @param date
     * @param minutes
     * @return
     */
    public static long addDateMinutes(Date date, int minutes){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        return calendar.getTime().getTime();
    }

    /**
     * 格式化日期  返回
     * @param date
     * @param pattern
     * @return String
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return Date
     */
    public static Date parse(Date date, String pattern)  {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}