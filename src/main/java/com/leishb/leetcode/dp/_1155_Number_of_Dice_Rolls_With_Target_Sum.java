package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/20.
 */
public class _1155_Number_of_Dice_Rolls_With_Target_Sum {

    public int numRollsToTarget(int d, int f, int target) {
        int M = 1_000_000_007;
        int[][] dp = new int[d+1][target+1];
        dp[0][0] = 1;
        for (int i=1;i<=d;i++){
            for (int j=1;j<=target;j++){
                for (int k=1;k<=f;k++){
                    if (j>=k){
                        dp[i][j] = (dp[i][j] + dp[i-1][j-k])%M;
                    }
                }
            }
        }
        return dp[d][target];
    }


    @Test
    public void test(){
        Assert.assertTrue(numRollsToTarget(2,5,10)==1);
        Assert.assertTrue(numRollsToTarget(30,30,500)==222616187);
    }
}
