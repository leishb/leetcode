package com.leishb.leetcode.linkedlist;

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
}
