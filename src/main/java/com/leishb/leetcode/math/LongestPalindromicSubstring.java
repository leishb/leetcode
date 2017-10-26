package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/19.
 */
@DynamicProgramming
public class LongestPalindromicSubstring {

    @Test
    public void test(){
        Assert.assertTrue(longthOfPalindromicSubstring("babad")==3);
        Assert.assertTrue(longthOfPalindromicSubstring("babab")==5);
        Assert.assertTrue(longthOfPalindromicSubstring("aaaaa")==5);
        Assert.assertTrue(longthOfPalindromicSubstring("abcde")==1);
        Assert.assertTrue(longthOfPalindromicSubstring("abbcdefgh")==2);


        Assert.assertTrue(longestPalindromicSubstring("babad").equals("aba"));
        Assert.assertTrue(longestPalindromicSubstring("babab").equals("babab"));
        Assert.assertTrue(longestPalindromicSubstring("aaaaa").equals("aaaaa"));
        Assert.assertTrue(longestPalindromicSubstring("abcde").equals("a"));
        Assert.assertTrue(longestPalindromicSubstring("abbcdefgh").equals("bb"));
    }


    public int longthOfPalindromicSubstring(String s){
        int len = s.length();
        char[] cs = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        for (int i=0;i<len;i++){
            dp[i][i] = true;
        }
        for (int i=0;i<len-1;i++){
            if (cs[i]==cs[i+1]){
                dp[i][i+1] = true;
                maxLen = 2;
            }
        }
        for (int i=2;i<len;i++){
            for (int j=0;i+j<len;j++){
                if (cs[j]==cs[i+j] && dp[j+1][i+j-1] == true){
                    dp[j][i+j] = true;
                    maxLen = i+1;
                }
            }
        }
        return maxLen;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public String longestPalindromicSubstring(String s){
        int len = s.length();
        char[] cs = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        int startIndex = 0;
        int endIndex = 0;
        for (int i=0;i<len;i++){
            dp[i][i] = true;
        }
        for (int i=0;i<len-1;i++){
            if (cs[i]==cs[i+1]){
                dp[i][i+1] = true;
                startIndex = i;
                endIndex = i+1;
            }
        }
        //先循环间隔，再循环数组下标
        for (int i=2;i<len;i++){ // i is gap
            for (int j=0;i+j<len;j++){ // j move
                if (cs[j]==cs[i+j] && dp[j+1][i+j-1] == true){
                    dp[j][i+j] = true;
                    startIndex = j;
                    endIndex = i+j;
                }
            }
        }
        return s.substring(startIndex, endIndex+1);
    }
}
