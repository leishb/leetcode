package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/15.
 */
public class _1048_Longest_String_Chain {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (s1, s2)->s1.length()-s2.length());
        int[] dp = new int[words.length];
        int max = 1;
        for (int i=0;i<words.length;i++){
            dp[i] = 1;
            for (int j=i-1;j>=0;j--){
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }



    private boolean isPredecessor(String s1, String s2){
        if (s2.length()-s1.length()!=1)return false;
        for (int i=0;i<s2.length();i++){
            if (s1.equals(s2.substring(0, i)+s2.substring(i+1))){
                return true;
            }
        }
        return false;
    }


    public int longestStrChain2(String[] words) {
        Arrays.sort(words, (s1, s2)->s1.length()-s2.length());
        Map<String, Integer> map = new HashMap<>();
        int max = 1;
        for (int i=0;i<words.length;i++){
            int curMax = 1;
            for (int j=0;j<words[i].length();j++){
                String next = words[i].substring(0, j) + words[i].substring(j+1);
                curMax = Math.max(curMax, map.getOrDefault(next, 0)+1);
            }
            map.put(words[i], curMax);
            max = Math.max(max, curMax);
        }
        return max;
    }

    @Test
    public void test(){
        Assert.assertTrue(longestStrChain2(new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx",
                "zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"})==7);
    }
}
