package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/9/24.
 */
public class _426_Convert_Binary_Search_Tree_to_Sorted_Doubly_Linked_List {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    public Node treeToDoublyList(Node root) {
        if (root==null) return null;
        Node head = helper(root, true);
        Node tail =head;
        while (tail.right!=null){
            tail = tail.right;
        }
        head.left = tail;
        tail.right = head;
        return head;
    }


    private Node helper(Node root, boolean retMin){
        if (root==null) return null;
        Node left = helper(root.left, false);
        Node right = helper(root.right, true);
        root.left = left;
        root.right = right;
        if (left!=null)left.right = root;
        if (right!=null)right.left = root;
        Node cur = root;
        if (retMin){
            while (cur.left!=null){
                cur = cur.left;
            }
        }else {
            while (cur.right!=null){
                cur = cur.right;
            }
        }
        return cur;
    }



    public Node treeToDoublyList2(Node root) {
        if (root==null) return null;
        Node left = treeToDoublyList2(root.left);
        Node right = treeToDoublyList2(root.right);
        root.left = root;
        root.right = root;
        return connect(connect(left, root), right);
    }


    private Node connect(Node n1, Node n2){
        if (n1==null)return n2;
        if (n2==null)return n1;

        Node tail1 = n1.left;
        Node tail2 = n2.left;

        tail1.right = n2;
        n2.left = tail1;
        n1.left = tail2;
        tail2.right = n1;
        return n1;
    }


    @Test
    public void test(){
        Node root = new Node(4, new Node(2, new Node(1, null, null), new Node(3, null, null)), new Node(5, null, null));
        treeToDoublyList(root);
    }
}
