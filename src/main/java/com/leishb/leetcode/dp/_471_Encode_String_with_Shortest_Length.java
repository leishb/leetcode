package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/12/24.
 */
public class _471_Encode_String_with_Shortest_Length {


    /**
     * Accepted
     * @param s
     * @return
     */
    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int len = 1;len <=s.length();len++){
            for (int i=0;i+len-1<s.length();i++){
                int j = i+len-1;
                dp[i][j] = s.substring(i, j+1);
                if (len < 4) continue;
                for (int k=i;k<j;k++){
                    if ((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
                String subStr = s.substring(i, j+1);
                for (int k=i;k<=j;k++){
                    String repeatStr = s.substring(i,k+1);
                    if (subStr.length()%repeatStr.length()==0 && subStr.replaceAll(repeatStr, "").length()==0
                            && (subStr.length()/repeatStr.length() + "[" + dp[i][k] +"]" ).length() < dp[i][j].length()){
                        dp[i][j] = subStr.length()/repeatStr.length() + "[" + dp[i][k] +"]";
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
    public String encode2(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int len = 1;len <=s.length();len++){
            for (int i=0;i+len-1<s.length();i++){
                int j = i+len-1;
                dp[i][j] = s.substring(i, j+1);
                if (len < 4) continue;
                for (int k=i;k<j;k++){
                    if ((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
                String cur = s.substring(i, j+1);
                int index = (cur+cur).indexOf(cur, 1);
                if (index!=-1 && index<cur.length()){
                    String ss = cur.length()/index + "[" + dp[i][i+index-1] + "]";
                    if (ss.length()<dp[i][j].length()){
                        dp[i][j] = ss;
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
