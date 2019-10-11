package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/9/26.
 */
public class _543_Diameter_of_Binary_Tree {

    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }

    int max = 0;

    private int helper(TreeNode root){
        if (root==null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(max, left+right);
        return Math.max(left, right)+1;
    }
}
