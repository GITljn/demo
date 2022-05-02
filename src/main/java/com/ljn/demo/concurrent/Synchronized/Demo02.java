package com.ljn.demo.concurrent.Synchronized;

// notify和wait在同一个线程中，程序不能停止
// 要保证程序可以正常退出，则打印最后一个数后不可以调用wait，
public class Demo02 {
    private static int i = 1;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= 100) {
                    synchronized (Demo02.class) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        Demo02.class.notify();
                        try {
                            if (i <= 100)
                                Demo02.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i <= 100) {
                    synchronized (Demo02.class) {
                        System.out.println(Thread.currentThread().getName() + ":" + i++);
                        Demo02.class.notify();
                        try {
                            if (i <= 100)
                                Demo02.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
