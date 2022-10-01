package com.ljn.demo.multi_thread.create_thread;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("子线程："+Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        thread.join();
        System.out.println("主线程："+Thread.currentThread().getName());
    }
}
