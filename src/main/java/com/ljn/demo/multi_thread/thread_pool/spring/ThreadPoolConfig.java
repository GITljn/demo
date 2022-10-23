package com.ljn.demo.multi_thread.thread_pool.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
// 开启定时任务，加了该注解spring才会创建定时线程池
// 在普通方法上加@Scheduled，方法会定时执行
@EnableScheduling
// 开启异步调用
// 在普通方法上加@Async，当方法被调用的时候，
// 会开启一个线程执行方法，无需定义任务在提交任务
@EnableAsync
public class ThreadPoolConfig {
}
