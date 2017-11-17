package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;

/**
 * Created by me on 2017/11/15.
 */
public class MaximumLengthofRepeatedSubarray {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    @DynamicProgramming
    public int findLength(int[] A, int[] B){
        int[][] dp = new int[A.length][B.length];
        int max = 0;
        for (int i=0;i<B.length;i++){
            if (A[0]==B[i]){
                dp[0][i]=1;
                max =1;
            }
        }
        for (int i=0;i<A.length;i++){
            if (B[0]==A[i]){
                dp[i][0]=1;
                max=1;
            }
        }
        for (int i=1;i<A.length;i++){
            for (int j=1;j<B.length;j++){
                if (A[i]==B[j]){
                    dp[i][j] = dp[i-1][j-1] +1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    public int findLength2(int[] A, int[] B){
        int r = Math.min(A.length, B.length);
        return helper(A, B, 0, r);
    }


    private int helper(int[] A, int[] B, int l, int r){
        while (l<=r){
            int m = (l+r)/2;
            if (windowExist(A, B, m)){
                l=m+1;
            }else {
                r=m-1;
            }
        }
        return l;
    }

    private boolean windowExist(int[] A, int[] B, int k){
        return true;
    }
}
