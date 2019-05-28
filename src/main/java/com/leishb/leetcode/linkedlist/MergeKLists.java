package com.leishb.leetcode.linkedlist;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by me on 2019/3/7.
 */
public class MergeKLists {


    /**
     * Accepted
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((Comparator<ListNode>) (o1, o2) -> Integer.compare(o1.val, o2.val));
        for (ListNode node : lists){
            if (node!=null){
                queue.add(node);
            }
        }
        ListNode preHead = new ListNode(0);
        ListNode node = preHead;
        while (!queue.isEmpty()){
            ListNode listNode = queue.poll();
            node.next = listNode;
            node = node.next;
            if (listNode.next!=null){
                queue.add(listNode.next);
                listNode.next = null;
            }
        }
        return preHead.next;
    }

    /**
     * Accepted
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length==0){
            return null;
        }
        return mergeKListsWithConque(lists, 0, lists.length-1);
    }

    public ListNode mergeKListsWithConque(ListNode[] lists, int left, int right) {
        if (left>=right){
            return lists[left];
        }
        int mid = (left+right)/2;
        ListNode l1 = mergeKListsWithConque(lists, left, mid);
        ListNode l2 = mergeKListsWithConque(lists, mid+1, right);
        return mergeTwoLists(l1, l2);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode preHead = new ListNode(0);
        ListNode node = preHead;
        while(l1!=null && l2!=null){
            if (l1.val <= l2.val){
                node.next = l1;
                l1 = l1.next;
            }else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 != null){
            node.next = l1;
        }
        if (l2 != null){
            node.next = l2;
        }
        return preHead.next;
    }


    /**
     * Accepted
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null){
            return null;
        }
        int lenA = 0;
        ListNode cur = headA;
        while (cur!=null){
            lenA++;
            cur = cur.next;
        }
        int lenB = 0;
        cur = headB;
        while (cur!=null){
            lenB++;
            cur = cur.next;
        }
        return getIntersectionNode(headA, headB, lenA, lenB);
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB, int lenA, int lenB) {
        if (lenA < lenB){
            return getIntersectionNode(headB, headA, lenB, lenA);
        }
        ListNode cur = headA;
        while (lenA!=lenB){
            cur = cur.next;
            lenA--;
        }
        while (cur!=headB){
            cur = cur.next;
            headB = headB.next;
        }
        return cur;
    }

    /**
     * Accepted
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode curA =headA;
        ListNode curB = headB;
        while (curA != curB){
            curA = curA==null?headB:curA.next;
            curB = curB==null?headA:curB.next;
        }
        return curA;
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head==null){
            return null;
        }
        int count = 0;
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode next = head.next;
        ListNode cur = head;
        while (cur != null){
            if (count%2==0){
                l1.next = cur;
                l1 = l1.next;
            }else {
                l2.next = cur;
                l2 = l2.next;
            }
            cur = cur.next;
            count++;
        }
        l1.next = next;
        l2.next = null;
        return head;
    }




    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        oddEvenList(head);
    }
}
