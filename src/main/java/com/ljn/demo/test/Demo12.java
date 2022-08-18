package com.ljn.demo.test;

import java.util.Scanner;

public class Demo12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int res = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int time = scanner.nextInt();
            if (time > t) {
                res++;
            }
        }
        System.out.println(res);
    }
}
