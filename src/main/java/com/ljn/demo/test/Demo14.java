package com.ljn.demo.test;

import java.util.Arrays;
import java.util.Scanner;

public class Demo14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        String trace = sc.nextLine();
        int[][] vis = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i], 0);
        }
        int sum = n * m-1, x = 0, y = 0, id = 0;
        vis[0][0] = 1;
        for (int i = 0; i < k; i++) {
            char c = trace.charAt(i);
            switch (c) {
                case 'S' :
                    x+=1;
                    break;
                case 'W':
                    x-=1;
                    break;
                case 'A':
                    y-=1;
                    break;
                case 'D':
                    y+=1;
                default:
                    break;
            }
            if (vis[x][y] == 0) {
                vis[x][y] = 1;
                sum--;
            }
            if (sum == 0) {
                id = i;
                break;
            }
        }
        if (sum == 0) {
            System.out.println("YES");
            System.out.println(id+1);
        }else {
            System.out.println("No");
            System.out.println(sum);
        }
    }
}
