package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        for (int i = 4; i <= n; i++) {
            arr[i] = (arr[i-1]+arr[i-2]+arr[i-3]+1) % 1000000007;
        }
        System.out.println(arr[n]);
    }
}
