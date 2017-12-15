package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/12/11.
 */
@DynamicProgramming
public class PalindromicSubstrings {

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

        Assert.assertTrue(countSubstrings("aaa")==6);
        Assert.assertTrue(countSubstrings("abccba")==9);
        Assert.assertTrue(countSubstrings("abc")==3);


        Assert.assertTrue(shortestPalindrome("aacecaaa").equals("aaacecaaa"));
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

    /**
     * Accepted
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            dp[i][i] = true;
            count++;
        }
        for (int i=1;i<s.length();i++){
            if (s.charAt(i-1) == s.charAt(i)){
                dp[i-1][i] = true;
                count++;
            }
        }
        for (int i=2;i<s.length();i++){
            for (int j=0;i+j<s.length();j++){
                if (s.charAt(j) == s.charAt(i+j) && dp[j+1][i+j-1]){
                    dp[j][j+i] = true;
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if (s==null || s.length()==0){
            return s;
        }
        int len=1;
        int start=0;
        for (int end=s.length()-1;end>0;end--){
            int i=start;
            int j = end;
            while (i<j){
                if (s.charAt(i) == s.charAt(j)){
                    i++;
                    j--;
                }else {
                    break;
                }
            }
            if (i>=j){
                len = end-start+1;
                break;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int j=s.length()-1;j>=len;j--){
            sb.append(s.charAt(j));
        }
        sb.append(s);
        return sb.toString();
    }




}
