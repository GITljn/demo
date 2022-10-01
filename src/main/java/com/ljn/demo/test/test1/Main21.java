package com.ljn.demo.test.test1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main21 {
    public static void main(String[] args) {
        Box box = new Box();
//        for (int i = 0; i < 100; i++) {
//            new Thread(()->{
//                box.get();
//            }, String.valueOf(i)).start();
//        }
//        for (int i = 0; i < 100; i++) {
//            new Thread(()->{
//                box.add();
//            }, String.valueOf(i)).start();
//        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(box::get);
        }
        for (int i = 0; i < 100; i++) {
            executorService.execute(box::add);
        }
    }
}

class Box {
    private Queue<Integer> queue = new LinkedList<>();
    private int capcity = 10;

    public synchronized void add() {
        while (queue.size() == capcity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int temp = new Random().nextInt(10);
        queue.offer(temp);
        System.out.println("生产者"+Thread.currentThread().getName() + ":" + queue.size());
        this.notifyAll();
    }

    public synchronized void get() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int temp = queue.poll();
        System.out.println("消费者"+Thread.currentThread().getName()+":"+queue.size());
        this.notifyAll();
    }
}
