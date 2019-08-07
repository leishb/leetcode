package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/8/7.
 */
public class _617_Merge_Two_Binary_Trees {


    /**
     * Accepted
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null||t2==null){
            return t1==null?t2:t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
