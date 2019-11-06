package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/6.
 */
public class _998_Maximum_Binary_Tree_II {


    /**
     * Accepted
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root==null) return new TreeNode(val);
        if (root.val > val){
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }else {
            TreeNode node = new TreeNode(val);
            node.left = root.left;
            return node;
        }
    }
}
