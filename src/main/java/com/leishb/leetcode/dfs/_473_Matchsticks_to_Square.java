package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/7/9.
 */
public class _473_Matchsticks_to_Square {


    boolean found = false;


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums) {
        if (nums.length==0)return false;
        long total = 0;
        for (int num : nums){
            total += num;
        }
        if (total%4!=0){
            return false;
        }
        Arrays.sort(nums);
        long target = total/4;
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0,  target, 0, total, used);
        return found;
    }


    private void dfs(int[] nums, int start,  long target, long sum,  long total, boolean[] used){
        if (total==0){
            found = true;
            return;
        }
        if (found){
            return;
        }
        for (int i=start;i<nums.length;i++){
            if (used[i]){
                continue;
            }
            if (sum+nums[i]>target){
                break;
            }
            used[i] = true;
            if (sum+nums[i]==target){
                dfs(nums,0, target, 0, total-target, used);
            }else if (sum+nums[i]<target){
                dfs(nums,i+1, target, sum+nums[i], total, used);
            }
            used[i] = false;
        }
    }


    @Test
    public void test(){
        Assert.assertFalse(makesquare(new int[]{3,3,3,3,4}));

        long start = System.currentTimeMillis();
        makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4});
        makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3});
        System.out.println("cost:" + (System.currentTimeMillis()-start));
    }
}
