package com.leishb.leetcode.linkedlist;

import org.junit.Test;

/**
 * Created by me on 2019/11/5.
 */
public class _147_Insertion_Sort_List {


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = insertionSortList(next);
        ListNode cur = newHead;
        ListNode prev = null;
        while (cur!=null && cur.val < head.val){
            prev = cur;
            cur = cur.next;
        }
        if (prev!=null){
            prev.next = head;
            head.next = cur;
            return newHead;
        }else {
            head.next = cur;
            return head;
        }
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            while (prev.next!=null && prev.next.val < cur.val){
                prev = prev.next;
            }
            cur.next = prev.next;
            prev.next = cur;
            prev = dummy;
            cur = next;
        }
        return dummy.next;
    }

    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        insertionSortList2(head);
    }
}
