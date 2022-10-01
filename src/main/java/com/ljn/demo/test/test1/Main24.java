package com.ljn.demo.test.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        Integer[] aBasicArr = new Integer[n];
        Integer[] bBasicArr = new Integer[n];
        int[] aXArr = new int[q];
        int[] bXArr = new int[q];
        for (int i = 0; i < n; i++) {
            aBasicArr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bBasicArr[i] = sc.nextInt();
        }
        for (int i = 0; i < q; i++) {
            aXArr[i] = sc.nextInt();
            bXArr[i] = sc.nextInt();
        }
        Arrays.sort(aBasicArr, Collections.reverseOrder());
        Arrays.sort(bBasicArr);
        for (int i = 0; i < q; i++) {
            int res = 0;
            for (int j = 0; j < n; j++) {
                if (aBasicArr[j]*aXArr[i] > bBasicArr[j]*bXArr[i]) {
                    res += aBasicArr[j]*aXArr[i] - bBasicArr[j]*bXArr[i];
                } else {
                    break;
                }
            }
            System.out.println(res);
        }
    }
}
