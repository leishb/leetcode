package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/3.
 */
public class _930_Binary_Subarrays_With_Sum {

    /**
     * MLE
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum(int[] A, int S) {
        int[][] dp = new int[A.length][S+1];
        if (A[0]==0)dp[0][0] = 1;
        for (int i=1;i<A.length;i++){
            if (A[i]==0){
                dp[i][0] = +dp[i-1][0] + 1;
            }
        }
        for (int i=1;i<=S;i++){
            for (int j=0;j<A.length;j++){
                if (j==0){
                    dp[j][i] = i==A[j]?1:0;
                    continue;
                }
                if (A[j]==0){
                    dp[j][i] += dp[j-1][i];
                }else {
                    dp[j][i] += dp[j-1][i-1];
                }
                if (i==A[j]){
                    dp[j][i] += 1;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<A.length;i++){
            ans += dp[i][S];
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum2(int[] A, int S) {
        int[] P = new int[A.length+1];
        for (int i=1;i<=A.length;i++){
            P[i] = P[i-1] + A[i-1];
        }
        Map<Integer, Integer> counts = new HashMap<>();
        int ans = 0;
        for (int i=0;i<P.length;i++){
            ans += counts.getOrDefault(P[i], 0);
            counts.put(P[i]+S, counts.getOrDefault(P[i]+S,0)+1);
        }
        return ans;
    }

    /**
     * Accepted
     * @param A
     * @param S
     * @return
     */
    public int numSubarraysWithSum3(int[] A, int S) {
        int left = 0;
        int right = 0;
        int leftSum = 0;
        int rightSum = 0;
        int ans = 0;
        for (int i=0;i<A.length;i++){
            leftSum += A[i];
            while (left<i && leftSum > S){
                leftSum -= A[left++];
            }
            rightSum += A[i];
            while (right<i && (rightSum>S || (rightSum==S && A[right]==0))){
                rightSum -= A[right++];
            }
            if (leftSum==S){
                ans += right-left+1;
            }
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(numSubarraysWithSum3(new int[]{1,0,1,0,1},2)==4);
    }
}
