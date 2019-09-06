package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/9/3.
 */
public class _931_Minimum_Falling_Path_Sum {

    /**
     * Accepted
     * @param A
     * @return
     */
    public int minFallingPathSum(int[][] A) {
        if (A.length==1) return A[0][0];
        int[][] dp = new int[A.length][A.length];
        for (int i=0;i<A.length;i++){
            dp[0][i] = A[0][i];
        }
        int ans = Integer.MAX_VALUE;
        for (int i=1;i<dp.length;i++){
            for (int j=0;j<A.length;j++){
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j+1]) + A[i][j];
                }else if (j==A.length-1 ){
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]) + A[i][j];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j+1]), dp[i-1][j-1]) + A[i][j];
                }
                if (i==dp.length-1){
                    ans = Math.min(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
