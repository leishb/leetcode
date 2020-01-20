package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/24.
 */
public class _1000_Minimum_Cost_to_Merge_Stones {


    public int mergeStones(int[] stones){
        int n = stones.length;
        int[] prefixSum = new int[n+1];
        for (int i=0;i<n;i++){
            prefixSum[i+1] = stones[i] + prefixSum[i];
        }
        int[][] dp = new int[n][n];
        for (int i=0;i<n;i++){
            dp[i][i] = 1;
        }
        for (int len = 2;len<=n;len++){
            for (int i=0;i+len-1<n;i++){
                int j = i+len-1;
                dp[i][j] = Integer.MAX_VALUE;
                int sum = prefixSum[j+1] - prefixSum[i];
                for (int k=i;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j] , sum + dp[i][k] + dp[k+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }


    /**
     * Accepted
     * @param stones
     * @param K
     * @return
     */
    public int mergeStones(int[] stones, int K) {
        if (stones.length > 1 && stones.length<K) return -1;
        int n = stones.length;
        if (K!=2 && n%(K-1)!=1) return -1;
        int[] prefixSum = new int[n+1];
        for (int i=0;i<n;i++){
            prefixSum[i+1] = stones[i] + prefixSum[i];
        }
        int[][][] dp = new int[n][n][K+1];
        int max = 99999999;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                for (int k=2;k<=K;k++){
                    dp[i][j][k] = max;
                }
            }
        }
        for (int len=2;len<=n;len++){
            for (int i=0;i+len-1<n;i++){
                int j = i+len-1;
                for (int k=2;k<=K;k++){
                    dp[i][j][k] = max;
                    for (int t=i;t<j;t++){
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][t][k-1]+dp[t+1][j][1]);
                    }
                }
                dp[i][j][1] = dp[i][j][K] + prefixSum[j+1]-prefixSum[i];
            }
        }
        return dp[0][n-1][1];
    }


    @Test
    public void test(){
        Assert.assertTrue(mergeStones(new int[]{3,2,4,1},2)==20);
        Assert.assertTrue(mergeStones(new int[]{3,5,1,2,6},3)==25);
        Assert.assertTrue(mergeStones(new int[]{6,4,4,6},2)==40);
        Assert.assertTrue(mergeStones(new int[]{36,2,61,30,74,35,65,31,43,92,15,11,22},5)==902);
    }
}
