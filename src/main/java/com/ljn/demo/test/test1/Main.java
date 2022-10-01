package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int id = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += sc.nextInt();
            }
            arr[i] = sum;
        }
        int id_import = arr[id-1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > id_import || arr[i] == id_import && i < id-1) {
                res++;
            }
        }
        System.out.println(res+1);
    }

}
