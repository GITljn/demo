package com.ljn.demo.concurrent_programming.lock.ReentrantLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 不加锁时读写、写写并发会出现问题
 * 使用读写时读读可以并发，读写、写写不会并发执行
 */
public class Demo04 {
    public static class Cache {
        private Map<String, Object> map = new HashMap<>();
        private ReentrantReadWriteLock  rwLock = new ReentrantReadWriteLock();

        public void put(String key, Object value) {
            System.out.println(Thread.currentThread().getName()+"正在写入数据: " + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入数据完成: " + key);
        }

        public Object get(String key) {
            System.out.println(Thread.currentThread().getName()+"正在读取数据: " + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取数据完成: " + key);
            return value;
        }

        public void putWithWriteLock(String key, Object value) {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"正在写入数据: " + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入数据完成: " + key);
            rwLock.writeLock().unlock();
        }

        public Object getWithReadLock(String key) {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"正在读取数据: " + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取数据完成: " + key);
            rwLock.readLock().unlock();
            return value;
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 5; i++) {
            final int tempI = i;
            new Thread(()->{
//                cache.put(tempI+"", tempI+"");
                cache.putWithWriteLock(tempI+"", tempI+"");
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempI = i;
            new Thread(()->{
//                cache.get(tempI+"");
                cache.getWithReadLock(tempI+"");
            }).start();
        }
    }
}
