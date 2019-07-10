package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/10.
 */
public class _494_Target_Sum {

    int count = 0;

    /**
     * Accepted
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, 0, S, 0);
        return count;
    }


    private void dfs(int[] nums, int index,  int S, int sum){
        if (sum==S && index==nums.length){
            count++;
            return;
        }
        if (index==nums.length){
            return;
        }
        dfs(nums, index+1, S, sum+nums[index]);
        dfs(nums, index+1, S, sum-nums[index]);
    }


    /**
     * Accepted
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if (Math.abs(S)>sum || nums.length==0){
            return 0;
        }
        int[][] dp = new int[nums.length+1][sum+1];
        dp[1][nums[0]] = nums[0]==0?2:1;
        for (int i=2;i<=nums.length;i++){
            for (int j=0;j<sum+1;j++){
                if (j+nums[i-1] >= dp[0].length){
                    dp[i][j] = dp[i-1][Math.abs(j - nums[i-1])];
                }else {
                    dp[i][j] = dp[i-1][Math.abs(j - nums[i-1])] + dp[i-1][j + nums[i-1]];
                }
            }
        }
        return dp[nums.length][Math.abs(S)];
    }


    /**
     * Accepted
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays3(int[] nums, int S) {
        return dfs(nums, 0, S, 0, new HashMap<>());
    }


    private int dfs(int[] nums, int index, int S, int sum, Map<String, Integer> memo){
        if (memo.containsKey(serialize(index, sum))){
            return memo.get(serialize(index, sum));
        }
        if (index==nums.length){
            if (S==sum){
                return 1;
            }
            return 0;
        }
        int add = dfs(nums, index+1, S, sum+nums[index], memo);
        int sub = dfs(nums, index+1, S, sum-nums[index], memo);
        memo.put(serialize(index, sum), add+sub);
        return add+sub;
    }

    private String serialize(int curIndex, int targetSum) {
        return curIndex + "," + targetSum;
    }


    @Test
    public void test(){
        Assert.assertTrue(findTargetSumWays2(new int[]{1, 999}, 998)==1);
        Assert.assertTrue(findTargetSumWays3(new int[]{1, 999}, 998)==1);
    }
}
