package com.leishb.leetcode.tree;

import com.leishb.leetcode.tag.DFS;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/12/28.
 */
@DFS
public class SumRootToLeafNumbers {

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);


        Assert.assertTrue(hasPathSum(root, 6));

        flatten(root);
    }



    public int total = 0;

    /**
     * Accepted
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return total;
    }


    private void dfs(TreeNode node,  String s){
        if (node==null){
            return;
        }
        if (node.left == null && node.right == null){
            total += Integer.parseInt(s+node.val);
            return;
        }
        dfs(node.left, s+node.val);
        dfs(node.right, s+node.val);
    }


    /**
     * Accepted
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        if (root.left == null && root.right == null ){
            if (sum == root.val) {
                return true;
            }
            return false;
        }
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }


    public void flatten(TreeNode root) {
        TreeNode prev = new TreeNode(0);
        flatten(root, prev);
    }


    private void flatten(TreeNode root, TreeNode prev){
        if(root == null){
            return;
        }
        TreeNode right = root.right;
        flatten(root.left, root);
        prev.right = root.left;
        flatten(right, root);
    }
}
