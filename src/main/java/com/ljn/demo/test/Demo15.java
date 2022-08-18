package com.ljn.demo.test;

import java.util.Scanner;

public class Demo15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
//        if (n == 1 && m==1) {
//            System.out.println("Yes");
//            System.out.println(0);
//        }
        int k = scanner.nextInt();
        boolean[][] arr = new boolean[n][m];
        arr[0][0] = true;
        if (n == 1 && m==1) {
            System.out.println("Yes");
            System.out.println(0);
        }
        String s = scanner.next();
        int num = 1;
        int x = 0;
        int y = 0;
        boolean flag = false;
        for (int i = 0; i < k; i++) {
            if (s.charAt(i) == 'W') {
                y-=1;
            }else if (s.charAt(i)=='S') {
                y+=1;
            } else if (s.charAt(i) == 'D') {
                x+=1;
            } else {
                x-=1;
            }
            if (arr[x][y]==false) {
                num++;
                arr[x][y] = true;
            }
            if (n*m==num) {
                System.out.println("Yes");
                flag = true;
                System.out.println(i+1);
                break;
            }
        }
        if (!flag) {
            System.out.println("No");
//            System.out.println(n*m-num);
            System.out.println(n*m-num);
        }
    }
}
