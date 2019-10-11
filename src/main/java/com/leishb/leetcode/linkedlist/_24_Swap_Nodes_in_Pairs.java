package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2019/10/10.
 */
public class _24_Swap_Nodes_in_Pairs {

    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode cur = head;
        ListNode next = cur.next;
        cur.next = swapPairs(next.next);
        next.next = cur;
        return next;
    }



    public ListNode swapPairs2(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode newHead = head.next;
        ListNode cur = head;
        ListNode prev = null;
        while (cur!=null && cur.next!=null){
            ListNode next = cur.next;
            ListNode nn = next.next;
            next.next = cur;
            cur.next = nn;
            if (prev!=null) prev.next = next;
            prev = cur;
            cur = nn;
        }
        return newHead;
    }
}
