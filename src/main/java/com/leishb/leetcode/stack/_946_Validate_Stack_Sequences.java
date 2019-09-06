package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/9/4.
 */
public class _946_Validate_Stack_Sequences {



    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int num : pushed){
            stack.push(num);
            while (!stack.isEmpty() && j<N && stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }
        return j==N;
    }


    @Test
    public void test(){
        Assert.assertTrue(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
        Assert.assertTrue(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{5,4,3,2,1}));
        Assert.assertFalse(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
        Assert.assertFalse(validateStackSequences(new int[]{4,0,1,2,3}, new int[]{4,2,3,0,1}));
        Assert.assertFalse(validateStackSequences(new int[]{1,2,3,4,5,6}, new int[]{3,2,1, 6, 4,5}));
    }
}
