package com.ljn.demo.Volatile;

public class Demo01 {
    public static class Data {
        volatile int num;
        void increment() {
            num++;
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            data.increment();
            System.out.println("子线程修改num");
        }, "子线程").start();

        // 如果num使用volatile，则一致循环
        while (data.num == 0) {
            // 如果输出data.num的值，不用volatile最后也会退出
            // System.out.println(data.num);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程得到num最新值"+data.num);
    }
}
