package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/20.
 */
public class _1140_Stone_Game_II {

    public int stoneGameII(int[] piles) {
        int[] sums = new int[piles.length+1];
        for (int i=1;i<piles.length+1;i++){
            sums[i] = sums[i-1] + piles[i-1];
        }
        Map<String, Integer> memo = new HashMap<>();
        int max = dfs(piles, 0, 1, sums, memo);
        return max;
    }


    private int dfs(int[] piles, int start, int M, int[] sums, Map<String, Integer> memo){
        if (start>=piles.length) return 0;
        String key = start+"-"+M;
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        int sum = sums[sums.length-1]-sums[start];
        int max = 0;
        for (int i=1;i<=2*M;i++){
            int next = dfs(piles, start+i, Math.max(i, M), sums, memo);
            max = Math.max(max, sum-next);
        }
        memo.put(key, max);
        return max;
    }


    @Test
    public void test(){
        Assert.assertTrue(stoneGameII(new int[]{2,7,9,4,4})==10);
    }
}
