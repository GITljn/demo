package com.ljn.demo.concurrent_programming.lock.ReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int[] array = new int[10];

    public void read() {
        readWriteLock.readLock().lock();
        try {
            for (int i = 0; i < array.length; i++) {
                System.out.println("读: " + array[i]);
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write(int index, int value) {
        readWriteLock.writeLock().lock();
        try {
            array[index] = value;
            System.out.println("写: " + value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
