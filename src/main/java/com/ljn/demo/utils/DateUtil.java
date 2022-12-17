package com.ljn.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期操作工具类
 *
 * @author qy
 * @since 1.0
 */
public class DateUtil {

    private DateUtil() {}

    private static final String dateFormat = "yyyy-MM-dd";

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 解析日期
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 在日期date上增加amount天 。
     * @param date   处理的日期，非null
     * @param amount 要加的天数，可能为负数
     */
    public static Date addDays(Date date, int amount) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DATE, amount);
//        now.set(Calendar.DATE,now.get(Calendar.DATE)+amount);
        return now.getTime();
    }

    /**
     * 计算两个日期之间相隔的天数
     * @param start
     * @param end
     * @return
     */
    public static int daysBetween(Date start, Date end) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(start);
        long milliseconds1 = calendar1.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(end);
        long milliseconds2 = calendar2.getTimeInMillis();
        long days = (milliseconds2-milliseconds1) / (1000*3600*24);
        return Integer.parseInt(String.valueOf(days));
    }

    /**
     * 计算两个日期之间的日期列表
     * @param start
     * @param end
     * @return
     */
    public static List<String> dayListBetween(Date start, Date end) {
        List<String> dayList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (!calendar.getTime().after(end)) {
            dayList.add(formatDate(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return dayList;
    }

    public static void main(String[] args) {
//        System.out.println(DateUtil.formatDate(new Date()));
//        System.out.println(DateUtil.formatDate(DateUtil.addDays(new Date(), 334)));
//        System.out.println(DateUtil.daysBetween(DateUtil.parseDate("2022-07-19"),
//                DateUtil.parseDate("2022-07-19")));
        System.out.println(111);
        Date date1 = DateUtil.parseDate("2022-10-28");
        Date date2 = DateUtil.parseDate("2022-11-03");
        System.out.println(DateUtil.dayListBetween(date1, date2));
    }
}
