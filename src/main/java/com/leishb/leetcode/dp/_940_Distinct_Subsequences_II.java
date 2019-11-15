package com.leishb.leetcode.dp;

import java.util.Arrays;

/**
 * Created by me on 2019/11/14.
 */
public class _940_Distinct_Subsequences_II {


    /**
     *
     *
     * @param S
     * @return
     */
    public int distinctSubseqII(String S) {
        int M = 1_000_000_007;
        if (S.length()<=1) return S.length();
        int[] dp = new int[S.length()+1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i=1;i<=S.length();i++){
            dp[i] = (2 * dp[i-1])%M;
            if (last[S.charAt(i-1)-'a']!=-1){
                dp[i] -= dp[last[S.charAt(i-1)-'a']];
            }
            dp[i] %=M;
            last[S.charAt(i-1)-'a'] = i-1;
        }
        dp[S.length()]--;
        if (dp[S.length()]<0) dp[S.length()]+=M;
        return dp[S.length()];
    }
}
