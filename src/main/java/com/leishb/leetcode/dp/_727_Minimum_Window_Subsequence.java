package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/11/5.
 */
public class _727_Minimum_Window_Subsequence {


    /**
     * Accepted
     * @param S
     * @param T
     * @return
     */
    public String minWindow(String S, String T) {
        int[][] dp = new int[S.length()+1][T.length()+1];
        for (int i=0;i<=S.length();i++){
            dp[i][0] = i+1;
        }
        for (int i=1;i<=S.length();i++){
            for (int j=1;j<=T.length();j++){
                if (S.charAt(i-1) == T.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        int start = 0, len = S.length()+1;
        for (int i=0;i<=S.length();i++){
            if (dp[i][T.length()]!=0){
                if (i-dp[i][T.length()]+1 < len){
                    start = dp[i][T.length()]-1;
                    len = i - dp[i][T.length()] + 1;
                }
            }
        }
        return len==S.length()+1 ? "" : S.substring(start, start+len);
    }
}
