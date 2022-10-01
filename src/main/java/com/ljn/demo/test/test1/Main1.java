package com.ljn.demo.test.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int[] eN = new int[2];
        int[] eC = new int[2];
        int[] oN = new int[2];
        int[] oC = new int[2];
        Map<Integer, Integer> mO = new HashMap<>();
        Map<Integer, Integer> mE = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            if (0 == i % 2) {
                mE.put(arr[i], mE.getOrDefault(arr[i], 0)+1);
                int tN = mE.get(arr[i]);
                if (arr[i] == eN[0]) {
                    eC[0] = Math.max(tN, eC[0]);
                } else if (tN > eC[1]) {
                    eC[1] = tN;
                    eN[1] = arr[i];
                }
                if (eC[1] > eC[0] && eN[1]!=eN[0]) {
                    exchange(eC);
                    exchange(eN);
                }
            } else {
                mO.put(arr[i], mO.getOrDefault(arr[i], 0) + 1);
                int tN = mO.get(arr[i]);
                if (oN[0] == arr[i]) {
                    oC[0] = Math.max(tN, oC[0]);
                } else if (oC[1] < tN) {
                    oN[1] = arr[i];
                    oC[1] = tN;
                }
                if (oN[0] != oN[1] && oC[0] < oC[1]) {
                    exchange(oN);
                    exchange(oC);
                }
            }
        }
        int res = 0;
        if (eN[0] == oN[0]) {
            res = n - Math.max(oC[1]+eC[0],oC[0] +eC[1]);
        } else {
            res = n - (eC[0] + oC[0]);
        }
        System.out.println(res);
    }

    public static void exchange(int[] arr) {
        int a = arr[0];
        arr[0] = arr[1];
        arr[1] = a;
    }
}
