package com.ljn.demo.test;

import java.util.ArrayList;
import java.util.List;

public class Demo19 {
    List<List<Integer>> edges = new ArrayList<>();
    boolean res = true;
    int[] state;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] info: prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        state = new int[numCourses];
        for (int i = 0; i < numCourses && res; i++) {
            dfs(i);
        }
        return res;
    }

    private void dfs(int u) {
        state[u] = 1;
        for (Integer v: edges.get(u)) {
            if (state[v] == 0) {
                dfs(v);
                if (!res) {
                    return;
                }
            } else if (state[v] == 1){
                res = false;
                return;
            }
        }
        state[u] = 2;
    }
}
