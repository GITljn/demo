package com.ljn.demo.test.test1;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock1 {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock1() {
        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
        System.out.println(Thread.currentThread().getName()+"获取锁成功");
    }

    public void unLock1() {
        atomicReference.compareAndSet(Thread.currentThread(), null);
        System.out.println(Thread.currentThread().getName()+"释放锁成功");
    }

    public static void main(String[] args) {
        SpinLock1 spinLock1 = new SpinLock1();
        new Thread(() -> {
            spinLock1.lock1();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock1.unLock1();
        }, "a").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLock1.lock1();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock1.unLock1();
        }, "b").start();
    }
}
