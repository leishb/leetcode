package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/9/5.
 */
public class _951_Flip_Equivalent_Binary_Trees {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1==null&&root2==null){
            return true;
        }else if(root1==null || root2==null){
            return false;
        }else if (root1.val!=root2.val){
            return false;
        }
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
                || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
