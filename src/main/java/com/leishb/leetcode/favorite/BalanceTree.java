package com.leishb.leetcode.favorite;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/6/21.
 */
public class BalanceTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x){
            this.val = x;
        }
    }

    Map<TreeNode, Integer> map = new HashMap();

    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right);
    }



    private int getHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        if(map.containsKey(root)){
            System.out.println(root.val + "->" + map.get(root));
            return map.get(root);
        }
        int height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        map.put(root, height);
        return height;
    }



    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        root.left  = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(7);
        root.right.right.right = new TreeNode(7);
        isBalanced(root);
    }
}
