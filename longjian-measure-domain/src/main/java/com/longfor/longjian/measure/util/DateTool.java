package com.longfor.longjian.measure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by Wang on 2018/8/10.
 */
public class DateTool {

    private static final Logger logger = LoggerFactory.getLogger(DateTool.class);


    //日期格式，精确到日 2017-4-16
    public static final DateFormat formatter = DateFormat.getDateInstance();

    //可以精确到秒  2017-4-16 12:43:37
    public static final DateFormat dateFormatter = DateFormat.getDateTimeInstance();
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    private DateTool(){}



    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(Date date) {
        return dateFormatter.format(date);
    }

    /**
     * 判断2个时间大小
     * @param startTime
     * @param endTime
     * @param nowTime
     * @return
     */



    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort(Date date) {
        return formatter.format(date);
    }



    /**
     * 获取当前时间的时辰 2018-8-21 10:34 获取10
     * @param args
     */
    /**
     * 功能描述：返回小
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }





    public static Long getLongFromString(String time) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date dt2 = sdf.parse(time);
        return dt2.getTime();
    }

    /**
     * 获取某个时间前7天（一周）短时间
     * @return
     * @throws ParseException
     */
    public static String getBeforeWeekShortDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - 7);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Long time = date1.getTime();
        return format.format(time);
    }

    /**
     * Long 转 短时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getShortDateStringByLong(Long dateLong) {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        return format.format(dateLong);
    }

    /**
     * Long 转 长时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getDateStringByLong(Long dateLong){
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return format.format(dateLong);
    }

    /**
     * Long 转 长时间 Date
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static Date getDateByLong(Long dateLong) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        String d = format.format(dateLong);
        return format.parse(d);
    }

    /**
     * 通过短时间日期字符串转换时间再加一再转换时间字符串
     * @param startDate
     * @param day
     * @return
     * @throws ParseException
     */
    public static String getShortDateStringByStringDate(String startDate,int day) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Date startTime = format.parse(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.DATE, day);
        Date endDate = calendar.getTime();
        Long time = endDate.getTime();
        return format.format(time);
    }

    public static Long getLongFromShortString(String time) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat(YYYY_MM_DD);
        Date dt2 = sdf.parse(time);
        return dt2.getTime();
    }

}
