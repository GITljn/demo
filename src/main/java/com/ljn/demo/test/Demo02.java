package com.ljn.demo.test;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Demo02 {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        Arrays.sort(nums);
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            int count = 1;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == nums[j-1] + 1) {
                    count++;
                } else {
                    i = j-1;
                    break;
                }
            }
            maxLen = Math.max(maxLen, count);
        }
        System.out.println(maxLen);
    }
}
