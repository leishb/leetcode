package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/4.
 */
public class _431_Encode_N_ary_Tree_to_Binary_Tree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root==null) return null;
        TreeNode tn = new TreeNode(root.val);
        tn.left = root.children.size()>=1 ? encode(root.children.get(0)) : null;
        TreeNode l = tn.left;
        for(int i=1;i<root.children.size();i++){
            l.right = encode(root.children.get(i));
            l = l.right;
        }
        return tn;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root==null) return null;
        Node node = new Node(root.val, new ArrayList<>());
        if (root.left!=null){
            node.children.add(decode(root.left));
            TreeNode cur = root.left;
            while (cur.right!=null){
                node.children.add(decode(cur.right));
                cur = cur.right;
            }
        }
        return node;
    }
}
