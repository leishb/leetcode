package com.leishb.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2020/1/6.
 */
public class _143_Reorder_List {


    /**
     * Accepted
     * @param head
     */
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        int k = 0;
        ListNode cur = head;
        while (cur!=null){
            map.put(k, cur);
            cur = cur.next;
            k++;
        }
        int i=0, j=k-1;
        ListNode dummy = new ListNode(0);
        while (i<j){
            dummy.next = map.get(i);
            dummy.next.next = map.get(j);
            dummy = map.get(j);
            i++;j--;
        }
        if(i==j){
            dummy.next = map.get(i);
            dummy = dummy.next;
        }
        dummy.next = null;
    }



    /**
     * Accepted
     * @param head
     */
    public void reorderList2(ListNode head) {
        if (head==null) return;
        ListNode cur = head;
        ListNode fast = cur.next;
        ListNode slow = cur;
        while (fast!=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode l2 = reverse(slow.next);
        ListNode l1 = head;
        slow.next = null;
        ListNode dummy = new ListNode(0);
        int i = 0;
        while (l1 !=null && l2 !=null){
            if (i%2==0){
                dummy.next = l1;
                dummy = l1;
                l1 = l1.next;
            }else {
                dummy.next = l2;
                dummy = l2;
                l2 = l2.next;
            }
            i++;
        }
        dummy.next = l1==null?l2 :l1;
    }

    private ListNode reverse(ListNode head){
        ListNode cur = head, prev = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

}
