package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/9.
 */
public class _474_Ones_and_Zeroes {

    /**
     * Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
     * Accepted
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1];
        Map<String, int[]> map = new HashMap<>();
        for (String s : strs){
            map.put(s, count(s));
        }
        for (int i=1;i<=strs.length;i++){
            for (int j=0;j<=m;j++){
                for (int k=0;k<=n;k++){
                    int[] counts = map.get(strs[i-1]);
                    if (j>=counts[0] && k>=counts[1]){
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-counts[0]][k-counts[1]]+1);
                    }else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    /**
     *
     * 0/1背包问题降维
     * Accepted
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (int i=1;i<=strs.length;i++){
            int[] counts = count(strs[i-1]);
            for (int j=m;j>=0;j--){
                for (int k=n;k>=0;k--){
                    if (j>=counts[0] && k>=counts[1]){
                        dp[j][k] = Math.max(dp[j][k], dp[j-counts[0]][k-counts[1]]+1);
                    }
                }
            }
        }
        return dp[m][n];
    }




    private int[] count(String s){
        int one = 0;
        int zero = 0;
        for (char c : s.toCharArray()){
            if (c=='0'){
                zero++;
            }else {
                one++;
            }
        }
        return new int[]{zero, one};
    }

    @Test
    public void test(){
        Assert.assertTrue(findMaxForm2(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3)==4);
    }
}
