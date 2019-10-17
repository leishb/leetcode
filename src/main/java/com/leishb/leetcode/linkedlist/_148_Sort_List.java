package com.leishb.leetcode.linkedlist;

import org.junit.Test;

/**
 * Created by me on 2019/10/14.
 */
public class _148_Sort_List {

    public ListNode sortList(ListNode head) {
        if (head==null) return null;
        ListNode cur = head;
        while (cur.next!=null){
            cur = cur.next;
        }
        return sortList(head, cur);
    }


    public ListNode sortList(ListNode head, ListNode tail) {
        if (head!=tail){
            ListNode mid = findMid(head);
            ListNode next = mid.next;
            mid.next = null;
            ListNode l1 = sortList(head, mid);
            ListNode l2 = sortList(next, tail);
            return merge(l1, l2);
        }
        return head;
    }


    private ListNode merge(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1!=null && l2!=null){
            if (l1.val<=l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1!=null?l1:l2;
        return head.next;
    }

    private ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void test(){
        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(3);
        sortList(l1);
    }
}
