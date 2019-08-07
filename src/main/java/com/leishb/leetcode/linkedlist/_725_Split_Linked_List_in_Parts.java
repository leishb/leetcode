package com.leishb.leetcode.linkedlist;

import org.junit.Test;

/**
 * Created by me on 2019/8/5.
 */
public class _725_Split_Linked_List_in_Parts {


    /**
     * Accepted
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] nodes = new ListNode[k];
        int len = 0;
        ListNode cur = root;
        while (cur!=null){
            cur = cur.next;
            len++;
        }
        int counts = len/k;
        int modes = len%k;
        int n = 0;
        cur = root;
        while (cur!=null){
            ListNode prev = null;
            nodes[n++] = cur;
            for (int i=0;i<counts;i++){
                prev = cur;
                cur = cur.next;
            }
            if (modes>0){
                prev = cur;
                cur = cur.next;
                modes--;
            }
            if (prev!=null){
                prev.next = null;
            }
        }
        return nodes;
    }


    @Test
    public void test(){
        ListNode root = new ListNode(0);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);
        root.next.next.next = new ListNode(3);
        root.next.next.next.next = new ListNode(4);
        splitListToParts(root, 3);
    }
}
