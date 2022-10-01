package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main35 {
    static int N = 35;
    static long[][] f = new long[N][N];
    static int[] w = new int[N];
    static int[][] root = new int[N][N];
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l+len-1 <= n; l++) {
                int r = l + len - 1;
                for (int k = l; k <= r; k++) {
                    long left = k == l ? 1 : f[l][k-1];
                    long right = k == r ? 1 : f[k+1][r];
                    long score = w[k] + left * right;
                    if (l == r) score = w[k];
                    if (f[l][r] < score) {
                        f[l][r] = score;
                        root[l][r] = k;
                    }
                }
            }
        }
        System.out.println(f[1][n]);
        out(1, n);
    }
    static void out (int l, int r) {
        if  (l > r) return;
        int k = root[l][r];
        System.out.println(k+" ");
        out(l, k-1);
        out(k+1, r);
    }
}
