package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/1.
 */
public class _510_Inorder_Successor_in_BST_II {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node inorderSuccessor(Node x) {
        Node root = x;
        while (root.parent!=null){
            root = root.parent;
        }
        Node ans = null;
        while (root!=null){
            if (root.val > x.val){
                ans = root;
                root = root.left;
            }else {
                root = root.right;
            }
        }
        return ans;
    }

    public Node inorderSuccessor2(Node x) {
        Node cur = x.right;
        if (cur!=null){
            while (cur.left!=null){
                cur = cur.left;
            }
            return cur;
        }
        Node p = x.parent;
        while (p != null && x != p.left){
            x = p;
            p = p.parent;
        }
        return p;
    }
}
