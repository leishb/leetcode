package com.leishb.leetcode.linkedlist;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/10/14.
 */
public class _445_Add_Two_Numbers_II {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        int carry = 0;
        ListNode cur = dummy;
        while (r1!=null || r2!=null){
            int sum = (r1==null?0:r1.val) + (r2==null?0:r2.val) +carry;
            carry = sum/10;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            r1 = r1!=null?r1.next:null;
            r2 = r2==null?null:r2.next;
        }
        if (carry > 0){
            cur.next = new ListNode(carry);
        }
        ListNode newHead = reverse(dummy.next);
        return newHead;
    }


    private ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode prev = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        for (ListNode cur = l1; cur!=null;cur = cur.next){
            s1.push(cur.val);
        }
        Stack<Integer> s2 = new Stack<>();
        for (ListNode cur = l2; cur!=null;cur = cur.next){
            s2.push(cur.val);
        }
        ListNode dummy = new ListNode(0);
        ListNode next = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry!=0){
            int num1 = s1.isEmpty()?0:s1.pop();
            int num2 = s2.isEmpty()?0:s2.pop();
            int sum = num1+num2+carry;
            carry = sum/10;
            ListNode node= new ListNode(sum%10);
            node.next = next;
            dummy.next = node;
            next = node;
        }
        return dummy.next;
    }

    @Test
    public void test(){
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        addTwoNumbers(l1, l2);
    }
}
