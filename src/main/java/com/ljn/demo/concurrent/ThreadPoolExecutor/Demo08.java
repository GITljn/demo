package com.ljn.demo.concurrent.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo08 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                1L, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable());
        }
        // 线程池进入SHUTDOWN状态，不再接收新的任务但是会将当前正在执行的以及等待队列中的任务执行完成
        executor.shutdown();
        // 线程池进入STOP状态，立即停止当前正在执行的以及等待队列中的任务
        // executor.shutdownNow();
        // 调用shutdown后会返回true
        // executor.isShutdown();
        // 调用shutdown后且所有任务都执行完成会返回true
        // executor.isTerminated();
        while (!executor.isTerminated()) {}
        System.out.println("线程池结束");
    }

    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 正在运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 运行结束");
        }
    }
}
