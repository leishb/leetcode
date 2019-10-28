package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/10/28.
 */
public class _1123_Lowest_Common_Ancestor_of_Deepest_Leaves {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root==null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<TreeNode> leaves = new ArrayList<>();
        while (!queue.isEmpty()){
            leaves = new ArrayList<>();
            int size = queue.size();
            while (size-->0){
                TreeNode p = queue.poll();
                leaves.add(p);
                if (p.left!=null){
                    queue.offer(p.left);
                }
                if (p.right!=null){
                    queue.offer(p.right);
                }
            }
        }
        TreeNode prev = leaves.get(0);
        TreeNode ans = prev;
        for (int i=1;i<leaves.size();i++){
            ans = lowestCommonAncestor(root, prev, leaves.get(i));
            prev = ans;
        }
        return ans;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null || root==p || root==q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left!=null && right!=null){
            return root;
        }
        if (left!=null){
            return left;
        }
        return right;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        lcaDeepestLeaves(root);
    }
}
