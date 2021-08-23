package com.sk.util.file;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Title: ReadWriteFileDemo
 * @Package: com.sk.util.file
 * @Description:
 * @Author: sk
 * @Date: 2021/8/18 - 9:43
 */
public class ReadWriteFileDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

//        Stream<String> lines = Files.lines(Paths.get("E:\\Documents\\WeChat Files\\wxid_7555995544114\\FileStorage\\File\\2021-08\\泉州市link_id.csv"));
//        List<String> collect = lines.collect(Collectors.toList());
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//
//        long l = Long.parseLong("1617814620000");
//
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(l), ZoneId.systemDefault());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
//        String format = formatter.format(localDateTime);
//        System.out.println(localDateTime);
//        System.out.println(format);
//        System.out.println(Instant.ofEpochSecond(l));
//
//        System.out.println(simpleDateFormat.format(new Date(1617811143000L)));
//        System.out.println(simpleDateFormat.format(new Date(1617811200000L)));
//
//
//        System.out.println(simpleDateFormat.format(new Date(1617811200000L)));
//        System.out.println(simpleDateFormat.format(new Date(1617811121000L)));
//
//        System.out.println("-----------------------------------------------");
//        System.out.println(simpleDateFormat.format(new Date(1617814620000L)));
//        System.out.println(simpleDateFormat.format(new Date(1617814331000L)));

        System.out.println(getDataTime("1617814620"));
        dateToString();

    }

    public static String getDataTime(String longTime) {
        long l = Long.parseLong(longTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(l * 1000);
        String format = simpleDateFormat.format(date);

        return format;
    }

    public static void dateToString() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Date parse = simpleDateFormat.parse("2021-04-08 07:30:00");
        Date parse2 = simpleDateFormat.parse("2021-04-08 09:30:00");

        System.out.println(parse.getTime());
        System.out.println(parse2.getTime());
    }
}
