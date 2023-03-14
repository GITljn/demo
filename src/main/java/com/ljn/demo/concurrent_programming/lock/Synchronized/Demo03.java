package com.ljn.demo.concurrent_programming.lock.Synchronized;

public class Demo03 {
    public Integer i = 0;

    public void increment() {
        i++;
    }

    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        new Thread(()->{
            while (true) {
                synchronized (demo03) {
                    demo03.increment();
                    System.out.println(Thread.currentThread().getName()+demo03.i);
                    demo03.notifyAll();
                    try {
                        demo03.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A").start();
        new Thread(()->{
            while (true) {
                synchronized (demo03) {
                    demo03.increment();
                    System.out.println(Thread.currentThread().getName()+demo03.i);
                    demo03.notifyAll();
                    try {
                        demo03.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();
    }
}
