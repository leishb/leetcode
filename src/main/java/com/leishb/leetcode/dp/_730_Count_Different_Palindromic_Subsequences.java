package com.leishb.leetcode.dp;

import java.util.Arrays;

/**
 * Created by me on 2019/12/12.
 */
public class _730_Count_Different_Palindromic_Subsequences {


    /**
     * Accepted
     * @param S
     * @return
     */
    public int countPalindromicSubsequences(String S) {
        int M = 1_000_000_007;
        int n = S.length();
        int[] rec = new int[4], leftNext = new int[n],rightNext = new int[n] ;
        Arrays.fill(rec, -1);
        Arrays.fill(leftNext, -1);
        for (int i=0;i<n;i++){
            leftNext[i] = rec[S.charAt(i)-'a'];
            rec[S.charAt(i)-'a'] = i;
        }
        Arrays.fill(rec, n);
        for (int i=n-1;i>=0;i--){
            rightNext[i] = rec[S.charAt(i)-'a'];
            rec[S.charAt(i)-'a'] = i;
        }
        int[][] dp = new int[n][n];
        for (int i=0;i<n;i++) dp[i][i] = 1;
        for (int len=2;len<=n;len++){
            for (int i=0;i+len-1<n;i++){
                int j = i+len-1;
                if (S.charAt(i)!=S.charAt(j)){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                }else{
                    int irn = rightNext[i], jln = leftNext[j];
                    if (irn < jln){
                        dp[i][j] = dp[i+1][j-1] * 2 - dp[irn+1][jln-1];
                    }else if (irn > jln){
                        dp[i][j] = dp[i+1][j-1] * 2 + 2;
                    }else {
                        dp[i][j] = dp[i+1][j-1] * 2 + 1;
                    }
                }
                dp[i][j] = dp[i][j]<0?dp[i][j]+M:dp[i][j]%M;
            }
        }
        return dp[0][n-1];
    }
}
