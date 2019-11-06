package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/5.
 */
public class _1120_Maximum_Average_Subtree {

    public double maximumAverageSubtree(TreeNode root) {
        inorder(root);
        return max;
    }

    double max = Double.MIN_VALUE;

    private int[] inorder(TreeNode root){
        if (root==null) return new int[]{0, 0};
        int[] left = inorder(root.left);
        int[] right = inorder(root.right);
        int[] ans = new int[]{left[0]+right[0]+root.val, left[1]+right[1]+1};
        double avg = ans[0]/(ans[1]*1.0);
        max = Math.max(max, avg);
        return ans;
    }
}
