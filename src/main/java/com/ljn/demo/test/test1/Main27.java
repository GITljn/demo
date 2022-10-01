package com.ljn.demo.test.test1;

import java.util.Arrays;
import java.util.Scanner;

public class Main27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strArr = str.split(" ");
        int[] resArr = new int[strArr.length];
        int[] numArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
        }
        resArr[0] = 1;
        for (int i = 1; i < numArr.length; i++) {
            if (numArr[i] > numArr[i-1]) {
                resArr[i] = resArr[i-1]+1;
            } else if (numArr[i] < numArr[i-1]) {
                resArr[i] = resArr[i-1]-1;
            } else {
                resArr[i] = resArr[i-1];
            }
        }
        int minIndex = 0;
        int min = resArr[0];
        for (int i = 1; i < resArr.length; i++) {
            if (resArr[i] < min) {
                min = resArr[i];
                minIndex = i;
            }
        }
        int i = minIndex;
        resArr[i] = 1;
        i++;
        while (i < resArr.length) {
            if (numArr[i] > numArr[i-1]) {
                resArr[i] = resArr[i-1]+1;
            } else if (numArr[i] < numArr[i-1]) {
                resArr[i] = 1;
            } else {
                resArr[i] = resArr[i-1];
            }
            i++;
        }
        i = minIndex-1;
        while (i >= 0) {
            if (numArr[i] > numArr[i+1]) {
                resArr[i] = resArr[i+1]+1;
            } else if (numArr[i] < numArr[i+1]) {
                resArr[i] = 1;
            } else {
                resArr[i] = resArr[i+1];
            }
            i--;
        }
        int res = 0;
        for (int j = 0; j < resArr.length; j++) {
            res += resArr[j];
        }
        System.out.println(res);
    }
}
