package com.leishb.leetcode.linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/4/10.
 */
public class MinStack {

    class Node {
        int val;
        int min;

        Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }

    Stack<Node> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(new Node(x,x));
        }else {
            stack.push(new Node(x, Math.min(x, stack.peek().min)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }


    @Test
    public void test(){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }
}
