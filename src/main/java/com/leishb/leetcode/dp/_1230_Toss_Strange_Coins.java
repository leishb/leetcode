package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/11/27.
 */
public class _1230_Toss_Strange_Coins {


    /**
     * Accepted
     * @param prob
     * @param target
     * @return
     */
    public double probabilityOfHeads(double[] prob, int target) {
        double[][] dp = new double[target+1][prob.length+1];
        dp[0][0] = 1;
        for (int i=1;i<=prob.length;i++){
            dp[0][i] = dp[0][i-1] * (1-prob[i-1]);
        }
        for (int i=1;i<=target;i++){
            for (int j=i;j<=prob.length;j++){
                dp[i][j] = dp[i-1][j-1] * prob[j-1] + dp[i][j-1] * (1-prob[j-1]);
            }
        }
        return dp[target][prob.length];
    }
}
