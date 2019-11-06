package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/11/1.
 */
public class _256_Paint_House {


    /**
     * Accepted
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if (costs.length==0) return 0;
        int[] colors = new int[3];
        for (int i=0;i<3;i++){
            colors[i] = costs[0][i];
        }
        for (int i=1;i<costs.length;i++){
            int c0 = colors[0];
            int c1 = colors[1];
            int c2 = colors[2];
            colors[0] = Math.min(c1, c2) + costs[i][0];
            colors[1] = Math.min(c0, c2) + costs[i][1];
            colors[2] = Math.min(c0, c1) + costs[i][2];
        }
        return Math.min(colors[0], Math.min(colors[1], colors[2]));
    }
}
