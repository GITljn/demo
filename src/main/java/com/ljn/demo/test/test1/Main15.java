package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main15 {
    static boolean flag;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int x = sc.nextInt();
            flag = false;
            int[][] arr = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    arr[j][l] = sc.nextInt();
                }
            }
            dfs(arr, 0, 0, 0, x);
            if (flag)
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }

    public static void dfs(int[][] arr, int i, int j, int sum, int x) {
        int n = arr.length;
        int m = arr[0].length;
        if (i >= n || j >= m || flag) {
            return;
        }
        if (i == n-1 && j == m-1 && sum+arr[i][j] == x) {
            flag = true;
            return;
        }
        dfs(arr, i+1, j, sum+arr[i][j], x);
        dfs(arr, i, j+1, sum+arr[i][j], x);
    }
}
