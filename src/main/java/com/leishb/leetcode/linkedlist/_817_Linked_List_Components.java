package com.leishb.leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2020/1/3.
 */
public class _817_Linked_List_Components {


    /**
     * Accepted
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i : G){
            set.add(i);
        }
        ListNode cur = head, prev = null;
        while (cur!=null){
            if (set.contains(cur.val)){
                if (prev==null || !set.contains(prev.val)){
                    count++;
                }
            }
            prev = cur;
            cur = cur.next;
        }
        return count;
    }
}
