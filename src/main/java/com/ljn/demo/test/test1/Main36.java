package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int b1 = -arr[0];
        int s1 = 0;
        int b2 = -arr[0];
        int s2 = 0;
        for (int i = 1; i < n; i++) {
            b1 = Math.max(b1, -arr[i]);
            s1 = Math.max(s1, b1+arr[i]);
            b2 = Math.max(b2, s1-arr[i]);
            s2 = Math.max(s2, b2+arr[i]);
        }
        System.out.println(s2);
    }
}
