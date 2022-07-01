package com.ljn.demo.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 三个线程交替打印ABC
// 调用await之后会释放锁，所以忘了在finally中释放锁也是可以正常执行的
// 等待时间不能太短，否则无法打印完整信息
public class Demo05 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ": A");
                    condition2.signal();
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ": B");
                    condition3.signal();
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ": C");
                    condition1.signal();
                    try {
                        condition3.await();
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
