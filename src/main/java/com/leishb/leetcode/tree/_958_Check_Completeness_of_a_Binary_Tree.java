package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by me on 2019/9/5.
 */
public class _958_Check_Completeness_of_a_Binary_Tree {

    /**
     * Accepted
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root==null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                TreeNode cur = queue.poll();
                if (cur.left==null && cur.right!=null){
                    return false;
                }
                if ((cur.left!=null || cur.right!=null) && end){
                    return false;
                }
                if ((cur.left==null && cur.right==null) || (cur.left!=null && cur.right==null)){
                    end = true;
                }
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
            }
        }
        return true;
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public boolean isCompleteTree2(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        preOrder(root, 0, set);
        for (int i=0;i<set.size();i++){
            if (!set.contains(i)){
                return false;
            }
        }
        return true;
    }


    private void preOrder(TreeNode node , int k, Set<Integer> set){
        if (node!=null){
            set.add(k);
            preOrder(node.left, 2*k+1, set);
            preOrder(node.right, 2*k+2, set);
        }
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        Assert.assertTrue(isCompleteTree2(root));
        root.right.left = new TreeNode(1);
        Assert.assertFalse(isCompleteTree2(root));
    }
}
