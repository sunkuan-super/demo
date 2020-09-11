package com.sk.flume;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @Title: FlumeLogUtils
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/11 - 17:58
 */
public class FlumeLogUtils {
    /**
     * 验证启动日志
     * @param log
     * @return
     */
    public static boolean validateStart(String log){
        if(StringUtils.isNotBlank(log)){
            return false;
        }
        // 判断数据是否是{开头，是否是}结尾
        if(!log.trim().startsWith("{") || !log.trim().endsWith("}")){
            return false;
        }
        return true;
    }

    /**
     * 验证事件日志
     * @param log
     * @return
     */
    public static boolean validateEvent(String log){
        // 服务器时间 | 日志内容
        if(StringUtils.isNotBlank(log)){
            return false;
        }
        String[] logContents = log.trim().split("\\|");
        // 检查服务器时间(长度必须是13位，必须全部是数字)
        if(logContents.length != 2){
            return false;
        }

        if(logContents[0].length()!=13 || NumberUtils.isDigits(logContents[0])){
            return false;
        }

        // 检验日志格式
        if(!logContents[1].trim().startsWith("{") || !logContents[1].endsWith("}")){
            return false;
        }
        return true;
    }
}
