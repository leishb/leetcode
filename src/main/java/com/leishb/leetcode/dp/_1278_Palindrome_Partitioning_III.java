package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/12/26.
 */
public class _1278_Palindrome_Partitioning_III {


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public int palindromePartition(String s, int k) {
        int[][] cost = new int[s.length()][s.length()];
        for (int len=1;len<=s.length();len++){
            for (int i=0;i+len-1<s.length();i++){
                int j = i+len-1;
                if (len==1){
                    cost[i][j] = 0;
                }else if (s.charAt(i)==s.charAt(j)){
                    cost[i][j] = cost[i+1][j-1];
                }else {
                    cost[i][j] = cost[i+1][j-1] + 1;
                }
            }
        }
        int[][] dp = new int[k+1][s.length()];
        for (int i=0;i<s.length();i++){
            dp[1][i] = cost[0][i];
        }
        for (int i=2;i<=k;i++){
            for (int j=0;j<s.length();j++){
                dp[i][j] = j+1;
                for (int p=0;p<j;p++){
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][p] + cost[p+1][j]);
                }
            }
        }
        return dp[k][s.length()-1];
    }



    @Test
    public void test(){
        Assert.assertEquals(0, palindromePartition("abc", 3));
    }
}
