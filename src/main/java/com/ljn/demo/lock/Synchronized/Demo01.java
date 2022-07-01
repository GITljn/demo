package com.ljn.demo.lock.Synchronized;

import com.ljn.demo.lock.ReentrantLock.Demo03;

// notify和wait分别在两个线程中
// 程序是否可以正常退出取决于i的上限
public class Demo01 {
    private static int i = 1;

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
                    synchronized (Demo03.class) {
                        System.out.println(Thread.currentThread().getName()+ ":" + i++);
                        // notify并不会释放锁，只是唤醒其他线程，执行完同步代码块才会释放锁
                        Demo03.class.notify();
                    }
                    // 避免当前线程再次抢到锁，所以需要停顿，但是停顿不可以放到同步代码块，因为只有停顿结束之后才会释放锁
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
                    synchronized (Demo03.class) {
                        System.out.println(Thread.currentThread().getName()+ ":" + i++);
                        try {
                            Demo03.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
