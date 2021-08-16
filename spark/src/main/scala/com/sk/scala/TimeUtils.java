package com.sk.scala;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Title: TimeUtils
 * @Package: com.sk.scala
 * @Description:
 * @Author: sk
 * @Date: 2021/6/15 - 11:24
 */
public class TimeUtils {
    public static String getDataTime(String longTime) {
        long l = Long.parseLong(longTime);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(l), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String format = formatter.format(localDateTime);

        return format;
    }
}
