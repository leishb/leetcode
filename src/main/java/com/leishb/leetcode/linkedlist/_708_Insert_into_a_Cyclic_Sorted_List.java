package com.leishb.leetcode.linkedlist;

/**
 * Created by me on 2019/9/26.
 */
public class _708_Insert_into_a_Cyclic_Sorted_List {

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head==null){
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        Node cur = head.next;
        Node prev = head;
        while (!(insertVal>=prev.val && insertVal<=cur.val)){
            if (prev.val > cur.val && (insertVal >= prev.val || insertVal<=cur.val)){
                break;
            }
            prev = cur;
            cur = cur.next;
            if (cur == head.next){
                break;
            }
        }
        Node node = new Node(insertVal, cur);
        prev.next = node;
        return head;
    }
}
