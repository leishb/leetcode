package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/7/16.
 */
public class _518_Coin_Change_2 {


    /**
     * TLE
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        return dfs(amount, coins, 0, new int[amount+1][coins.length+1]);
    }



    private int dfs(int amount , int[] coins, int start, int[][] memo){
        if (amount==0){
            return 1;
        }
        if (memo[amount][start] > 0){
            return memo[amount][start];
        }
        int count = 0;
        for (int i=start;i<coins.length;i++){
            if (amount >= coins[i]){
                count += dfs(amount-coins[i], coins, i, memo);
            }else {
                break;
            }
        }
        memo[amount][start] = count;
        return count;
    }


    /**
     * Accepted
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        if (amount==0)return 1;
        if (coins.length==0)return 0;
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for (int j=1;j<=amount;j++){
                if (j>=coins[i]){
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }


    /**
     * Accepted
     * @param amount
     * @param coins
     * @return
     */
    public int change3(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0]=1;
        for(int i=1;i<=coins.length;i++){
            for (int j=0;j<=amount;j++){
                if (j==0){
                    dp[i][j] = 1;
                }else if (j >= coins[i - 1]){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }


    @Test
    public void test(){
        Assert.assertTrue(change3(5, new int[]{1,2,5})==4);
        Assert.assertTrue(change3(5, new int[]{})==0);
        Assert.assertTrue(change3(0, new int[]{})==1);
    }
}
