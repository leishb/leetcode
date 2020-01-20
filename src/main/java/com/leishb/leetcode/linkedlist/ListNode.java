package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2017/12/20.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode prev;

    public ListNode(int x){
        this.val = x;
    }


    @Override
    public String toString() {
        return this.val+"->";
    }
}
