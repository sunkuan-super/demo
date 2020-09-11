package com.sk.flume;


import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: LogETLInterceptor
 * @Package: com.sk.hdfs
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/11 - 17:34
 */
public class LogETLInterceptor implements Interceptor {
    public void initialize() {

    }

    public Event intercept(Event event) {
        // 清洗数据     ETL  {}  => { xxx 脏数据

        // 获取日志
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));
        if(log.contains("start")){
            // 验证启动日志的逻辑
            if(FlumeLogUtils.validateStart(log)){
                return event;
            }
        }else {
            // 验证时间日志的逻辑
            if(FlumeLogUtils.validateEvent(log)){
                return event;
            }
        }
        // 区分类型处理
        return null;
    }

    public List<Event> intercept(List<Event> eventList) {
        // 多event处理
        List<Event> events = new ArrayList<Event>();
        // 取出校验合格的数据返回
        for (Event event : eventList) {
            Event intercept = intercept(event);

            if(intercept != null){
                events.add(intercept);
            }
        }
        return events;
    }

    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
