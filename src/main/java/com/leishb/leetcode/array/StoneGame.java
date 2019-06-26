package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by me on 2019/6/24.
 */
public class StoneGame {


    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        int sum = 0;
        for (int i=piles.length-1;i>=0;i--){
            sum += piles[i];
            for (int j=i;j<piles.length;j++){
                if (i==j){
                    dp[i][i] = piles[i];
                }else {
                    dp[i][j] = Math.max(dp[i+1][j]+piles[i], dp[i][j-1]+piles[j]);
                }
            }
        }
        return dp[0][piles.length-1] > sum/2;
    }


    @Test
    public void test(){

        for (int i=10;i<100;i+=2){
            int[] piles = new int[i];
            for (int j=0;j<i;j++){
                piles[j] = new Random().nextInt(100);
            }
            System.out.println(i);
            Assert.assertTrue(stoneGame(piles));
        }
    }
}
