package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/8/21.
 */
public class _869_Reordered_Power_of_2 {


    /**
     * Accepted
     * @param N
     * @return
     */
    public boolean reorderedPowerOf2(int N) {
        int[] counts = new int[10];
        for (char c : (N+"").toCharArray()){
            counts[c-'0'] +=1;
        }
        int len = (N+"").length();
        return dfs(counts, len, "");
    }


    private boolean dfs(int[] counts , int len,  String s){
        if (s.length()==len){
            int num = Integer.parseInt(s);
            return (num&(num-1))==0;
        }
        for (int i=(s.length()==0?1:0);i<=9;i++){
            if (counts[i]>0){
                counts[i]-=1;
                if (dfs(counts, len, s+i)){
                    return true;
                }
                counts[i]+=1;
            }
        }
        return false;
    }



    public boolean reorderedPowerOf2_2(int N) {
        int[] counts = count(N);
        for (int i=0;i<31;i++){
            if (Arrays.equals(counts, count(1<<i))){
                return true;
            }
        }
        return false;
    }


    private int[] count(int N){
        int[] count = new int[10];
        for (char c : (N+"").toCharArray()){
            count[c-'0'] +=1;
        }
        return count;
    }


    @Test
    public void test(){
        Assert.assertTrue(reorderedPowerOf2_2(1));
        Assert.assertTrue(reorderedPowerOf2_2(16));
        Assert.assertFalse(reorderedPowerOf2_2(10));
        Assert.assertFalse(reorderedPowerOf2_2(12));
        Assert.assertTrue(reorderedPowerOf2_2(46));
        Assert.assertFalse(reorderedPowerOf2_2(461));
        Assert.assertTrue(reorderedPowerOf2_2(2014));
    }
}
