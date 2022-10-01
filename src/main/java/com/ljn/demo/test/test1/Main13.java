package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 2) {
            System.out.println(0);
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] maxIndex = getMaxIndex(arr);
        int l = maxIndex[0];
        int r = maxIndex[1];
        if (l == 0) {
            int maxL = Math.abs(arr[r]-arr[r+1]);
            int updateR = (arr[l]+arr[r+1]) / 2;
            int maxR = Math.max(Math.abs(arr[l]-updateR), Math.abs(arr[r+1]-updateR));
            if (maxL < maxR) {
                arr[l] = arr[r];
            } else {
                arr[r] = updateR;
            }
        } else if (r == n-1) {
            int maxR = Math.abs(arr[l]-arr[l-1]);
            int updateL = (arr[l-1]+arr[r])/ 2;
            int maxL = Math.max(Math.abs(arr[l-1]-updateL), Math.abs(arr[r]-updateL));
            if (maxR < maxL) {
                arr[r] = arr[l];
            } else {
                arr[l] = updateL;
            }
        } else {
            int updateL = (arr[l-1]+arr[r]) / 2;
            int maxL = Math.max(Math.abs(arr[l-1]-updateL), Math.abs(arr[r]-updateL));
            int updateR = (arr[l]+arr[r+1]) / 2;
            int maxR = Math.max(Math.abs(arr[l]-updateR), Math.abs(arr[r+1]-updateR));
            if (maxL < maxR) {
                arr[l] = updateL;
            } else {
                arr[r] = updateR;
            }
        }
        maxIndex = getMaxIndex(arr);
        System.out.println(Math.abs(arr[maxIndex[0]]-arr[maxIndex[1]]));

    }

    public static int[] getMaxIndex(int[] arr) {
        int l = 0;
        int r = 1;
        long max = Math.abs(arr[l]-arr[r]);
        for (int i = 0; i < arr.length-1; i++) {
            if (Math.abs(arr[i] - arr[i+1]) > max) {
                max = Math.abs(arr[i] - arr[i+1]);
                l = i;
                r = i+1;
            }
        }
        return new int[]{l, r};
    }
}
