package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/9/11.
 */
public class _1026_Maximum_Difference_Between_Node_and_Ancestor {


    int max = 0;

    /**
     * Accepted
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        travel(root);
        return max;
    }


    private int[] travel(TreeNode root){
        if (root.left==null && root.right==null){
            return new int[]{root.val, root.val};
        }
        if (root.left==null){
            int[] right = travel(root.right);
            max = Math.max(max, Math.max(Math.abs(root.val-right[0]), Math.abs(root.val-right[1])));
            return new int[]{Math.max(root.val, right[0]), Math.min(root.val, right[1])};
        }
        if (root.right==null){
            int[] left = travel(root.left);
            max = Math.max(max, Math.max(Math.abs(root.val-left[0]), Math.abs(root.val-left[1])));
            return new int[]{Math.max(root.val, left[0]), Math.min(root.val, left[1])};
        }
        int[] left = travel(root.left);
        int[] right = travel(root.right);
        max = Math.max(max, Math.max(Math.abs(root.val-right[0]), Math.abs(root.val-right[1])));
        max = Math.max(max, Math.max(Math.abs(root.val-left[0]), Math.abs(root.val-left[1])));
        return new int[]{Math.max(root.val, Math.max(left[0], right[0])), Math.min(root.val, Math.min(left[1], right[1]))};
    }


    private int dfs(TreeNode root, int max, int min){
        if (root==null) return max-min;
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        return Math.max(dfs(root.left, max, min), dfs(root.right, max, min));
    }
}
