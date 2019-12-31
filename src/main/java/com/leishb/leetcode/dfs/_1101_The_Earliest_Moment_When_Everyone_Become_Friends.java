package com.leishb.leetcode.dfs;

import java.util.Arrays;

/**
 * Created by me on 2019/11/26.
 */
public class _1101_The_Earliest_Moment_When_Everyone_Become_Friends {


    /**
     * Accepted
     * @param logs
     * @param N
     * @return
     */
    public int earliestAcq(int[][] logs, int N) {
        int[] parents = new int[N];
        int groups = N ;
        for (int i=0;i<N;i++){
            parents[i] = i;
        }
        Arrays.sort(logs, (l1 ,l2)->l1[0]-l2[0]);
        for (int i=0;i<logs.length;i++){
            int rx = find(logs[i][1], parents);
            int ry = find(logs[i][2], parents);
            if (rx!=ry){
                parents[rx] = ry;
                groups--;
            }
            if (groups==1) return logs[i][0];
        }
        return -1;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
