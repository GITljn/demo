package com.ljn.demo.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo06 {
    static class Resource {
        private int num = 1;
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();

        public void printA() {
            lock.lock();
            try {
                while (num != 1) {
                    condition1.await();
                }
                System.out.println(Thread.currentThread().getName());
                num = 2;
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void printB() {
            lock.lock();
            try {
                while (num != 2) {
                    condition2.await();
                }
                System.out.println(Thread.currentThread().getName());
                num = 3;
                condition3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void printC() {
            lock.lock();
            try {
                while (num != 3) {
                    condition3.await();
                }
                System.out.println(Thread.currentThread().getName());
                num = 1;
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                resource.printA();
            }, "A").start();
            new Thread(()->{
                resource.printB();
            }, "B").start();
            new Thread(()->{
                resource.printC();
            }, "C").start();
        }
    }
}
