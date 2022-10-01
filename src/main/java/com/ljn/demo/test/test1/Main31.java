package com.ljn.demo.test.test1;

import java.util.Arrays;

public class Main31 {
    public static void main(String[] args) {

    }

    int max = 0;
    public int maxScore (int energy, int[][] actions) {
        Arrays.sort(actions, ((o1, o2) -> o1[0]-o2[0]));
        helper(actions, energy, 0, 0);
        return max;
    }

    public void helper(int[][] actions, int energy, int idx, int num) {
        if (idx >= actions.length||actions[idx][0]> energy) {
            max = Math.max(num, max);
            return;
        }
        helper(actions, energy -actions[idx][0], idx +1, num +actions[idx][1]);
        helper(actions, energy, idx +1, num);
    }
}
