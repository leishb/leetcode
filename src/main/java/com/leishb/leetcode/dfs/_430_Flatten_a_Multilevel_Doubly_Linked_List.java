package com.leishb.leetcode.dfs;

import org.junit.Test;

/**
 * Created by me on 2019/7/1.
 */
public class _430_Flatten_a_Multilevel_Doubly_Linked_List {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }


    public Node flatten(Node head) {
        dfs(head);
        return head;
    }


    private Node dfs(Node head){
        Node cur = head;
        Node tail = null;
        while (cur!=null){
            tail = cur;
            Node next = cur.next;
            if (cur.child!=null){
                cur.next = cur.child;
                cur.child.prev = cur;
                Node t = dfs(cur.child);
                tail = t;
                cur.child = null;
                t.next = next;
                if (next!=null){
                    next.prev = t;
                }
            }
            cur = next;
        }
        return tail;
    }


    @Test
    public void test(){
        Node head = new Node(1, null, null, null);
        head.child = new Node(2, null, null, null);
        head.child.child = new Node(3, null, null, null);

        flatten(head);
    }
}
