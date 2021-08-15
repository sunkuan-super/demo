package com.sk.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title: LogTypeInterceptor
 * @Package: com.sk.flume
 * @Description:
 * @Author: sunkuan
 * @Date: 2020/9/11 - 18:28
 */
public class LogTypeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        Map<String, String> headers = event.getHeaders();
        if(log.contains("start")){
            headers.put("topic","topic_start");
        }else {
            headers.put("topic","topic_event");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        List<Event> eventList = new ArrayList<Event>();
        for (Event event : events) {
            Event intercept = intercept(event);
            eventList.add(intercept);
        }
        return eventList;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
