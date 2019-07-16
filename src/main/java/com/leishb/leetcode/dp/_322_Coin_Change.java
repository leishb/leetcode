package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/7/16.
 */
public class _322_Coin_Change {


    /**
     * Accepted
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=1;i<=amount;i++){
            for (int j=0;j<coins.length;j++){
                if (i>=coins[j]){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }


    /**
     * Accepted
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=0;i<coins.length;i++){
            for (int j=1;j<=amount;j++){
                if (j>=coins[i]){
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

    @Test
    public void test(){
        Assert.assertTrue(coinChange2(new int[]{1,2,5}, 11)==3);
        long start = System.currentTimeMillis();
       Assert.assertTrue( coinChange2(new int[]{412,392,401,75,38,106,223} , 7802)==20);
        System.out.println("cost:" + (System.currentTimeMillis()-start));
    }
}
