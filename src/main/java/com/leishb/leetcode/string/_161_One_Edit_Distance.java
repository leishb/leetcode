package com.leishb.leetcode.string;

/**
 * Created by me on 2019/9/25.
 */
public class _161_One_Edit_Distance {

    public boolean isOneEditDistance(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i=0;i<=s.length();i++){
            for (int j=0;j<=t.length();j++){
                if (i==0){
                    dp[i][j] = j;
                }else if (j==0){
                    dp[i][j] = i;
                }else if (s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1])+1;
                }
            }
        }
        return dp[s.length()][t.length()]==1;
    }

    public boolean isOneEditDistance2(String s, String t) {
        if (Math.abs(s.length()-t.length())>1) return false;
        int i=0;
        int j=0;
        int dis = 0;
        while (i<s.length() && j<t.length()){
            if (s.charAt(i)==t.charAt(j)){
                i++;
                j++;
                continue;
            }
            if (dis>0) return false;
            if (s.length() > t.length()){
                i++;
            }else if (t.length() > s.length()){
                j++;
            }else {
                i++;
                j++;
            }
            dis +=1;
        }
        return dis==1 || (dis==0 && Math.abs(s.length()-t.length())==1);
    }


    public boolean isOneEditDistance3(String s, String t) {
        if (Math.abs(s.length()-t.length())>1) return false;
        if (s.length() > t.length()){
            return isOneEditDistance3(t, s);
        }
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) != t.charAt(i)){
                if (s.length()==t.length()){
                    return s.substring(i+1).equals(t.substring(i+1));
                }else {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        return s.length()+1==t.length();
    }
}
