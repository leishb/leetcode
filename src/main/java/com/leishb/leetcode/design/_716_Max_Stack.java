package com.leishb.leetcode.design;

import java.util.Stack;

/**
 * Created by me on 2019/12/6.
 */
public class _716_Max_Stack {

    int max = Integer.MIN_VALUE;
    Stack<Integer> stack ;

    /** initialize your data structure here. */
    public _716_Max_Stack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (x>=max){
            stack.push(max);
            max = x;
        }
        stack.push(x);
    }

    public int pop() {
        int p = stack.pop();
        if (max==p) max = stack.pop();
        return p;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        Stack<Integer> s = new Stack<>();
        while (stack.peek()!=max){
            s.push(stack.pop());
        }
        int m = this.pop();
        while (!s.isEmpty()){
            this.push(s.pop());
        }
        return m;
    }
}
