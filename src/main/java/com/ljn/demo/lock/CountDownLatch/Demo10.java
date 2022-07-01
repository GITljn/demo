package com.ljn.demo.lock.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Demo10 {
    public static Integer sum = 0;

    public static void main(String[] args) throws InterruptedException {
        Integer num = 3;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(new MyRunnable(countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("==========统计完成：共" + sum + "个");

    }

    public static class MyRunnable implements Runnable {
        private Random random = new Random();
        private CountDownLatch countDownLatch;

        public MyRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 开始统计");
            try {
                Thread.sleep(random.nextInt(1000) + 1000);
                int num = random.nextInt(10);
                synchronized (countDownLatch) {
                    sum += num;
                }
                System.out.println(Thread.currentThread().getName() + ": 统计完成，共" + num + "个");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
