package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/2.
 */
public class _918_Maximum_Sum_Circular_Subarray {

    public int maxSubarraySumCircular(int[] A) {
        int[] sums = new int[A.length+1];
        for (int i=0;i<A.length;i++){
            sums[i+1] = sums[i] + A[i];
        }
        int total = sums[A.length];
        int max = Integer.MIN_VALUE;
        for (int i=0;i<A.length;i++){
            max = Math.max(max, A[i]);
            for (int j=i+1;j<A.length;j++){
                int sum = sums[j+1]-sums[i];
                max = Math.max(max, sum);
                max = Math.max(total-sum+A[i]+A[j], max);
            }
        }
        return max;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int maxSubarraySumCircular2(int[] A) {
        if(A.length==0)return 0;
        int[] dp = new int[A.length];
        dp[0] = A[0];
        int max = A[0];
        int total = A[0];
        for (int i=1;i<A.length;i++){
            dp[i] = dp[i-1] >0? dp[i-1] + A[i]:A[i];
            max = Math.max(dp[i], max);
            total += A[i];
        }
        int min = A[0];
        for (int i=1;i<A.length;i++){
            dp[i] = dp[i-1] <0? dp[i-1] + A[i]:A[i];
            min = Math.min(min, dp[i]);
        }
        if (total!=min){
            max = Math.max(max, total-min);
        }
        return max;
    }


    public int maxSubarraySumCircular3(int[] A) {
        int max = A[0];
        int sum = A[0];
        for (int i=1;i<A.length;i++){
            sum = sum>0?sum+A[i]:A[i];
            max = Math.max(sum ,max);
        }
        int[] maxRight = new int[A.length];
        maxRight[A.length-1] = A[A.length-1];
        int cur = A[A.length-1];
        for (int i=A.length-2;i>=0;i--){
            cur = cur + A[i];
            maxRight[i] = Math.max(maxRight[i+1], cur);
        }
        cur = 0;
        for (int i=0;i<A.length-2;i++){
            cur += A[i];
            max = Math.max(max, cur + maxRight[i+2]);
        }
        return max;
    }

    @Test
    public void test(){
        Assert.assertTrue(maxSubarraySumCircular3(new int[]{5,-3,5})==10);
        Assert.assertTrue(maxSubarraySumCircular3(new int[]{5, -3, 2 ,-1, 1, 5})==12);
        Assert.assertTrue(maxSubarraySumCircular3(new int[]{3,-1,2,-1})==4);
        Assert.assertTrue(maxSubarraySumCircular3(new int[]{-2,-3,-1})==-1);
    }
}
