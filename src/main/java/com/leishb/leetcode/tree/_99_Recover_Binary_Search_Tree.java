package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/10/14.
 */
public class _99_Recover_Binary_Search_Tree {


    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val= temp;
    }


    private void helper(TreeNode root){
        if (root==null)return;
        helper(root.left);
        if (prev!=null && root.val < prev.val){
            second = root;
            if (first==null) first = prev;
        }
        prev = root;
        helper(root.right);
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        recoverTree(root);
    }
}
