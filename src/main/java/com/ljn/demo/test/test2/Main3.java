package com.ljn.demo.test.test2;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        int res = 0;
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strings = line.split(" ");
        int len = strings.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        int[] temp = new int[len+1];
        for (int i = 0; i < len; i++) {
            temp[i+1] = temp[i]+arr[i];
        }
        for (int i = 0; i < len - 2; i++) {
            for (int j = i+1; j < len-1; j++) {
                int x = temp[i+1];
                int y = temp[j+1]-temp[i+1];
                int z = temp[arr.length]-temp[j+1];
                if (x <= y && y <= z ) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
