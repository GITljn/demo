package com.ljn.demo.lock.CountDownLatch;

import java.util.Random;
import java.util.concurrent.*;

// submit.get();会阻塞当前线程，并没有实现真正的并发
public class Demo09 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 1L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        for (int i = 0; i < 10; i++) {
            MyCallable task = new MyCallable();
            Future submit = executor.submit(task);
            Object o = submit.get();
            System.out.println((int)o);
        }
    }

    public static class MyCallable implements Callable {
        private Random random = new Random();

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + ": 开始统计");
            Thread.sleep(random.nextInt(5000) + 5000);
            System.out.println(Thread.currentThread().getName() + ": 统计完成");
            return random.nextInt(200)+100;
        }
    }
}
