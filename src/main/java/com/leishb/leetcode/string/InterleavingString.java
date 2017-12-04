package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/23.
 */
@DynamicProgramming
public class InterleavingString {


    @Test
    public void test(){
        Assert.assertTrue(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assert.assertTrue(!isInterleave("aabcc", "dbbca", "aadbbbaccc"));

        Assert.assertTrue(numDistinct("ABCDEABCDE", "ACE")==4);
        Assert.assertTrue(numDistinct("rabbbit", "rabbit")==3);
        Assert.assertTrue(numDistinct("abcdece", "ace")==3);
        Assert.assertTrue(numDistinct("aa", "a")==2);
        Assert.assertTrue(numDistinct("aab", "ab")==2);
        Assert.assertTrue(numDistinct("aabb", "ab")==4);
    }

    /**
     * Accepted
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if (l1 + l2 != l3) {
            return false;
        }
        boolean[][] dp = new boolean[l1+1][l2+1];
        dp[0][0]=true;
        for (int i=1;i<=l1;i++){
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        for (int i=1;i<=l2;i++){
            dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
        }
        for (int i=1;i<=l1;i++){
            for (int j=1;j<=l2;j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[l1][l2];
    }


    /**
     * Accepted
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i=0;i<s.length();i++){
            dp[i][0]=1;
        }
        for (int i=0;i<s.length();i++){
            for (int j=0;j<t.length();j++){
                if (s.charAt(i) == t.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + dp[i][j+1];
                }else {
                    dp[i+1][j+1] = dp[i][j+1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }


    /**
     * Accepted
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<=word2.length();i++){
            dp[0][i] = i;
        }
        for(int i=0;i<=word1.length();i++){
            dp[i][0] = i;
        }

        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

}
