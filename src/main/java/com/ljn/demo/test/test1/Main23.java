package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] arr = getArr(n, m);
        long res = 0;
        for (int i = 0; i+k-1 < n; i++) {
            for (int j = 0; j+k-1 < m; j++) {
                for (int l = 0; l < k; l++) {
                    for (int o = 0; o < k; o++) {
                        res += arr[i+l][j+o];
                    }
                }
            }
        }
        System.out.println(res);
    }

    public static int[][] getArr(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = gcd(i+1, j+1);
            }
        }
        return arr;
    }

    public static int gcd(int i, int j) {
        while ( i != j) {
            if (i <= j) {
                j = j - i;
            } else {
                i = i - j;
            }
        }
        return i;
    }
}
