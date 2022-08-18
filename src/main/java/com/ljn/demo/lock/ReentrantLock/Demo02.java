package com.ljn.demo.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {
    private static int i = 1;
    private static int n = 100;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= n) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        condition2.signal();
                        if (i <= n)
                            condition1.await();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= n) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                        condition1.signal();
                        if (i <= n)
                            condition2.await();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
