package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/10/28.
 */
public class _132_Palindrome_Partitioning_II {


    /**
     * Accepted
     * @param s
     * @return
     */
    public int minCut(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int dif=0;dif<s.length();dif++){
            for (int i=0;i+dif<s.length();i++){
                int j = i+dif;
                if (i==j){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = s.length()+1;
                    if (s.charAt(i)==s.charAt(j) && dp[i+1][j-1]==0){
                        dp[i][j] = 0;
                    }else {
                        for (int k=i;k<=j-1;k++){
                            dp[i][j] = Math.min(dp[i][k]+dp[k+1][j]+1, dp[i][j]);
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    /**
     * Accepted
     * @param s
     * @return
     */
    public int minCut3(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int[] cut = new int[s.length()];
        for (int i = 0;i<s.length();i++){
            cut[i] = s.length();
            for (int len=0;len<=i;len++){
                int j = i-len;
                if (s.charAt(j)==s.charAt(i) && (i==j || j+1==i || dp[j+1][i-1])){
                    dp[j][i] = true;
                    if (j==0){
                        cut[i] = 0;
                    }else {
                        cut[i] = Math.min(cut[i], cut[j-1]+1);
                    }
                }
            }
        }
        return cut[s.length()-1];
    }
}
