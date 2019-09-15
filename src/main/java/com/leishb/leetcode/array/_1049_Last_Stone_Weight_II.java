package com.leishb.leetcode.array;

/**
 * Created by me on 2019/9/15.
 */
public class _1049_Last_Stone_Weight_II {

    public int lastStoneWeightII(int[] stones) {
        boolean[] dp = new boolean[3001];
        dp[0] = true;
        int sum = 0;
        for (int num : stones){
            sum += num;
            for (int i = sum;i>=num;i--){
                dp[i] |= dp[i-num];
            }
        }
        for (int i=sum/2;i>=0;i--){
            if (dp[i]) return sum -2*i;
        }
        return 0;
    }
}
