package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2017/12/20.
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode prev;

    ListNode(int x){
        this.val = x;
    }


    @Override
    public String toString() {
        return this.val+"->";
    }
}
