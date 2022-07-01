package com.ljn.demo.lock.SpinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用原子引用(CAS)实现自旋锁
 * 原理是通过循环等待达到加锁的功能
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName()+"加锁成功");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName()+"解锁成功");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"正在执行同步代码");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "A").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"正在执行同步代码");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "B").start();
    }
}
