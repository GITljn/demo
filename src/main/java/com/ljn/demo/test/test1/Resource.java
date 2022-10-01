package com.ljn.demo.test.test1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Resource {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capcity = 5;

    public synchronized void consume() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者" + Thread.currentThread().getName() + ":" + queue.poll());
        this.notifyAll();
    }

    public synchronized void produce() {
        while (queue.size() == capcity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int temp = new Random().nextInt(100);
        queue.add(temp);
        System.out.println("生产者" + Thread.currentThread().getName() + ":" + temp);
        this.notifyAll();
    }

}
