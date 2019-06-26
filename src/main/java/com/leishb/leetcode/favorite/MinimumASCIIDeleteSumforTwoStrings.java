package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/6/13.
 */
public class MinimumASCIIDeleteSumforTwoStrings {

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i=0;i<=s1.length();i++){
            for (int j=0;j<=s2.length();j++){
                if (i==0 && j==0){
                    dp[0][0] = 0;
                }else if (i==0){
                    dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
                }else if (j==0){
                    dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
                }else {
                    if (s1.charAt(i-1) == s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }else {
                        dp[i][j] = Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1]+s2.charAt(j-1));
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    @Test
    public void test(){
        Assert.assertTrue(minimumDeleteSum("sea", "eat")==231);
        Assert.assertTrue(minimumDeleteSum("delete", "leet")==403);

        System.out.println('s'+0);
    }



}
