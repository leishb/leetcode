package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/28.
 */
@DynamicProgramming
public class DecodeWays {


    @Test
    public void test(){
        long start = System.currentTimeMillis();
        Assert.assertTrue(new DecodeWays().numDecodings("12")==2);
        Assert.assertTrue(new DecodeWays().numDecodings("1")==1);
        Assert.assertTrue(new DecodeWays().numDecodings("01")==0);
        Assert.assertTrue(new DecodeWays().numDecodings("101")==1);
        Assert.assertTrue(new DecodeWays().numDecodings("301")==0);
        Assert.assertTrue(new DecodeWays().numDecodings("3001")==0);
        Assert.assertTrue(new DecodeWays().numDecodings("230")==0);
        System.out.println(new DecodeWays().numDecodings("9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253"));
        System.out.println("cost : " + (System.currentTimeMillis()-start));

        Assert.assertTrue(new DecodeWays().numDecodings2("12")==2);
        Assert.assertTrue(new DecodeWays().numDecodings2("1")==1);
        Assert.assertTrue(new DecodeWays().numDecodings2("01")==0);
        Assert.assertTrue(new DecodeWays().numDecodings2("101")==1);
        Assert.assertTrue(new DecodeWays().numDecodings2("230")==0);
        Assert.assertTrue(new DecodeWays().numDecodings2("301")==0);
        Assert.assertTrue(new DecodeWays().numDecodings2("2001")==0);
        System.out.println(new DecodeWays().numDecodings2("9371597631128776948387197132267188677349946742344217846154932859125134924241649584251978418763151253"));
    }


    public int count = 0;


    /**
     * TLE
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.length()==0 || s.startsWith("0")){
            return 0;
        }
        backtracking(s, 0);
        return count;
    }


    private void backtracking(String s, int start){
        if (start==s.length()){
            count++;
            return;
        }
        for (int i=1;i<=2;i++){
            if (start+i<=s.length()){
                String str = s.substring(start, start+i);
                if (!str.startsWith("0") && Integer.parseInt(str) > 0 && Integer.parseInt(str) <=26){
                    backtracking(s, start+i);
                }
            }
        }
    }

    /**
     * Accepted
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        if (s.length()==0 || s.startsWith("0")){
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=1;
        for (int i=2;i<s.length()+1;i++){
            char c = s.charAt(i-1);
            char pre = s.charAt(i-2);
            if (c=='0'){
                if (pre-'0' >2 || pre == '0'){
                    return 0;
                }else {
                    dp[i] = dp[i - 2];
                }
            }else {
                if (pre == '0' || (pre-'0')*10 + (c-'0') > 26){
                    dp[i]=dp[i-1];
                }else {
                    dp[i]=dp[i-1] + dp[i-2];
                }
            }
        }
        return dp[s.length()];
    }

}
