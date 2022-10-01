package com.ljn.demo.test.test1;

import java.util.Arrays;
import java.util.Scanner;

public class Main32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int k = sc.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, (o1, o2)-> {
            return o2-o1;
        });
        int res = 0;
        for (int i = 0; i < n; i++) {
            int personTime = arr[i] * x;
            int carTime = y;
            if (carTime <= personTime && k > 0) {
                res += carTime;
                k--;
            } else {
                res += personTime;
            }
        }
        System.out.println(res);
    }
}
