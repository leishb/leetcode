package com.leishb.leetcode.dp;

import java.util.Arrays;

/**
 * Created by me on 2019/12/6.
 */
public class _1199_Minimum_Time_to_Build_Blocks {


    /**
     * Accepted
     * @param blocks
     * @param split
     * @return
     */
    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length;
        Arrays.sort(blocks);
        int i=0, j=n-1;
        while (i<j){
            int temp = blocks[i];
            blocks[i] = blocks[j];
            blocks[j] = temp;
            i++;j--;
        }
        int[][] memo = new int[n][n];
        return dfs(blocks, 0, 1, split, memo);
    }


    private int dfs(int[] blocks, int index,  int workers,  int cost, int[][] dp){
        if (index==blocks.length){
            return 0;
        }
        if (workers==0) return Integer.MAX_VALUE;
        if (workers>=blocks.length-index){
            return blocks[index];
        }
        if (dp[index][workers]!=0){
            return dp[index][workers];
        }
        int opt1 = Math.max(blocks[index], dfs(blocks, index+1, workers-1, cost, dp)); //assign blocks[index] to a worker
        int opt2 = dfs(blocks, index, workers*2, cost, dp) + cost; //split workers * 2
        dp[index][workers] = Math.min(opt1, opt2);
        return dp[index][workers];
    }

}
