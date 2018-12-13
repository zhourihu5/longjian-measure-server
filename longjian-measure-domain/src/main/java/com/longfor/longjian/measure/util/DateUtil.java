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
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);


    //日期格式，精确到日 2017-4-16
    public static final DateFormat formatter = DateFormat.getDateInstance();

    //可以精确到秒  2017-4-16 12:43:37
    public static final DateFormat dateFormatter = DateFormat.getDateTimeInstance();


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
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        Long time = date1.getTime();
        String d = format.format(time);
        Date startDate = format.parse(d);
        return startDate;
    }

    /**
     * 获取传入日期前一天的短时间
     */
    public static Date getYeasterShortDateByDate(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        Long time = date1.getTime();
        String d = format.format(time);
        Date startDate = format.parse(d);
        return startDate;
    }

    public static Long getLongFromString(String time) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt2 = sdf.parse(time);
        long lTime = dt2.getTime();
        return lTime;
    }

    /**
     * 获取某个时间前7天（一周）短时间
     * @return
     * @throws ParseException
     */
    public static String getBeforeWeekShortDate(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - 7);
        Date date1 = calendar.getTime();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        Long time = date1.getTime();
        String d = format.format(time);
//        Date startDate = format.parse(d);
        return d;
    }

    /**
     * Long 转 短时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getShortDateStringByLong(Long dateLong) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(dateLong);
        return d;
    }

    /**
     * Long 转 长时间 字符串
     * @param dateLong
     * @return
     * @throws ParseException
     */
    public static String getDateStringByLong(Long dateLong) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(dateLong);
        return d;
    }

    /**
     * 通过短时间日期字符串转换时间再加一再转换时间字符串
     * @param startDate
     * @param day
     * @return
     * @throws ParseException
     */
    public static String getShortDateStringByStringDate(String startDate,int day) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = format.parse(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.DATE, day);
        Date endDate = calendar.getTime();
        Long time = endDate.getTime();
        String d = format.format(time);
        return d;
    }

    public static Long getLongFromShortString(String time) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date dt2 = sdf.parse(time);
        long lTime = dt2.getTime();
        return lTime;
    }

//    public static void main(String[] args) throws ParseException {
////        String category_key = "1605";
////        String categoryPathAndKey = "/1605/1605_21_0/1605_55_0/";
////        String key = "/" + category_key + "/";
////        if (categoryPathAndKey.charAt(0) == '/'){
////            categoryPathAndKey  = categoryPathAndKey.substring(1);
////        }
////        if (categoryPathAndKey.charAt(categoryPathAndKey.length() - 1) == '/'){
////            categoryPathAndKey = categoryPathAndKey.substring(0,categoryPathAndKey.length() - 1);
////        }
////        categoryPathAndKey = "/" + categoryPathAndKey + "/";
////        int index = categoryPathAndKey.indexOf(key);
//////        if (index < 0){
//////            return "";
//////        }
////        index += key.length();
////        String [] subKeys = categoryPathAndKey.substring(index).split("/");
////        System.out.print(subKeys[0]);
////        System.out.println(getYeasterShortDate(new Date()));
//        System.out.println(getShortDateStringByStringDate("2018-12-8",1));
//    }
}
