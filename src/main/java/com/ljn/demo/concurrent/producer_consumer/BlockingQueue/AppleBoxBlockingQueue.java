package com.ljn.demo.concurrent.producer_consumer.BlockingQueue;

import java.util.Random;
import java.util.concurrent.*;

public class AppleBoxBlockingQueue {
    private BlockingQueue<Integer> blockingQueue;
    private Random random = new Random();

    public AppleBoxBlockingQueue(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private void put() {
        try {
            int apple = random.nextInt(100);
            blockingQueue.put(apple);
            Thread.sleep(100);
            System.out.println("==生产者=="+Thread.currentThread().getName()+"==生产==了一个苹果"+apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void get() {
        try {
            Integer apple = blockingQueue.take();
            Thread.sleep(100);
            System.out.println("==消费者=="+Thread.currentThread().getName()+"==消费==了一个苹果"+apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Producer implements Runnable {
        private AppleBoxBlockingQueue appleBox;

        public Producer(AppleBoxBlockingQueue appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.put();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private AppleBoxBlockingQueue appleBox;

        public Consumer(AppleBoxBlockingQueue appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.get();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        AppleBoxBlockingQueue appleBox = new AppleBoxBlockingQueue(blockingQueue);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        int consumerNum = 2;
        int producerNum = 2;
        for (int i = 0; i < producerNum; i++) {
//            new Thread(new Producer(appleBox)).start();
            cachedThreadPool.execute(new Producer(appleBox));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < consumerNum; i++) {
            new Thread(new Consumer(appleBox)).start();
        }
    }
}
