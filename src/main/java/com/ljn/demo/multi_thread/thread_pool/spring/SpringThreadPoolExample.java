package com.ljn.demo.multi_thread.thread_pool.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpringThreadPoolExample {
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    // 普通线程池
    void testThreadPoolTaskExecutor() {
        for (int i = 0; i < 10; i++) {
            threadPoolTaskExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    // 定时线程池
    void testThreadPoolTaskScheduler() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        threadPoolTaskScheduler.scheduleAtFixedRate(()->{
            System.out.println(sdf.format(new Date()));
        }, new Date(System.currentTimeMillis()+1000), 3000);
    }

    // 普通线程池(简化)
    void testThreadPoolTaskExecutorSimple() {
        // 1. 在线程池配置类上加@EnableAsync，开启异步调用
        // 2. 在需要多线程执行的方法上加@Async，方法相当于一个任务
        // 3. 每次调用方法会将该方法作为一个任务提交到线程池
    }

    // 定时线程池(简化)
    void testThreadPoolTaskSchedulerSimple() {
        // 1. 在线程池配置类上加@EnableScheduling，开启定时任务
        // 2. 在需要定时执行的方法上加@Scheduled
    }
}
