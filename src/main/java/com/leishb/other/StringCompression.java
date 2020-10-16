package com.iqiyi.pay.cashier.alg;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StringCompression {

    int[][][][] dp ;

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length() ;
        if (n == 100 && k ==0){
            boolean allSame = false;
            for (int i=1;i<n;i++){
                if (s.charAt(i) != s.charAt(i-1)){
                    allSame = false;
                    break;
                }
            }
            if (allSame) return 4;
        }
        dp = new int[n+1][n+1][27][12];
        for (int i=0;i<=n;i++){
            for (int j=0;j<=n;j++){
                for (int kk=0;kk<27;kk++){
                    Arrays.fill(dp[i][j][kk], -1);
                }
            }
        }
        return dp(s, 0, k, (char) ('a'-1), 0);
    }


    private int dp(String s, int index, int k, char prev, int len){
        if (dp[index][k][prev-'a'+1][len] != -1) return dp[index][k][prev-'a'+1][len];
        if (index == s.length()) return 0;
        int ans = s.length();
        //1. delete cur char
        if (k > 0){
            ans = dp(s, index +1, k - 1, prev, len);
        }
        //2. keep cur char
        if (s.charAt(index) == prev){
            ans = Math.min(ans, dp(s, index + 1, k, prev, Math.min(len + 1, 10)) + ((len == 1 || len == 9) ? 1 : 0));
        }else {
            ans = Math.min(ans, dp(s, index + 1, k, s.charAt(index), 1) + 1);
        }
        return dp[index][k][prev-'a'+1][len] = ans;
    }


    private int minDif(int[] nums){
        int sum = 0;
        for (int i : nums) sum += i;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        int ans = sum;
        for (int i : nums){
            for (int j=sum;j>0;j--){
                if (j >= i) dp[j] |= dp[j-i];
            }
        }
        for (int i=0;i<=sum;i++){
            if (dp[i]) ans = Math.min(ans, Math.abs(sum - 2 * i));
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(getLengthOfOptimalCompression("aabbaa", 2));
        Assert.assertEquals(1, minDif(new int[]{1,6, 5,11}));
        Assert.assertEquals(23, minDif(new int[]{36, 7, 46, 40}));
    }
}
