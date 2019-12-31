package com.leishb.leetcode.design;

import java.util.Stack;

/**
 * Created by me on 2019/12/6.
 */
public class _155_Min_Stack {


    int min = Integer.MAX_VALUE;
    Stack<Integer> stack;

    /** initialize your data structure here. */
    public _155_Min_Stack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x<=min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop()==min){
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
