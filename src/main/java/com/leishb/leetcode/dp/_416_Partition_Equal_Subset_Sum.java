package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/9/27.
 */
public class _416_Partition_Equal_Subset_Sum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i=0;i<nums.length ;i++ ) {
            sum += nums[i];
        }
        if(sum%2!=0) return false;
        boolean[][] dp = new boolean[n+1][sum/2+1];
        dp[0][0] = true;
        for (int i=1;i<=n;i++){
            for (int j=nums[i-1];j<=sum/2;j++){
                for (int k=i-1;k>=0;k--){
                    if (dp[k][j-nums[i-1]]){
                        dp[i][j] = true;
                        break;
                    }
                }
            }
            if (dp[i][sum/2]) return true;
        }
        return false;
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, 0, sum/2);
    }



    private boolean dfs(int[] nums , int pos, int target){
        if (target==0){
            return true;
        }
        for (int i=pos;i<nums.length;i++){
            if (nums[i] > target) break;
            if (i!=pos && nums[i]==nums[i-1]) continue;
            if (dfs(nums, i+1, target-nums[i])){
                return true;
            }
        }
        return false;
    }


    public boolean canPartition3(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        sum /=2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i=0;i<=n;i++){
            dp[i][0] = true;
        }
        for (int i=1;i<=n;i++){
            for (int j=1;j<=sum;j++){
                dp[i][j] = dp[i-1][j];
                if (j>=nums[i-1] && dp[i-1][j-nums[i-1]]){
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][sum];
    }


    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, k,0,  0, sum/k, new boolean[nums.length]);
    }


    private boolean dfs(int[] nums, int k, int pos,  int sum, int target, boolean[] visited){
        if (k==0){
            return true;
        }
        if (sum==target){
            return dfs(nums, k-1, 0,  0, target, visited);
        }
        for (int i=pos;i<nums.length;i++){
            if (visited[i]) continue;
            if (sum+nums[i] > target) break;
            if (i!=0 && nums[i]==nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            if (dfs(nums, k, i+1 , sum+nums[i], target, visited)){
                return true;
            }
            visited[i] = false;
        }
        return false;
    }


    @Test
    public void test(){
        Assert.assertFalse(canPartition3(new int[]{1,2,5}));
        Assert.assertTrue(canPartition3(new int[]{1,1,1,1}));
        Assert.assertTrue(canPartition3(new int[]{1,5,11,5}));
        Assert.assertTrue(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
