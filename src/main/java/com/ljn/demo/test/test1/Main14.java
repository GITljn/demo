package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j + i < n; j++) {
                if (isGood(arr, j, j+i, k)) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    public static boolean isGood(int[] arr, int i, int j, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        while (i <= j) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            i++;
        }
        return k * min == max;
    }
}
