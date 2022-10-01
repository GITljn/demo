package com.ljn.demo.multi_thread.thread_pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Submit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            Future<Integer> f = es.submit(() -> 111);
            System.out.println(f.get());
        }
        es.shutdown();
    }
}
