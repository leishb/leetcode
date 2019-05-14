package com.leishb.leetcode.linkedlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/5/9.
 * https://leetcode.com/problems/reorder-list/
 */
public class ReOrderList {


    /**
     * Accepted
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head==null||head.next==null||head.next.next==null){
            return;
        }
        ListNode next = head.next;
        ListNode tail = head;
        ListNode pre = null;
        while (tail.next!=null){
            pre = tail;
            tail = tail.next;
        }
        pre.next = null;
        head.next = tail;
        tail.next = next;
        reorderList(next);
    }


    /**
     * Accepted
     * @param head
     */
    public void reorderList2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur!=null){
            list.add(cur);
            cur = cur.next;
        }
        int size = list.size();
        int i=0;
        int j=size-1;
        while (i+1<j){
            ListNode next = list.get(i).next;
            list.get(i).next = list.get(j);
            list.get(j).next = next;
            list.get(j-1).next = null;
            i++;
            j--;
        }
    }

    /**
     * Accepted
     * @param head
     */
    public void reorderList3(ListNode head) {
        if (head==null||head.next==null){
            return;
        }
        //1.find middle
        ListNode slow = head;
        ListNode fast = slow;
        ListNode preMid = null;
        while (fast!=null && fast.next!=null){
            preMid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //2.reverse second half
        preMid.next = null;
        ListNode mid = slow;
        ListNode prev = null;
        while (mid!=null){
            ListNode next = mid.next;
            mid.next = prev;
            prev = mid;
            mid = next;
        }
        //3.merge
        ListNode first = head;
        ListNode second = prev;
        while (first!=null && second!=null){
            ListNode next = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            if (next!=null){
                second.next = next;
            }
            first = next;
            second = secondNext;
        }
    }



    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderList3(head);

        System.out.println(head);
    }

}
