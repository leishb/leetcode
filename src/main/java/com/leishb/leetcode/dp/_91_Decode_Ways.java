package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/17.
 */
public class _91_Decode_Ways {

    public int numDecodings(String s) {
        if (s.charAt(0)=='0') return 0;
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i=1;i<s.length();i++){
            int num = Integer.parseInt(s.substring(i-1, i+1));
            if (num==0 || (s.charAt(i)=='0' && num > 26)){
                return 0;
            }
            if (s.charAt(i-1)=='0'  || num > 26 ){
                dp[i] = dp[i-1];
            }else if (s.charAt(i)=='0'){
                dp[i] = i>1?dp[i-2]:1;
            } else {
                dp[i] = dp[i-1] + (i>1?dp[i-2]:1);
            }
        }
        return dp[s.length()-1];
    }

    public int numDecodings2(String s) {
        if (s==null||s.length()==0) return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)=='0'?0:1;
        for (int i=2;i<=n;i++){
            int last1 = Integer.parseInt(s.substring(i-1, i));
            int last2 = Integer.parseInt(s.substring(i-2, i));
            if (last1>=1 && last1<=9){
                dp[i] += dp[i-1];
            }
            if (last2>=10 && last2<=26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }



    public int numDecodings3(String s) {
        if (s.length()==0 || s.startsWith("0")){
            return 0;
        }
        int[] dp = new int[s.length()+1];
        return dfs(s, 0, dp);
    }


    private int dfs(String s, int start, int[] memo){
        if (start==s.length()){
            return 1;
        }
        if (memo[start]>0) return memo[start];
        int count = 0;
        for (int i=1;i<=2;i++){
            if (start+i<=s.length()){
                String str = s.substring(start, start+i);
                if (!str.startsWith("0") && Integer.parseInt(str) > 0 && Integer.parseInt(str) <=26){
                    count += dfs(s, start+i, memo);
                }
            }
        }
        memo[start] = count;
        return count;
    }

    @Test
    public void test(){
        Assert.assertTrue(numDecodings2("11")==2);
    }
}
