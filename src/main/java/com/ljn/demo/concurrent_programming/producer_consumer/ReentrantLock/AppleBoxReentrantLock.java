package com.ljn.demo.concurrent_programming.producer_consumer.ReentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AppleBoxReentrantLock {
    private static final int capcity = 10;
    private Queue<Integer> queue;
    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyCondition = lock.newCondition();
    private Condition fullCondition = lock.newCondition();

    public AppleBoxReentrantLock(Queue<Integer> queue) {
        this.queue = queue;
    }

    private void put() {
        int num = new Random().nextInt(100);
        queue.offer(num);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==生产者=="+Thread.currentThread().getName()+"==生产==了一个苹果，当前苹果数量为"+queue.size());
    }

    private void get() {
        queue.poll();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==消费者=="+Thread.currentThread().getName()+"==消费==了一个苹果，当前苹果数量为"+queue.size());
    }

    private static class Producer implements Runnable {
        private AppleBoxReentrantLock appleBox;

        public Producer(AppleBoxReentrantLock appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    appleBox.lock.lock();
                    while (appleBox.queue.size() == AppleBoxReentrantLock.capcity) {
                        System.out.println("==生产者=="+Thread.currentThread().getName()+"被阻塞");
                        appleBox.fullCondition.await();
                    }
                    appleBox.put();
                    // 唤醒所有生产者消费者
//                    appleBox.emptyCondition.notifyAll();
//                    appleBox.fullCondition.notifyAll();
                    appleBox.emptyCondition.signalAll();
                    appleBox.fullCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    appleBox.lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private AppleBoxReentrantLock appleBox;

        public Consumer(AppleBoxReentrantLock appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    appleBox.lock.lock();
                    while (appleBox.queue.isEmpty()) {
                        System.out.println("==消费者=="+Thread.currentThread().getName()+"被阻塞");
                        appleBox.emptyCondition.await();
                    }
                    appleBox.get();
                    // 唤醒所有生产者消费者
//                    appleBox.emptyCondition.notifyAll();
//                    appleBox.fullCondition.notifyAll();
                    appleBox.emptyCondition.signalAll();
                    appleBox.fullCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    appleBox.lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        AppleBoxReentrantLock appleBox = new AppleBoxReentrantLock(new LinkedList<>());
        int consumerNum = 8;
        int producerNum = 10;
        for (int i = 0; i < consumerNum; i++) {
            new Thread(new Consumer(appleBox)).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < producerNum; i++) {
            new Thread(new Producer(appleBox)).start();
        }
    }
}
