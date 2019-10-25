package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/24.
 */
public class _1000_Minimum_Cost_to_Merge_Stones {

    public int mergeStones(int[] stones, int K) {
        if (stones.length > 1 && stones.length<K) return -1;
        int n = stones.length;
        if (K!=2 && n%(K-1)!=1) return -1;
        return helper(stones, K, new HashMap<>());
    }


    private int helper(int[] stones, int K, Map<String, Integer> memo){
        if (stones.length==1) return 0;
        String key = Arrays.toString(stones);
        if (memo.containsKey(key)) return memo.get(key);
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int start = 0, end=0;
        for (int i=0;i<stones.length;i++){
            sum +=stones[i];
            if (i>=K){
                sum -= stones[i-K];
            }
            if (i>=K-1 && sum<min){
                min = sum;
                start = i-K+1;
                end = i;
            }
        }
        int ans = min;
        int[] temp = new int[stones.length-K+1];
        int j = 0;
        for (int i=0;i<start;i++){
            temp[j++] = stones[i];
        }
        temp[j++] = min;
        for (int i=end+1;i<stones.length;i++){
            temp[j++] = stones[i];
        }
        ans +=  helper(temp, K, memo);
        memo.put(key, ans);
        return ans;
    }


    @Test
    public void test(){
//        Assert.assertTrue(mergeStones(new int[]{3,2,4,1},2)==20);
//        Assert.assertTrue(mergeStones(new int[]{3,5,1,2,6},3)==25);
        Assert.assertTrue(mergeStones(new int[]{6,4,4,6},2)==25);
    }
}
