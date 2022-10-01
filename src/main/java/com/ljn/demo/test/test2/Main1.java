package com.ljn.demo.test.test2;

import java.util.Scanner;

public class Main1 {
    static int count = 0;
    static int m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        int[] arr = new int[n];
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

    }
    public static void helper(int[] arr, String[] strings, String s, int index) {
        while (index >= 0) {
            if (strings[index]==null) {
                strings[index] = s;
                count++;
            }
            index--;
        }
    }
}
