package com.ljn.demo.test;

import java.util.Scanner;

public class Demo01 {
    static int max = 0;
    static int sum = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        sum = arr[0][0]+arr[n-1][0]+arr[0][m-1]+arr[n-1][m-1];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                helper(arr, n, m, i, j);
            }
        }
        System.out.println(max);
    }

    private static void helper(int[][] arr, int n, int m, int i, int j) {
        int L = Math.min(n, m);
        for (int l = 2; i+l-1<n&&j+l-1<m; l++) {
            int ziSum = 0;
            int s = arr[i][j]+arr[i+l-1][j]+arr[i][j+l-1]+arr[i+l-1][j+l-1];
            if (s == sum) {
                for (int q = i; q <= i+l-1; q++) {
                    for (int p = j; p <= j+l-1; p++) {
                        ziSum+=arr[q][p];
                    }
                }
            }
            max = Math.max(max, ziSum);
        }
    }

}
