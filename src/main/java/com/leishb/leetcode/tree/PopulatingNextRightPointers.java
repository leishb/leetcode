package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/4/24.
 */
public class PopulatingNextRightPointers {
    
    
    class Node {
        Node left;
        Node right;
        Node next;
        int val;
        Node(int val){
            this.val = val;
        }

        @Override
        public String toString(){
            if (next!=null){
                return this.val + "->" +next.val;
            }
            return this.val + "-> NULL";
        }
    }

    /**
     * Accepted
     * @param root
     * @return
     */
    public Node connect(Node root){
        if (root==null||root.left==null||root.right==null){
            return root;
        }
        Node left = root.left;
        Node right = root.right;
        left.next = right;
        if (root.next!=null){
            right.next = root.next.left;
        }
        connect(left);
        connect(right);
        return root;
    }


    /**
     * TLE
     * @param root
     * @return
     */
    public Node connect2(Node root){
        if (root==null||(root.left==null&&root.right==null)){
            return root;
        }
        Node left = root.left;
        Node right = root.right;
        Node next = findNext(root.next);
        if (left!=null && right!=null){
            left.next = right;
            right.next=next;
        }else if (left!=null && right==null){
            left.next = next;
        }else {
            right.next = next;
        }
        connect2(root.next);
        connect2(root.left);
        return root;
    }


    private Node findNext(Node node){
        if (node==null){
            return null;
        }
        if (node.left==null && node.right==null){
            return findNext(node.next);
        }
        if (node.left!=null){
            return node.left;
        }else {
            return node.right;
        }
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public Node connect3(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node pre = null;
            for (int i=0;i<size;i++){
                Node node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (pre!=null){
                    pre.next = node;
                }
                pre = node;
            }
        }
        return root;
    }


    @Test
    public void test(){
        Node root = new Node(1);
        root.left=new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.left.left.left = new Node(4);

        root.left.right.left= new Node(6);
        root.left.right.right= new Node(7);
        root.left.right.right.left= new Node(8);

        root.right = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(11);
        root.right.right.left= new Node(12);
        root.right.right.right = new Node(13);

        connect3(root);

        preOrder(root);
    }


    private void preOrder(Node root){
        if (root!=null){
            System.out.println(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
}
