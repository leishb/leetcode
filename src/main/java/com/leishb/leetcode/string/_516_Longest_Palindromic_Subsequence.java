package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/15.
 */
public class _516_Longest_Palindromic_Subsequence {


    /**
     * Accepted
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s==null||s.length()==0)return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            for (int j=0;i+j<s.length();j++){
                if (i==0){
                    dp[j][i+j] = 1;
                }else {
                    if (s.charAt(i+j) == s.charAt(j)){
                        dp[j][i+j] = dp[j+1][i+j-1]+2;
                    }else {
                        dp[j][i+j] = Math.max(dp[j+1][i+j], dp[j][i+j-1]);
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }


    @Test
    public void test(){
        Assert.assertTrue(longestPalindromeSubseq("cbbd")==2);
        Assert.assertTrue(longestPalindromeSubseq("cdbbdcyxyz")==6);
        Assert.assertTrue(longestPalindromeSubseq("cbb")==2);
        Assert.assertTrue(longestPalindromeSubseq("cb")==1);
        Assert.assertTrue(longestPalindromeSubseq("c")==1);
    }
}
