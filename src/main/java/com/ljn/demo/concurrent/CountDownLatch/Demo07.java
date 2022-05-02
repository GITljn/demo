package com.ljn.demo.concurrent.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Demo07 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i+1, countDownLatch)).start();
        }
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + ": 统计完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyThread implements Runnable {
        private int file;
        private CountDownLatch countDownLatch;

        public MyThread(int file, CountDownLatch countDownLatch) {
            this.file = file;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +": 正在统计" + file + "文件中的单词数目");
            try {
                Thread.sleep(new Random().nextInt(3000)+3000);
                System.out.println(Thread.currentThread().getName() +": 文件"+file+"统计完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
