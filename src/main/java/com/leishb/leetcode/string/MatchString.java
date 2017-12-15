package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/12/8.
 */
@DynamicProgramming
public class MatchString {


    @Test
    public void test(){
        Assert.assertTrue(isMatch("aab", "c*a*b"));
        Assert.assertTrue(isMatch("aaa", "ab*ac*a"));


        Assert.assertTrue(isMatch4("aab", "c*a*b"));
        Assert.assertTrue(isMatch4("aaa", "ab*ac*a"));


        Assert.assertTrue(isMatch2("ab", "a*"));
        Assert.assertTrue(isMatch2("ab", "?*"));
        Assert.assertTrue(isMatch2("aa", "a*"));
        Assert.assertTrue(isMatch2("aa", "*"));
        Assert.assertFalse(isMatch2("aaa", "ab*ac*a"));


        Assert.assertTrue(isMatch3("ab", "a*"));
        Assert.assertTrue(isMatch3("ab", "?*"));
        Assert.assertTrue(isMatch3("aa", "a*"));
        Assert.assertTrue(isMatch3("aa", "*"));
        Assert.assertFalse(isMatch3("aaa", "ab*ac*a"));


        Assert.assertFalse(isMatch5("abcd", "d*"));
        Assert.assertTrue(isMatch5("a", "a*"));
        Assert.assertTrue(isMatch5("aa", "a*"));
    }


    /**
     * https://leetcode.com/problems/regular-expression-matching/description/
     * Accepted
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()]=true;
        for (int i=p.length()-1;i>=0;i--){
            if (i+1 < p.length() && p.charAt(i+1) == '*'){
                dp[s.length()][i] = dp[s.length()][i+2];
            }
        }
        for (int i=s.length()-1;i>=0;i--){
            for (int j=p.length()-1;j>=0;j--){
                boolean firstMatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
                if (j+1 < p.length() && p.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || (firstMatch && dp[i+1][j]);
                }else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }


    /**
     * Accepted
     * https://leetcode.com/problems/wildcard-matching/description/
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()]=true;
        for (int i=p.length()-1;i>=0;i--){
            if (p.charAt(i) == '*'){
                dp[s.length()][i] = dp[s.length()][i+1];
            }
        }
        for (int i=s.length()-1;i>=0;i--){
            for (int j=p.length()-1;j>=0;j--){
                if (p.charAt(j) == '*'){
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];
                }else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    dp[i][j] = dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }


    /**
     * https://leetcode.com/problems/wildcard-matching/description/
     * Accepted
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch3(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i=1;i<=p.length();i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i=1;i<=s.length();i++){
            for (int j=1;j<=p.length();j++){
                if (p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }

            }
        }
        return dp[s.length()][p.length()];
    }


    /**
     * Accepted
     * https://leetcode.com/problems/regular-expression-matching/description/
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch4(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i=0;i<p.length();i++){
            if(p.charAt(i) == '*'){
                dp[0][i+1] = dp[0][i-1];
            }
        }
        for (int i=0;i<s.length();i++){
            for (int j=0;j<p.length();j++){
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
                    dp[i+1][j+1] =dp[i][j];
                }else if (p.charAt(j) == '*'){
                    if (s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1];
                    }else {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * https://leetcode.com/problems/regular-expression-matching/description/
     * Accepted
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch5(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i=1;i<=s.length();i++){
            for (int j=1;j<=p.length();j++){
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '*'){
                    if (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j-1] || dp[i-1][j] || dp[i][j-2];
                    }else {
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
