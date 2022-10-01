package com.ljn.demo.test.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int start = 1;
        int year = sc.nextInt();
        while (start < year) {
            start++;
            List<Integer> newBorn = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                int temp = list.get(i);
                temp++;
                switch (temp) {
                    case 2 :
                    case 4:
                        newBorn.add(1);
                        break;
                }
                list.set(i, temp);
            }
            list.addAll(newBorn);
        }
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) <= 5) {
                res++;
            }
        }
        System.out.println(res);
    }
}
