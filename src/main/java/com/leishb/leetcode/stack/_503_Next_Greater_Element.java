package com.leishb.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by me on 2019/7/15.
 */
public class _503_Next_Greater_Element {

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            boolean found = false;
            for (int j=i+1;j<nums.length;j++){
                if (nums[j] > nums[i]){
                    found = true;
                    ans[i] = nums[j];
                    break;
                }
            }
            if (found){
                continue;
            }
            for (int j=0;j<i;j++){
                if (nums[j] > nums[i]){
                    found = true;
                    ans[i] = nums[j];
                    break;
                }
            }
            if(!found){
                ans[i] = -1;
            }
        }
        return  ans;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int[] nextGreaterElements2(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=nums.length-1;i>=0;i--){
            stack.push(i);
            ans[i] = -1;
        }

        for (int i=nums.length-1;i>=0;i--){
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]){
                stack.pop();
            }
            if (!stack.isEmpty()){
                ans[i] = nums[stack.peek()];
            }
            stack.push(i);
        }
        return ans;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int[] nextGreaterElements3(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[stack.peek()]< nums[i]){
                ans[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for (int i=0;i<nums.length;i++){
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                ans[stack.pop()] = nums[i];
            }
        }
        return ans;
    }
}
