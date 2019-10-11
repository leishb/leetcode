package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2019/10/10.
 */
public class _25_Reverse_Nodes_in_k_Group {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null || k==0) return head;
        int len = 0;
        ListNode cur = head;
        ListNode newHead = null;
        while (cur!=null && len<k){
            len++;
            newHead = cur;
            cur = cur.next;
        }
        if (len<k) return head;
        reverse(head, cur);
        head.next = reverseKGroup(cur, k);
        return newHead;
    }


    private void reverse(ListNode head, ListNode tail){
        ListNode cur = head;
        ListNode prev = null;
        while (cur!=tail){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
}
