package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/27.
 */
public class _270_Closest_Binary_Search_Tree_Value {


    int ans = 0;

    public int closestValue(TreeNode root, double target) {
        ans = root.val;
        visit(root, target);
        return ans;
    }


    private void visit(TreeNode root, double target){
        if (root!=null){
            if (Math.abs(root.val-target) < Math.abs(ans-target)) ans = root.val;
            visit(root.left, target);
            visit(root.right, target);
        }
    }

    public int closestValue2(TreeNode root, double target) {
        int val = root.val;
        while (root!=null){
            if (Math.abs(root.val-target) < Math.abs(val-target)){
                val = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return val;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);

        Assert.assertTrue(closestValue(root, 6.0)==6);
    }
}
