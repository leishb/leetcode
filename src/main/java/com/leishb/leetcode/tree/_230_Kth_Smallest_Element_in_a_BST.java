package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/10/21.
 */
public class _230_Kth_Smallest_Element_in_a_BST {


    int ans = 0;
    int id = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans;
    }


    private void inorder(TreeNode root , int k){
        if (root!=null){
            inorder(root.left, k);
            id++;
            if (id==k){
                ans = root.val;
                return;
            }
            inorder(root.right, k);
        }
    }
}
