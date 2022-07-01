package com.ljn.demo.lock.Semaphore;

import java.util.concurrent.Semaphore;

// 可以通过release增加信号的个数，可以超过信号的初始值
public class Demo06 {
    private static int i = 1;
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        semaphore.acquire(1);
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(1);
                    }
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        semaphore.acquire(1);
                        System.out.println(Thread.currentThread().getName() + ": " + i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(1);
                    }
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
