package com.ljn.demo.test.test1;


import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int[] nums1 = new int[a];
        for (int i = 0; i < a; i++) {
            nums1[i] = sc.nextInt();
        }
        int b = sc.nextInt();
        int[] nums2 = new int[b];
        for (int i = 0; i < b; i++) {
            nums2[i] = sc.nextInt();
        }
        int[] res = get(nums1, nums2);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }

    public static int[] get(int nums1[], int nums2[]) {
        for (int a:nums2) {
            partition(nums1, a);
        }
        return nums1;
    }

    public static void partition(int[] a, int n) {
        int[] res = new int[a.length];
        int i = 0;
        int j = a.length-1;
        for (int num:a) {
            if (num < n) {
                res[i++] = num;
            } else if (num > n) {
                res[j--] = num;
            }
        }
        int k = a.length-1;
        j++;
        while (j < k) {
            int temp = res[k];
            res[k] = res[j];
            res[j] = temp;
            j++;
            k--;
        }
        res[i] = n;
        for (int l = 0; l < a.length; l++) {
            a[l] = res[l];
        }
    }
}
