package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/12/9.
 */
public class _1063_Number_of_Valid_Subarrays {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int validSubarrays(int[] nums) {
        int[] smaller = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while(!stack.isEmpty() && nums[i] < nums[stack.peek()]){
                smaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            smaller[stack.pop()] = nums.length;
        }
        int ans = 0;
        for (int i=0;i<nums.length;i++){
            ans+= smaller[i]-i;
        }
        return ans;
    }



    public int validSubarrays2(int[] nums) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while(!stack.isEmpty() && nums[i] < nums[stack.peek()]){
                ans += i-stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            ans += nums.length-stack.pop();
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertEquals(11, validSubarrays2(new int[]{1,4,2,5,3}));
    }
}
