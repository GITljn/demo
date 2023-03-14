package com.ljn.demo.concurrent_programming.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// await和signal分别在两个线程，如果是await线程打印最后一个数，则程序不会停止
public class Demo01 {
    private static int i = 1;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (i <= 100) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= 100) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
