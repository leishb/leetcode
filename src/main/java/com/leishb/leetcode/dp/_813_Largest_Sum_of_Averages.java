package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/15.
 */
public class _813_Largest_Sum_of_Averages {


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[] sums = new double[n+1];
        for (int i=0;i<n;i++){
            sums[i+1] = sums[i] + A[i];
        }
        double[][] dp = new double[K+1][n+1];
        for (int i=1;i<=n;i++){
            dp[1][i] = sums[i]/i;
        }
        for (int i=2;i<=K;i++){
            for (int j=i;j<=n;j++){
                for (int k=i-1;k<j;k++){
                    dp[i][j] = Math.max(dp[i-1][k]+(sums[j]-sums[k])/(j-k), dp[i][j]);
                }
            }
        }
        return dp[K][n];
    }


    @Test
    public void test(){
        Assert.assertTrue(largestSumOfAverages(new int[]{9,1,2,3,9}, 3)==20);
        Assert.assertTrue(largestSumOfAverages(new int[]{1,2,3,4,5,6,7}, 4)==20.5);
    }
}
