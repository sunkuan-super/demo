package com.sk.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {


    /**
     * 日期格式
     */
    private static DateTimeFormatter[] dtf = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyyMMdd")
    };


    /**
     * 时区 Asia/Shanghai
     */
    private static ZoneId zoneId = ZoneId.systemDefault();


    /**
     * 当前日期字符串
     *
     * @return 日期字符串
     */
    public static String currentDateString(DateTimeFormatter dtf) {
        LocalDate localDate = LocalDate.now();
        String dateStr = localDate.format(dtf);

        return dateStr;
    }


    /**
     * 当前时间字符串
     *
     * @param dtf DateTimeFormatter
     * @return 字符串类型的时间
     */
    public static String currentTimeString(DateTimeFormatter dtf) {
        LocalDateTime now = LocalDateTime.now();

        // localDateTime转字符串
        return now.format(dtf);
    }


    /**
     * 当前日期的时间戳
     *
     * @return 时间戳
     */
    public static long currentDateLong() {
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Instant instant = zonedDateTime.toInstant();

        long timestamp = instant.toEpochMilli();

        return timestamp;
    }


    /**
     * 当前时间的时间戳
     *
     * @return 当前时间戳
     */
    public static long currentTimeLong() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        return timestamp.getTime();
    }


    /**
     * 字符串转时间戳
     *
     * @param dateStr 字符串
     * @return 时间戳
     */
    public static long string2TimeStamps(String dateStr) {
        // 字符串转为LocalDate
        LocalDate localDate = LocalDate.parse(dateStr, dtf[0]);
        // 设置时区  2021-05-20T00:00+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        // 2021-05-19T16:00:00Z
        Instant instant = zonedDateTime.toInstant();
        // Instant转时间戳
        long timestamp = instant.toEpochMilli();

        return timestamp;
    }


    /**
     * 字符串转时间戳
     *
     * @param dateStr 字符串
     * @return 时间戳
     */
    public static long string2TimeStamps(String dateStr, DateTimeFormatter dtf) {
        // 字符串转为LocalDate
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dtf);
        // 设置时区  2021-05-20T00:00+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        // 2021-05-19T16:00:00Z
        Instant instant = zonedDateTime.toInstant();
        // Instant转时间戳
        long timestamp = instant.toEpochMilli();

        return timestamp;
    }


    /**
     * localDateTime转为 Long
     *
     * @param localDate LocalDate
     * @return Long类型
     */
    public static long localDate2Long(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        Instant instant = zonedDateTime.toInstant();
        long timestamp = instant.toEpochMilli();

        return timestamp;
    }


    /**
     * localDateTime转为 Long
     *
     * @param localDateTime LocalDateTime
     * @return Long类型
     */
    public static long localDateTime2Long(LocalDateTime localDateTime) {
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        long timestamps = timestamp.getTime();
        return timestamps;
    }


    /**
     * 当天的 0点时刻
     *
     * @return 当天的 0点时刻
     */
    public static Long getTodayTimeBeginMs() {
        //获取当前日期
        LocalDateTime beginTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.out.println("获取当天开始时间：" + beginTime);

        return LocalDateTime.from(beginTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 当天结束时间
     *
     * @return 当天结束的时间
     */
    public static Long getTodayTimeEndMs() {
        //获取当前日期
        LocalDate nowDate = LocalDate.now();
        //设置零点
        LocalDateTime beginTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        System.out.println("获取当天结束时间:" + beginTime);
        return LocalDateTime.from(beginTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 当天的0点时刻(Long型)
     *
     * @return
     */
    public static Long longTodayTimeBeginMs(Long time) {
        LocalDate longToLocalDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()).toLocalDate();
        LocalDateTime beginTime = LocalDateTime.of(longToLocalDate, LocalTime.MIN);
        return LocalDateTime.from(beginTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * LocalDate转Time字符串
     *
     * @param localDate LocalDate
     * @return LocalDateTime
     */
    public static LocalDateTime localDate2LocalDateTime(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.parse("00:00:00"));

        return localDateTime;
    }


    /**
     * long转Date字符串
     *
     * @param timestamp
     * @return
     */
    public static String long2StringDate(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
        LocalDate localDate = localDateTime.toLocalDate();
        String stringDate = dtf[0].format(localDate);

        return stringDate;
    }


    /**
     * long转String
     *
     * @param timestamp 时间戳
     * @return 时间字符串
     */
    public static String long2StringTime(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
        String stringTime = dtf[1].format(localDateTime);

        return stringTime;
    }


    public static void test() {
        DateTimeFormatter ftf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter ftf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.systemDefault();

        System.out.println("string -> long型的年月日：：" + LocalDate.parse("2018-03-11", ftf1).atStartOfDay(zoneId).toInstant().toEpochMilli());
        System.out.println("String -> LocalDate:  " + LocalDate.parse("2018-03-11", ftf1));
        System.out.println("String -> LocalDateTime:  " + LocalDateTime.parse("2020-07-15 00:00:00", ftf2));

        System.out.println("long -> String型年月日：" + ftf1.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1594794323956l), zoneId)));
        System.out.println("Long -> Date: " + new Date(1520754566856L));
        System.out.println("Long -> LocalDateTime:  " + LocalDateTime.ofInstant(Instant.ofEpochMilli(1594794323956l), ZoneId.systemDefault()));
        System.out.println("Long -> LocalDate:  " + LocalDateTime.ofInstant(Instant.ofEpochMilli(1594794323956l), ZoneId.systemDefault()).toLocalDate());

        System.out.println("LocalDateTime -> Long:  " + Timestamp.valueOf(LocalDateTime.now()).getTime());
        System.out.println("LocalDateTime -> String:  " + LocalDateTime.now().format(ftf2));

        System.out.println("LocalDate -> LocalDateTime: " + LocalDateTime.of(LocalDate.now(), LocalTime.parse("00:00:00")));
        System.out.println("LocalDate -> Long: " + LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println("LocalDate -> Date: " + Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println("LocalDate -> String: " + LocalDate.now().format(ftf1));

        System.out.println("Date -> String:  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("Date -> LocalDateTime:  " + LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        System.out.println("Date -> Timestamp:  " + new Timestamp(new Date().getTime()));
        System.out.println("Date -> Long: " + new Date().getTime());
        System.out.println("Date -> Instant:  " + new Date().toInstant());

        System.out.println("long -> Long型的当天开始时间：" + longTodayTimeBeginMs(1594794323956l));
        System.out.println("当天开始时间Long型:" + getTodayTimeBeginMs());
        System.out.println("获取当天结束时间Long型:" + getTodayTimeEndMs());
    }


    public static String getDataTime2(String longTime) {
        long l = Long.parseLong(longTime);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(l), ZoneId.systemDefault());
        System.out.println(localDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String format = formatter.format(localDateTime);

        return format;
    }


    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        String dataTime2 = getDataTime2("" + l);
    }
}
