package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/10/8.
 */
public class _285_Inorder_Successor_in_BST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root==null || p==null) return null;
        if (p.right!=null){
            TreeNode r = p.right;
            while (r.left!=null){
                r = r.left;
            }
            return r;
        }
        return find(root, p, null);
    }

    private TreeNode find(TreeNode root, TreeNode p, TreeNode cur){
        if (root!=null){
            if (root.val > p.val) {
                return find(root.left, p, root);
            }else if (root.val < p.val){
                return find(root.right, p, cur);
            }
            return cur;
        }
        return null;
    }


    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root==null || p==null) return null;
        TreeNode res = null;
        while (root!=null){
            if (root.val > p.val){
                res = root;
                root = root.left;
            }else {
                root = root.right;
            }
        }
        return res;
    }
}
