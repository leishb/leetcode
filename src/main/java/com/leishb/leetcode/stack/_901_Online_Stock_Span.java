package com.leishb.leetcode.stack;

import java.util.Stack;

/**
 * Created by me on 2019/8/30.
 */
public class _901_Online_Stock_Span {


    Stack<int[]> stack ;

    public _901_Online_Stock_Span() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int ans = 0;
        while (!stack.isEmpty() && stack.peek()[0]<=price){
            int[] p = stack.pop();
            ans += p[1];
        }
        stack.push(new int[]{price, ans+1});
        return ans+1;
    }
}
