package com.leishb.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2019/9/10.
 */
public class _1019_Next_Greater_Node_In_Linked_List {

    public int[] nextLargerNodes(ListNode head) {
        if (head==null) return new int[0];
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur!=null){
            list.add(cur.val);
            cur = cur.next;
        }
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[list.size()];
        stack.push(list.get(list.size()-1));
        ans[ans.length-1] = 0;
        for (int i=list.size()-2;i>=0;i--){
            while (!stack.isEmpty() && stack.peek()<=list.get(i)){
                stack.pop();
            }
            if (!stack.isEmpty()) ans[i] = stack.peek();
            stack.push(list.get(i));
        }
        return ans;
    }
}
