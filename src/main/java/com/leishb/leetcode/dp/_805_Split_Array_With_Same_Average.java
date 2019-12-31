package com.leishb.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/12/12.
 */
public class _805_Split_Array_With_Same_Average {


    /**
     * Accepted
     * @param A
     * @return
     */
    public boolean splitArraySameAverage(int[] A) {
        int total = 0;
        for (int num : A){
            total +=num;
        }
        return dfs(A, 0,  0, 0 , total, new HashMap<>());
    }



    private boolean dfs(int[] A, int index, int sum, int count, int total, Map<String, Boolean> memo){
        if (count==A.length) return false;
        if (count!=0 && sum * (A.length-count) == (total-sum) * count){
            return true;
        }
        String key = sum + "," + count;
        if (memo.containsKey(key)) return memo.get(key);
        for (int i= index;i<A.length;i++){
            if (dfs(A, i+1, sum+A[i], count+1, total, memo)){
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public boolean splitArraySameAverage2(int[] A) {
        int total = 0;
        for (int num : A){
            total +=num;
        }
        int[][] dp = new int[total+1][A.length];
        return dfs(A, 0,  0, 0 , total, dp);
    }


    private boolean dfs(int[] A, int index, int sum, int count, int total, int[][] memo){
        if (count==A.length) return false;
        if (count!=0 && sum * (A.length-count) == (total-sum) * count){
            return true;
        }
        if (memo[sum][count]!=0) return memo[sum][count]==1;
        for (int i= index;i<A.length;i++){
            if (dfs(A, i+1, sum+A[i], count+1, total, memo)){
                memo[sum][count]=1;
                return true;
            }
        }
        memo[sum][count]=-1;
        return false;
    }
}
