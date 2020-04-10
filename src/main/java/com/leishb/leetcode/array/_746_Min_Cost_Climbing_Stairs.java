package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/21.
 */
public class _746_Min_Cost_Climbing_Stairs {


    /**
     * Accepted
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        return Math.min(dfs(cost, 0, dp), dfs(cost, 1, dp));
    }


    private int dfs(int[] cost, int index , int[] dp){
        if (index>=cost.length){
            return 0;
        }
        if (dp[index]!=0) return dp[index];
        int res = Math.min(dfs(cost, index+1, dp), dfs(cost, index+2, dp)) + cost[index];
        dp[index] = res;
        return res;
    }


    /**
     * Accepted
     * @param cost
     * @return
     */
    public int minCostClimbingStairs2(int[] cost) {
        int[] dp = new int[cost.length];
        for (int i=2;i<cost.length;i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return Math.min(dp[cost.length-1] + cost[cost.length-1], dp[cost.length-2] + cost[cost.length-2]);
    }
}
