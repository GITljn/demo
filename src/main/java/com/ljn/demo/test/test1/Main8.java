package com.ljn.demo.test.test1;

public class Main8 {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (true) {
                synchronized (Main8.class) {
                    Main8.class.notifyAll();
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    if (i == 99)
                        break;
                    try {
                        Main8.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A").start();

        Thread.sleep(1000);
        new Thread(()->{
            while (true) {
                synchronized (Main8.class) {
                    Main8.class.notifyAll();
                    i++;
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    if (i == 100)
                        break;
                    try {
                        Main8.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();
    }
}
