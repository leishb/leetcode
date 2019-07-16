package com.leishb.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/7/16.
 */
public class _404_Sum_of_Left_Leaves {

    int sum = 0;


    /**
     * Accepted
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root!=null){
            sumOfLeftLeaves(root, false);
        }
        return sum;
    }


    public void sumOfLeftLeaves(TreeNode root, boolean left) {
        if (root.left==null && root.right == null && left){
            sum += root.val;
            return;
        }
        if (root.left!=null){
            sumOfLeftLeaves(root.left, true);
        }
        if (root.right!=null){
            sumOfLeftLeaves(root.right, false);
        }
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root==null){
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.left!=null){
                queue.offer(cur.left);
                if (cur.left.left==null && cur.left.right==null){
                    sum += cur.left.val;
                }
            }
            if (cur.right!=null){
                queue.offer(cur.right);
            }
        }
        return sum;
    }

}
