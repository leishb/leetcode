package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/10.
 */
public class _486_Predict_the_Winner {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        int total = 0;
        for (int num : nums){
            total += num;
        }
        Map<String, Integer> memo = new HashMap<>();
        int score = dfs(nums, 0, nums.length-1, memo);
        return total-score<score;
    }


    private int dfs(int[] nums, int start, int end, Map<String, Integer> memo){
        if (start>end){
            return 0;
        }
        if (memo.containsKey(start+"-"+end)){
            return memo.get(start+"-"+end);
        }
        int s1 = nums[start] + Math.min(dfs(nums, start+2, end, memo) , dfs(nums, start+1, end-1, memo));
        int s2 = nums[end] + Math.min(dfs(nums, start+1, end-1, memo) , dfs(nums, start, end-2, memo));
        int score = Math.max(s1, s2);
        memo.put(start+"-"+end, score);
        return score;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean PredictTheWinner2(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return winner(nums, 0, nums.length-1, memo)>=0;
    }


    private int winner(int[] nums, int start, int end, Integer[][] memo){
        if (start>end){
            return 0;
        }
        if (memo[start][end]!=null){
            return memo[start][end];
        }
        int s1 = nums[start] - winner(nums, start+1, end, memo);
        int s2 = nums[end] - winner(nums, start, end-1, memo);
        int score = Math.max(s1, s2);
        memo[start][end] = score;
        return score;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean PredictTheWinner3(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i=nums.length-1;i>=0;i--){
            for (int j=i;j<nums.length;j++){
                if (i==j){
                    dp[i][j] = nums[i];
                }else {
                    dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
                }
            }
        }
        return dp[0][nums.length-1]>=0;
    }
    @Test
    public void test(){
        Assert.assertFalse(PredictTheWinner(new int[]{1,5,2}));
        Assert.assertTrue(PredictTheWinner(new int[]{1,5,233,7}));
        Assert.assertTrue(PredictTheWinner3(new int[]{1,5,233,7}));
    }
}
