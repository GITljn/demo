package com.ljn.demo.concurrent_programming.producer_consumer.BlockingQueue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"放入1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(()->{
            try {
                Thread.sleep(5000);
                synchronousQueue.take();
                System.out.println(Thread.currentThread().getName()+"取出1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
