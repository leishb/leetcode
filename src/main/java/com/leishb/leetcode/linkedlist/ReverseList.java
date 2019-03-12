package com.leishb.leetcode.linkedlist;

import org.junit.Test;

/**
 * Created by me on 2017/12/18.
 */
public class ReverseList {

    @Test
    public void test(){
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(7);
        reverseBetween2(head, 2,4);
    }





    /**
     * Accepted
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || m==n){
            return head;
        }
        ListNode newHead = head;
        ListNode reversePrev = null;
        ListNode reverseHead = newHead;
        int count = 1;
        while (count != m){
            reversePrev = newHead;
            newHead = newHead.next;
            reverseHead = newHead;
            count++;
        }
        ListNode reverseTail = reverseHead;
        while (count != n){
            count++;
            newHead = newHead.next;
            reverseTail = newHead;
        }
        ListNode reverseNext = reverseTail.next;
        ListNode newReverseHead = reverse(reverseHead, reverseTail);
        if (reversePrev != null){
            reversePrev.next = newReverseHead;
        }
        reverseHead.next = reverseNext;
        return reversePrev == null ? newReverseHead : head;
    }


    private ListNode reverse(ListNode head, ListNode tail){
        ListNode prev = null;
        ListNode cur = head;
        ListNode end = tail.next;
        while(cur != end){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }


    /**
     * Accepted
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if(head==null || m==n){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = head;
        ListNode prev = dummy;
        int count = 1;
        while (count != m){
            prev = start;
            start = start.next;
            count++;
        }
        ListNode then = start.next;
        while (count != n){
            ListNode next = prev.next;
            start.next = then.next;
            prev.next = then;
            then.next = next;
            then = start.next;
            count++;
        }
        return dummy.next;
    }
}
