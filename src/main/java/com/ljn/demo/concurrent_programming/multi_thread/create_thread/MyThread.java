package com.ljn.demo.concurrent_programming.multi_thread.create_thread;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("子线程："+Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
        System.out.println("主线程："+Thread.currentThread().getName());
    }
}
