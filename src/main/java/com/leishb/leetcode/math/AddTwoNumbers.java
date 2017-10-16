package com.leishb.leetcode.math;

import org.junit.Test;

/**
 * Created by me on 2017/10/16.
 */
public class AddTwoNumbers {


    @Test
    public void test(){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);

    }


    class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    /**
     * Accepted
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;

        int sum = l1.val + l2.val;
        ListNode ln = new ListNode(0);

        ListNode tail = ln;

        carry = sum/10;
        ln.val = sum%10;

        l1 = l1.next;
        l2 = l2.next;

        while(l1!=null && l2 != null){

            sum = l1.val + l2.val + carry;

            ListNode node = new ListNode(0);
            node.val = sum%10;
            carry = sum/10;


            tail.next = node;

            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;

        }

        while(l1!= null){
            sum = carry + l1.val;
            carry = sum/10;
            ListNode node = new ListNode(0);
            node.val = sum%10;
            tail.next = node;

            tail = tail.next;
            l1 = l1.next;
        }

        while(l2!= null){
            sum = carry + l2.val;
            carry = sum/10;
            ListNode node = new ListNode(0);
            node.val = sum%10;
            tail.next = node;

            tail = tail.next;
            l2 = l2.next;
        }

        if(carry != 0){
            ListNode node = new ListNode(0);
            node.val = carry;
            tail.next = node;

            tail = tail.next;
        }
        return ln;
    }
}
