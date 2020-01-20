package com.leishb.leetcode.dp;

/**
 * Created by me on 2020/1/3.
 */
public class _375_Guess_Number_Higher_or_Lower_II {


    /**
     * Accepted
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        return calculate(1, n, new int[n+1][n+1]);
    }



    private int calculate(int start, int end, int[][] dp){
        if (start>=end) return 0;
        if (dp[start][end]!=0) return dp[start][end];
        int cost = Integer.MAX_VALUE;
        for (int i=(start+end)/2;i<=end;i++){
            cost = Math.min(cost, i +  Math.max(calculate(start, i-1, dp), calculate(i+1,end, dp)));
        }
        dp[start][end] = cost;
        return cost;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n+1][n+1];
        for (int len = 2;len <=n;len++){
            for (int i=1;i+len-1<=n;i++){
                int j = i+len-1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k=i;k<=j;k++){
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k-1] , (k==n?0:dp[k+1][j])));
                }
            }
        }
        return dp[1][n];
    }
}
