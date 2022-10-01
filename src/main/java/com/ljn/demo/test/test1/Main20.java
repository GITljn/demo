package com.ljn.demo.test.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m =sc.nextInt();
        int dp[] = new int[n+1];
        int[][] arrs = new int[m][3];
        for (int i = 0; i < m; i++) {
            arrs[i][0] = sc.nextInt();
            arrs[i][1] = sc.nextInt();
            arrs[i][2] = sc.nextInt();
        }
        for (int i = 0; i < n+1; i++) {
            dp[i] = 1;
        }
        Arrays.sort(arrs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        for (int[] arr:arrs) {
            int temp = 0;
            for (int i = arr[0];i<=arr[1]-arr[2];i++) {
                if (0==dp[i]) {
                    ++temp;
                }
            }
            if (arr[1]<=arr[0]+arr[2]+temp ) {
                continue;
            }
            for (int j=arr[1];j>=arr[2]+arr[0]+temp;j--) {
                dp[j]=0;
            }
        }
        int sum = 0;
        for (int i = 0; i < dp.length; i++) {
            sum += dp[i];
        }
        System.out.println(sum-1);
    }
}
