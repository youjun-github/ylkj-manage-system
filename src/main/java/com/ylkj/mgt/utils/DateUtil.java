package com.ylkj.mgt.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 基于Java8的时间工具类
 * @author yanyong
 * @date 2019年4月13日 下午9:44:00
 */
public class DateUtil {
    /**
     * 例如:2018-12-28
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 例如:2018-12-28 10:00:00
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 例如:2018122810200122334
     */
    public static final String DATE_TIME_SSS = "yyyyMMddHHmmssSSS";
    /**
     * 例如:200122334
     */
    public static final String TIME_SSS = "HHmmssSSS";
    /**
     * 例如:10:00:00
     */
    public static final String TIME = "HHmmss";
    /**
     * 例如:10:00
     */
    public static final String TIME_WITHOUT_SECOND = "HH:mm";

    /**
     * 例如:2018-12-28 10:00
     */
    public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

    /**
     * @return
     * @Title: getYear
     * @Description: 获取年
     * @return int
     * @author yanyong
     * @date 2019年4月13日 下午9:41:08
     */
    public static int getYear() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.YEAR);
    }

    /**
     * @return
     * @Title: getMonth
     * @Description: 获取月份
     * @return int
     * @author yanyong
     * @date 2019年4月13日 下午9:41:23
     */
    public static int getMonth() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.MONTH_OF_YEAR);
    }

    /**
     * @return
     * @Title: getMonthOfDay
     * @Description: 获取某月的第几天
     * @return int
     * @author yanyong
     * @date 2019年4月13日 下午9:41:30
     */
    public static int getMonthOfDay() {
        LocalTime localTime = LocalTime.now();
        return localTime.get(ChronoField.DAY_OF_MONTH);
    }

    /**
     * @param date
     * @param pattern
     * @return String
     * @Title: format
     * @Description: 格式化日期为字符串
     * @author yanyong
     * @date 2019年4月13日 下午9:41:41
     */
    public static String format(Date date, String pattern) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @Title: parse
     * @Description: 解析字符串日期为Date
     * @author yanyong
     * @date 2019年4月13日 下午9:42:20
     * @returnc Date
     */
    public static Date parse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * @param date
     * @param second
     * @Title: addSecond
     * @Description: 为Date增加秒数
     * @author yanyong
     * @date 2019年4月13日 下午10:01:58
     * @return Date
     */
    public static Date addSecond(Date date, Long second) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusSeconds(second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date addSecond(Date date, Integer second) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusSeconds(second);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @Title: addMinutes
     * @Description: 为Date增加分钟,减传负数
     * @author yanyong
     * @date 2019年4月13日 下午9:59:37
     * @return Date 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param date
     * @param hour
     * @Title: addHour
     * @Description: 为Date增加小时
     * @author yanyong
     * @date 2019年4月13日 下午10:00:17
     * @return Date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @Title: getStartTime
     * @Description: 返回当天的起始时间
     * @author yanyong
     * @date 2019年4月13日 下午10:02:41
     * @return Date
     */
    public static Date getStartTime() {
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }

    /**
     * @Title: getEndTime
     * @Description: 返回当天的结束时间
     * @author yanyong
     * @date 2019年4月13日 下午10:02:51
     * @return Date
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }

    /**
     * @param monthsToSubtract
     * @Title: minusMonths
     * @Description: 减月份
     * @author yanyong
     * @date 2019年4月13日 下午10:03:05
     * @return Date
     */
    public static Date minusMonths(long monthsToSubtract) {
        LocalDate localDate = LocalDate.now().minusMonths(monthsToSubtract);
        return localDate2Date(localDate);
    }

    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 查询当前年的第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getFirstDayOfCurrentYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().withMonth(1).withDayOfMonth(1);
        if (CommonUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getLastMonthFirstDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).withMonth(12).withDayOfMonth(1);
        if (CommonUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 查询前一年最后一个月第一天
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getLastMonthLastDayOfPreviousYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now().minusYears(1L).with(TemporalAdjusters.lastDayOfYear());
        if (CommonUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (CommonUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * @param startTime
     * @param endTime
     * @Title: between
     * @Description: 计算两个时间间隔的秒数
     * @author yanyong
     * @date 2019年4月13日 下午10:20:59
     * @return long
     */
    public static Long between(Date startTime, Date endTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instantStart = startTime.toInstant();
        Instant instantEnd = endTime.toInstant();
        return ChronoUnit.SECONDS.between(LocalDateTime.ofInstant(instantStart, zone),
                LocalDateTime.ofInstant(instantEnd, zone));
    }

    /**
     * @return : java.lang.Long
     * @description: 计算两个时间间隔的毫秒数
     * @auturn: liyanyong
     * @date: 2019-04-17 17:06:40
     * @param: startTime
     * @param: endTime
     */
    public static Long betweenMillis(Date startTime, Date endTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instantStart = startTime.toInstant();
        Instant instantEnd = endTime.toInstant();
        return ChronoUnit.MILLIS.between(LocalDateTime.ofInstant(instantStart, zone), LocalDateTime.ofInstant(instantEnd, zone));
    }


    public static void main(String[] args) {
        System.out.println(format(new Date(), DATE_TIME));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(format(new Date(), DATE_TIME));
        // 当前时间
        Date startTime = parse("2019-04-13 22:28:18", DATE_TIME);
        // 结束时间
        Date endTime = parse("2019-04-13 22:28:28", DATE_TIME);
        System.out.println(between(startTime, endTime));
    }
}
