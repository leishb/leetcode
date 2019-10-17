package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/16.
 */
public class _545_Boundary_of_Binary_Tree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<TreeNode> leaves = new ArrayList<>();
        inorder(root, leaves);
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        if (root.left==null && root.right==null){
            return ans;
        }
        TreeNode left = leaves.get(0);
        TreeNode cur = root.left;
        while (cur!=null && cur!=left){
            ans.add(cur.val);
            cur = cur.left!=null?cur.left:cur.right;
        }
        for (TreeNode node : leaves){
            ans.add(node.val);
        }
        TreeNode right = leaves.get(leaves.size()-1);
        cur = root.right;
        int size = ans.size();
        while (cur!=null && cur!=right){
            ans.add(size, cur.val);
            cur = cur.right!=null?cur.right:cur.left;
        }
        return ans;
    }


    private void inorder(TreeNode root, List<TreeNode> leaves){
        if (root!=null){
            inorder(root.left, leaves);
            if (root.left==null && root.right==null){
                leaves.add(root);
            }
            inorder(root.right, leaves);
        }
    }

    public List<Integer> boundaryOfBinaryTree2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root==null) return ans;
        if (!isLeaf(root)){
            ans.add(root.val);
        }
        TreeNode cur = root.left;
        while (cur!=null){
            if (!isLeaf(cur)){
                ans.add(cur.val);
            }
            cur = cur.left!=null?cur.left:cur.right;
        }
        addLeaves(root, ans);
        int size = ans.size();
        cur = root.right;
        while (cur!=null){
            if (!isLeaf(cur)){
                ans.add(size, cur.val);
            }
            cur = cur.right!=null ?cur.right:cur.left;
        }
        return ans;
    }


    private void addLeaves(TreeNode root, List<Integer> ans){
        if (root!=null){
            addLeaves(root.left, ans);
            if (isLeaf(root)){
                ans.add(root.val);
            }
            addLeaves(root.right, ans);
        }
    }


    private boolean isLeaf(TreeNode root){
        return root!=null && root.left==null && root.right==null;
    }
}
