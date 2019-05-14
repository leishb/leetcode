package com.leishb.leetcode.tree;

import java.util.List;

/**
 * Created by me on 2019/5/10.
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * Accepted
 */
public class BSTIterator {

    private TreeNode root = null;

    public BSTIterator(TreeNode root) {
        this.root = root;
    }

    private void inOrder(TreeNode root, List<Integer> list){
        if (root!=null){
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode parent = null;
        TreeNode cur = root;
        while (cur.left!=null){
            parent = cur;
            cur = cur.left;
        }
        if (parent==null){
            this.root = cur.right;
        }else {
            parent.left = cur.right;
        }
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.root!=null;
    }
}
