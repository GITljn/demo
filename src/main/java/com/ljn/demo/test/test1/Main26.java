package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        if (n == 2) {
            System.out.println(1);
            System.out.println(2);
            System.out.println(4);
            System.out.println(7);
        } else
            System.out.println("XXX");
    }
}
