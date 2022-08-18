package com.ljn.demo.lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Demo05 {
    private ReentrantLock lock = new ReentrantLock();

    public void lock() {
        try {
            lock.lock();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void tryLock() {
        // tryLock可以指定等待时间
        if (lock.tryLock()) {
            try {
                lock.lock();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        } else {
            // 不能获取锁
        }
    }
}
