package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/9/15.
 */
public class _1043_Partition_Array_for_Maximum_Sum {


    public int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length];
        for (int i=0;i<A.length;i++){
            int curMax = A[i];
            for (int j=1;j<=K && i-j+1>=0;j++){
                curMax = Math.max(curMax, A[i-j+1]);
                dp[i] = Math.max(dp[i], i>=j?dp[i-j]:0 + curMax*j);
            }
        }
        return dp[A.length-1];
    }
}
