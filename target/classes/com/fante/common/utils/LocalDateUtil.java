package com.fante.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: sunshinecredit
 * @Date: 2019/11/1 14:01
 * @Author: Mr.Z
 * @Description: 时间工具
 */
public class LocalDateUtil {

    /**
     * 判断target是否在start与end之间
     *
     * @param start yyyy-MM-dd HH:mm:ss
     * @param end   yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean betweenStartAndEnd(String start, String end, LocalDateTime targetLdt) {
        LocalDateTime startLdt = localDateTimeParse(start, DatePattern.yMdHms1.pattern());
        LocalDateTime endLdt = localDateTimeParse(end, DatePattern.yMdHms1.pattern());

        boolean result = true;
        if (targetLdt.compareTo(startLdt) < 0) {
            result = false;
        }
        if (targetLdt.compareTo(endLdt) > 0) {
            result = false;
        }
        return result;
    }

    /**
     * 判断target是否在start与end之间
     *
     * @param start  yyyy-MM-dd HH:mm:ss
     * @param end    yyyy-MM-dd HH:mm:ss
     * @param target yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean betweenStartAndEnd(String start, String end, String target) {
        LocalDateTime startLdt = localDateTimeParse(start, DatePattern.yMdHms1.pattern());
        LocalDateTime endLdt = localDateTimeParse(end, DatePattern.yMdHms1.pattern());
        LocalDateTime targetLdt = localDateTimeParse(target, DatePattern.yMdHms1.pattern());

        boolean result = true;
        if (targetLdt.compareTo(startLdt) < 0) {
            result = false;
        }
        if (targetLdt.compareTo(endLdt) > 0) {
            result = false;
        }
        return result;
    }


    /**
     * 当天零时
     *
     * @param datetime
     * @return
     */
    public static LocalDateTime dateTimeZero(LocalDateTime datetime) {
        return LocalDateTime.of(datetime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 当天结束
     *
     * @param datetime
     * @return
     */
    public static LocalDateTime dateTimeEnd(LocalDateTime datetime) {
        return LocalDateTime.of(datetime.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 将localDate 按照一定的格式转换成String
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDate
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate localDateParse(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeParse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        return localDateTimeFormat(dateToLocalDateTime(date), pattern);
    }

    /**
     * 将LocalDate 转换成 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param small
     * @param large
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime small, LocalDateTime large) {
        return Duration.between(small, large).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalTime(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     * @param time1
     * @param time2
     * @return
     */
    //public static Long minusToMillsLocalDate(LocalDate time1, LocalDate time2){
    //    return Duration.between(time1, time2).toMillis();
    //}

    /**
     * 计算两个LocalDate 之间的Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Period periodLocalDate(LocalDate time1, LocalDate time2) {
        return Period.between(time1, time2);
    }

    /**
     * 计算两个Date 之间的Period
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Period periodDate(Date date1, Date date2) {
        return periodLocalDate(dateToLocalDate(date1), dateToLocalDate(date2));
    }

    /**
     * 计算两个Date之间的 Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsDate(Date time1, Date time2) {
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1), dateToLocalDateTime(time2));
    }

    public enum DatePattern {

        /**
         * yyyy-MM-dd HH:mm:ss
         */
        yMdHms1("yyyy-MM-dd HH:mm:ss"),
        /**
         * yyyyMMddHHmmss
         */
        yMdHms2("yyyyMMddHHmmss"),
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        yMd1("yyyy-MM-dd"),
        /**
         * yyyyMMddHHmmss
         */
        yMd2("yyyyMMdd"),
        /**
         * HH:mm:ss
         */
        Hms1("HH:mm:ss"),
        ;

        String pattern;

        DatePattern(String pattern) {
            this.pattern = pattern;
        }

        public String pattern() {
            return pattern;
        }
    }
}

