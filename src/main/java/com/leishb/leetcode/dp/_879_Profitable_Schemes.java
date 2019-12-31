package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/12/30.
 */
public class _879_Profitable_Schemes {



    int M = 1_000_000_007;


    /**
     * Accepted
     * @param G
     * @param P
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int N = group.length;
        int[][][] dp = new int[N+1][G+1][P+1];
        dp[0][0][0] = 1;
        for (int k=1;k<=N;k++){
            for (int i=0;i<=G;i++){
                for (int j=0;j<=P;j++){
                    dp[k][i][j] = dp[k-1][i][j];
                    if (i>=group[k-1]){
                        dp[k][i][j] = (dp[k][i][j] + dp[k-1][i-group[k-1]][Math.max(0, j-profit[k-1])])%M;
                    }
                }
            }
        }
        int ans = 0;
        for (int i=0;i<=G;i++){
            ans = (ans + dp[N][i][P])%M;
        }
        return ans;
    }


    /**
     * Accepted
     * @param G
     * @param P
     * @param group
     * @param profit
     * @return
     */
    public int profitableSchemes2(int G, int P, int[] group, int[] profit) {
        int N = group.length;
        int[][] dp = new int[G + 1][P + 1];
        dp[0][0] = 1;
        for (int k=1;k<=N;k++){
            int g = group[k-1];
            int p = profit[k-1];
            for (int i=G;i>=g;i--){
                for (int j=P;j>=0;j--){
                    dp[i][j] = (dp[i][j] + dp[i-g][Math.max(0, j-p)])%M;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<=G;i++){
            ans = (ans + dp[i][P])%M;
        }
        return ans;
    }

}
