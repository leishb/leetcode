package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/7/5.
 */
public class _98_Validate_Binary_Search_Tree {


    /**
     * Accepted
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return check(root, null, null);
    }


    private boolean check(TreeNode root, Integer lower, Integer upper){
        if (root==null){
            return true;
        }
        if ((lower!=null && root.val <= lower) || (upper!=null && root.val >= upper)){
            return false;
        }
        return check(root.left, lower, root.val) && check(root.right, root.val, upper);
    }


    TreeNode prev;

    public boolean isValidBST2(TreeNode root) {
        if (root==null){
            return true;
        }
        if (!isValidBST2(root.left)){
            return false;
        }
        if (prev!=null && root.val <= prev.val){
            return false;
        }
        prev = root;
        if (!isValidBST2(root.right)){
            return false;
        }
        return true;
    }
}
