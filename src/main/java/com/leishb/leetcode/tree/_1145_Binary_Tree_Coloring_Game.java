package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/20.
 */
public class _1145_Binary_Tree_Coloring_Game {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root==null) return false;
        if (root.val==x){
            int left = count(root.left);
            int right = count(root.right);
            int parent = n-left-right-1;
            return parent > (left+right) || left > (parent+right) || right > (left+parent);
        }
        return btreeGameWinningMove(root.left, n, x) || btreeGameWinningMove(root.right, n, x);
    }


    private int count(TreeNode node){
        if (node==null)return 0;
        return count(node.left)+count(node.right)+1;
    }



    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        Assert.assertFalse(btreeGameWinningMove(root, 5, 2));
        Assert.assertTrue(btreeGameWinningMove(root, 5, 4));
    }
}
