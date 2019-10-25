package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2019/10/25.
 */
public class _369_Plus_One_Linked_List {

    public ListNode plusOne(ListNode head) {
        ListNode newHead = reverse(head);
        ListNode cur = newHead;
        int carry = 1;
        while (cur!=null){
            int sum = cur.val +carry;
            cur.val = sum%10;
            carry = sum/10;
            if (cur.next==null && carry!=0) {
                cur.next = new ListNode(carry);
                break;
            }
            cur = cur.next;
        }
        return reverse(newHead);
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

    public ListNode plusOne2(ListNode head) {
        ListNode newHead = helper(head);
        if (carry==1){
            ListNode dummy = new ListNode(1);
            dummy.next = newHead;
            return dummy;
        }
        return newHead;
    }


    int carry = 1;

    private ListNode helper(ListNode head){
        if (head.next==null){
            int sum = head.val+carry;
            head.val = sum%10;
            carry = sum/10;
            return head;
        }
        head.next = helper(head.next);
        int sum = head.val+carry;
        head.val = sum%10;
        carry = sum/10;
        return head;
    }
}
