package com.leishb.leetcode.stack;

import java.util.Stack;

/**
 * Created by me on 2019/7/8.
 */
public class _456_132_Pattern {


    /**
     *
     Input: [-1, 3, 2, 0]

     Output: True

     Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int third = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i=nums.length-1;i>=0;i--){
            if (nums[i] < third) return true;
            while (!stack.isEmpty() && stack.peek() < nums[i]){
                third = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
