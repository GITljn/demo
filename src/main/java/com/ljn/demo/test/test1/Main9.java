package com.ljn.demo.test.test1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main9 {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();

        new Thread(()->{
            while (true) {
                try {
                    reentrantLock.lock();
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + i);

                    condition2.signal();
                    if (i == 99) {
                        break;
                    }
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    System.out.println("=========");
                    reentrantLock.unlock();
                }
            }
        }, "A").start();

        Thread.sleep(100);

//        new Thread(()->{
//            while (true) {
//                try {
//                    reentrantLock.lock();
//                    i++;
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                    if (i == 100) {
//                        break;
//                    }
//                    condition1.signal();
//                    try {
//                        condition2.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } finally {
//                    if (i==100) {
//                        System.out.println("========");
//                    }
//                    reentrantLock.unlock();
//                }
//            }
//        }, "B").start();
    }
}
