package com.ljn.demo.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo02 {

    public static class Data {
        volatile int num;
        // 可以只用原子类或者加锁保证原子性
        AtomicInteger numAtomic = new AtomicInteger();

        void increment() {
            num++;
        }

        void incrementAtomic() {
            numAtomic.getAndIncrement();
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    data.increment();
                    data.incrementAtomic();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("num的最终结果为: " + data.num);
        System.out.println("numAtomic的最终结果为: " + data.numAtomic);
    }
}
