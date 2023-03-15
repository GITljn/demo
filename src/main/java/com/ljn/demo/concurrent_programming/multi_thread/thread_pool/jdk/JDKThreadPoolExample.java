package com.ljn.demo.concurrent_programming.multi_thread.thread_pool.jdk;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class JDKThreadPoolExample {
    @Test
    public void testExecute() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()-> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }

    @Test
    public void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            Future<Integer> f = executorService.submit(() -> 111);
            System.out.println(f.get());
        }
        executorService.shutdown();
    }

    @Test
    public void testScheduleThreadPool() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println(sdf.format(new Date()));
        }, 1000, 5000, TimeUnit.MILLISECONDS);

        Thread.currentThread().join();
    }
}
