package com.leishb.leetcode.array;

import java.util.Stack;

/**
 * Created by me on 2019/10/24.
 */
public class _581_Shortest_Unsorted_Continuous_Subarray {

    public int findUnsortedSubarray(int[] nums) {
        int[] leftMax = new int[nums.length];
        leftMax[0] = nums[0];
        for (int i=1;i<nums.length;i++){
            leftMax[i] = Math.max(nums[i], leftMax[i-1]);
        }
        int[] rightMin = new int[nums.length];
        rightMin[nums.length-1] = nums[nums.length-1];
        for (int i=nums.length-2;i>=0;i--){
            rightMin[i] = Math.min(nums[i], rightMin[i+1]);
        }
        int start = -1;
        for (int i=0;i<nums.length;i++){
            if (leftMax[i] > rightMin[i]){
                start=i;
                break;
            }
        }
        int end = -1;
        for (int i=nums.length-1;i>=0;i--){
            if (leftMax[i] > rightMin[i]){
                end=i;
                break;
            }
        }
        return start==-1?0:(end-start+1);
    }


    public int findUnsortedSubarray2(int[] nums) {
        int left = nums.length;
        int right = -1;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                left = Math.min(stack.pop(), left);
            }
            stack.push(i);
        }
        stack.clear();
        for (int i=nums.length-1;i>=0;i--){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                right = Math.max(stack.pop(), right);
            }
            stack.push(i);
        }
        return left==nums.length?0:right-left+1;
    }


    public int findUnsortedSubarray3(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i=1;i<nums.length;i++){
            if (nums[i] < nums[i-1]) flag = true;
            if (flag) min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i=nums.length-2;i>=0;i--){
            if (nums[i+1] < nums[i]) flag = true;
            if (flag) max = Math.max(max, nums[i]);
        }
        int l=0, r=0;
        for (l=0;l<nums.length;l++){
            if (nums[l] > min) break;
        }
        for (r=nums.length-1;r>=0;r--){
            if (nums[r] < max) break;
        }
        return !flag?0:r-l+1;
    }
}
