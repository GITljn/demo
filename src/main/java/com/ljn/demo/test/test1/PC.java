package com.ljn.demo.test.test1;

public class PC {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        for (int i = 0; i < 10; i++) {
            new Thread(resource::consume, String.valueOf(i)).start();
        }

        Thread.sleep(1000);

        for (int i = 0; i < 10; i++) {
            new Thread(resource::produce, String.valueOf(i)).start();
        }
    }
}
