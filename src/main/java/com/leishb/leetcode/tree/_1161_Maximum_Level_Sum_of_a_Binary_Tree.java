package com.leishb.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/11/5.
 */
public class _1161_Maximum_Level_Sum_of_a_Binary_Tree {

    public int maxLevelSum(TreeNode root) {
        if (root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int max = Integer.MIN_VALUE;
        int minLevel = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int sum = 0;
            while (size-->0){
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left!=null){
                    queue.offer(cur.left);
                }
                if (cur.right!=null){
                    queue.offer(cur.right);
                }
            }
            if (sum>max){
                max = sum;
                minLevel = level;
            }
            level++;
        }
        return minLevel;
    }
}
