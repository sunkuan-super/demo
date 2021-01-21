package com.sk.util.threadpool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Title: TaskPoolConfig
 * @Package: com.sk.util.threadpool
 * @Description:
 * @Author: sunkuan
 * @Date: 2021/1/21 - 16:00
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskPoolConfig {

    @Value("${core.threadNum}")
    private int threadNum;

    @Value("${core.maxThreadNum}")
    private int coreMaxThreadNum;

    @Bean("taskExecutorExport")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadNum);//核心线程数目
        executor.setMaxPoolSize(coreMaxThreadNum);//指定最大线程数
        executor.setQueueCapacity(1000);//队列中最大的数目
        executor.setKeepAliveSeconds(60);//线程空闲后的最大存活时间
        executor.setThreadNamePrefix("Export-taskExecutor-");//线程名称前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }
}