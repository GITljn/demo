package com.ljn.demo.concurrent.producer_consumer.Synchronized;

public class AppleBoxSynchronized {
    private final int capacity = 10;
    private int currentNum;

    private synchronized void put() {
        while (currentNum == capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentNum++;
        System.out.println("==生产者=="+Thread.currentThread().getName()+"==生产==了一个苹果，当前苹果数量为"+currentNum);
        this.notifyAll();
    }

    private synchronized void get() {
        while (currentNum == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentNum--;
        System.out.println("==消费者=="+Thread.currentThread().getName()+"==消费==了一个苹果，当前苹果数量为"+currentNum);
        this.notifyAll();
    }

    private static class Producer implements Runnable {
        private AppleBoxSynchronized appleBox;

        public Producer(AppleBoxSynchronized appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.put();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private AppleBoxSynchronized appleBox;

        public Consumer(AppleBoxSynchronized appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.get();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        AppleBoxSynchronized appleBox = new AppleBoxSynchronized();
        int consumerNum = 10;
        int producerNum = 10;
        for (int i = 0; i < consumerNum; i++) {
            new Thread(new Consumer(appleBox)).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < producerNum; i++) {
            new Thread(new Producer(appleBox)).start();
        }
    }
}
