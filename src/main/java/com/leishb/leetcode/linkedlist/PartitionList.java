package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2019/6/25.
 */
public class PartitionList {

    /**
     * Accepted
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode h1 = new ListNode(0);
        ListNode h2 = new ListNode(0);
        ListNode t1 = h1;
        ListNode t2 = h2;
        ListNode cur = head;
        while (cur!=null){
            if (cur.val < x){
                t1.next = cur;
                t1 = cur;
            }else if (cur.val >=x){
                t2.next = cur;
                t2 = t2.next;
            }
            cur = cur.next;
        }
        t2.next = null;
        t1.next = h2.next;
        return h1.next;
    }
}
