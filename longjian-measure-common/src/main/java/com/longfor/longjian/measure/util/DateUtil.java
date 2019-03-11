package com.longfor.longjian.measure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by Wang on 2018/8/10.
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    // 取得本地时间：

    //日期格式，精确到日 2017-4-16
    public static final DateFormat formatter = DateFormat.getDateInstance();

    //可以精确到秒  2017-4-16 12:43:37
    public static final DateFormat dateFormatter = DateFormat.getDateTimeInstance();
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private DateUtil(){}

    /**
     * 返回短时间格式
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort(Date date)  {
        String dateString = formatter.format(date);
        Date dd= null;
        try {
            dd = formatter.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return dd;
    }

    /**
     * 将长时间格式转换短时间格式
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getDateShort(Date date)  {
        String dateString = formatter.format(date);
        Date dd= null;
        try {
            dd = formatter.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return dd;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        return format.format(date);
    }

    /**
     * 判断2个时间大小
     * @param startTime
     * @param endTime
     * @param nowTime
     * @return
     */
    public static boolean compareDate(String startTime,String endTime,String nowTime){
        boolean flag=true;
        try {
            Date st=dateFormatter.parse(startTime);
            Date en=dateFormatter.parse(endTime);
            Date now=dateFormatter.parse(nowTime);
            if(now.before(st)){
                flag=false;
            }
            if(en.before(now)){
                flag=true;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return flag;
    }


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


    /**
     * 获取前N天短时间
     * @return
     * @throws ParseException
     */
    public static Date getYeasterShortDate(int days) throws ParseException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Long time = date1.getTime();
        String d = format.format(time);
        return format.parse(d);
    }

    /**
     * 获取传入日期前一天的短时间
     */
    public static Date getYeasterShortDateByDate(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Long time = date1.getTime();
        String d = format.format(time);
        return format.parse(d);
    }

    public static Long getLongFromString(String time) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date dt2 = sdf.parse(time);
        return dt2.getTime();
    }

    /**
     * 获取某个时间前7天（一周）短时间
     * @return
     */
    public static String getBeforeWeekShortDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - 7);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Long time = date1.getTime();
        return format.format(time);
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

    /**
     * Long 转 短时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getShortDateStringByLong(Long dateLong){
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        return format.format(dateLong);
    }

    /**
     * Long 转 短时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getLongDateStringByLong(Long dateLong){
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return format.format(dateLong);
    }

    /**
     * 返回短时间格式
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getDateShortFromString(String dateString)  {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        Date dd= null;
        try {
            dd = format.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return dd;
    }


    /**
     * 2018-10-26T17:03:58+08:00 格式
     * @param date
     * @return
     */
    public static String getZoneDateTime(Date date) {
        DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS).withZone(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz");
        ZonedDateTime zoneTime = ZonedDateTime.parse(new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date) , formatter0);
        return zoneTime.withFixedOffsetZone().format(formatter);
    }

}
