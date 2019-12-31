package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/12/5.
 */
public class _1246_Palindrome_Removal {


    /**
     * Accepted
     * @param arr
     * @return
     */
    public int minimumMoves(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        for (int i=0;i<arr.length;i++){
            dp[i][i] = 1;
        }
        for (int dif=1;dif<arr.length;dif++){
            for (int i=0;i+dif<arr.length;i++){
                int j = i+ dif;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k =i;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
                int left = i, right = j;
                while (left<=right){
                    if (arr[left]!=arr[right]) break;
                    left++;
                    right--;
                }
                if (left>right){
                    dp[i][j] = 1;
                }else {
                    dp[i][j] = Math.min(dp[i][j], dp[left][right]);
                }
            }
        }
        return dp[0][arr.length-1];
    }


    /**
     * Accepted
     * @param arr
     * @return
     */
    public int minimumMoves2(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i=0;i<n;i++){
            dp[i][i] = 1;
            if (i<n-1){
                if (arr[i]==arr[i+1]){
                    dp[i][i+1] = 1;
                }else {
                    dp[i][i+1] = 2;
                }
            }
        }

        for (int dif = 2;dif<n;dif++){
            for (int i=0;i+dif<n;i++){
                int j = i+dif;
                if (arr[i]==arr[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = dp[i+1][j-1]+2;
                }
                for (int k=i;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
    @Test
    public void test(){
        minimumMoves(new int[]{1,3,4,1,5});
    }
}
