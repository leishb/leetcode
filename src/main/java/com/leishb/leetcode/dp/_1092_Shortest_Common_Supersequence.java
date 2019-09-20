package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/9/16.
 */
public class _1092_Shortest_Common_Supersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        int[][] path = new int[str1.length()+1][str2.length()+1];
        int max = 0;
        int im = 0, jm =0;
        for (int i=1;i<=str1.length();i++){
            for (int j=1;j<=str2.length();j++){
                if (str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    path[i][j] = 1;
                }else {
                    if (dp[i-1][j] > dp[i][j-1]){
                        dp[i][j] = dp[i-1][j];
                        path[i][j] = 2;
                    }else {
                        dp[i][j] = dp[i][j-1];
                        path[i][j] = 3;
                    }
                }
                if (dp[i][j] > max){
                    im = i;
                    jm = j;
                    max = dp[i][j];
                }
            }
        }
        int[] arr1 = new int[max];
        int[] arr2 = new int[max];
        int k = max-1;
        while (k>=0){
            if (path[im][jm]==1){
                arr1[k] = --im;
                arr2[k] = --jm;
                k--;
            }else if (path[im][jm]==2){
                im = im-1;
            } else {
                jm = jm-1;
            }
        }
        if (max==0) return str1+str2;
        String s = str2.substring(0, arr2[0]) + str1.substring(0, arr1[0]+1);
        for (int i=1;i<arr1.length;i++){
            s = s + str2.substring(arr2[i-1]+1, arr2[i])+ str1.substring(arr1[i-1]+1, arr1[i]+1);
        }
        s = s + str2.substring(arr2[arr2.length-1]+1) + str1.substring(arr1[arr1.length-1]+1) ;
        return s;
    }


    @Test
    public void test(){
        shortestCommonSupersequence("cabd", "axb");
    }
}
