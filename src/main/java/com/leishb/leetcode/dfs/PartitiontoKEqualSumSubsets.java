package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/6/6.
 */
public class PartitiontoKEqualSumSubsets {


    boolean result = false;


    /**
     *  [4, 3, 2, 3, 5, 2, 1]
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        if (total%k!=0){
            return false;
        }
        Arrays.sort(nums);
        backtracking(0, 0, total/k, total, nums, new boolean[nums.length]);
        return result;
    }

    public void backtracking(int k, int sum , int target, int total,  int[] nums, boolean[] used){
        if (total==0){
            result = true;
            return;
        }
        if (result){
            return;
        }
        for (int i=k;i<nums.length;i++){
            if (used[i]){
                continue;
            }
            if (i!=0 && nums[i]==nums[i-1] && !used[i-1]){
                continue;
            }
            used[i] = true;
            if (sum+nums[i]==target){
                backtracking(0 , 0, target, total-target, nums, used);
            }else if (sum+nums[i] < target){
                backtracking(i+1 , sum+nums[i], target, total, nums, used);
            }
            used[i]=false;
        }
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int i=0;i<nums.length;i++){
            total += nums[i];
        }
        if (total%2!=0){
            return false;
        }
        Arrays.sort(nums);
        return helper(0, nums, total/2, new boolean[nums.length]);
    }


    public boolean helper(int start, int[] nums, int sum, boolean[] used){
        if (sum==0){
            return true;
        }
        for (int i=start;i<nums.length;i++){
            if (used[i]){
                continue;
            }
            if (i!=0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }
            if (sum-nums[i] >=0){
                used[i] = true;
                if (helper(i+1, nums, sum-nums[i], used)){
                    return true;
                }
                used[i] = false;
            }else {
                break;
            }
        }
        return false;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int total = 0;
        for (int i=0;i<nums.length;i++){
            total += nums[i];
        }
        if (total%2!=0){
            return false;
        }
        boolean[] dp = new boolean[total/2+1];
        for (int i=0;i<nums.length;i++){
            for (int j=dp.length-1;j>=nums[i];j--){
                if (nums[i]==j || dp[j-nums[i]]){
                    dp[j] = true;
                }
            }
        }
        return dp[dp.length-1];
    }


    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int total = 0;
        for (int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        if (total%k!=0){
            return false;
        }
        Arrays.sort(nums);
        return helper(0, 0, total/k, total, nums, new boolean[nums.length]);
    }



    private boolean helper(int k, int sum , int target, int total,  int[] nums, boolean[] used){
        if (total==0){
            return true;
        }
        if (sum==target){
            return helper(0, 0, target, total-target, nums, used);
        }
        for (int i=k;i<nums.length;i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            if (helper(i+1, sum+nums[i], target, total, nums, used)){
                return true;
            }
            used[i] = false;
        }
        return false;
    }

    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println(canPartitionKSubsets(new int[]{10,10,10,7,7,7,7,7,7,6,6,6}, 3));

        System.out.println("cost : "+ (System.currentTimeMillis()-start));
        System.out.println(canPartitionKSubsets2(new int[]{3522,181,521,515,304,123,2512,312,922,407,146,1932,4037,2646,3871,269}, 5));

        System.out.println("cost : "+ (System.currentTimeMillis()-start));


        Assert.assertTrue(canPartition(new int[]{28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79}));
        Assert.assertTrue(canPartition2(new int[]{28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79}));

        System.out.println("cost : "+ (System.currentTimeMillis()-start));

        canPartition2(new int[]{1,2,5});
    }
}
