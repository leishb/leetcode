package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/8.
 */
public class _1214_Two_Sum_BSTsa {

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1==null || root2==null) return false;
        if (root1.val+root2.val==target) return true;
        else if (root1.val+root2.val > target){
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        }else {
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        }
    }


    public boolean twoSumBSTs2(TreeNode root1, TreeNode root2, int target) {
        if (root1==null || root2==null) return false;
        return isFound(root1, target-root2.val) ||
                twoSumBSTs2(root1, root2.left, target) || twoSumBSTs2(root1, root2.right, target);
    }

    private boolean isFound(TreeNode r, int target){
        while (r!=null){
            if (r.val == target) return true;
            if (r.val > target){
                r = r.left;
            }else {
                r = r.right;
            }
        }
        return false;
    }
}
