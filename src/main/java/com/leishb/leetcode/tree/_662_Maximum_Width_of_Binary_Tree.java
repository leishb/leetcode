package com.leishb.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/7/29.
 */
public class _662_Maximum_Width_of_Binary_Tree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root==null){
            return 0;
        }
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> idxQ = new LinkedList<>();
        queue.offer(root);
        idxQ.offer(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            int left = 0;
            int right = 0;
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                int id = idxQ.poll();
                if (i==0){
                    left = id;
                }
                if (i==size-1){
                    right = id;
                }
                if (node.left!=null){
                    queue.offer(node.left);
                    idxQ.offer(id*2+1);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                    idxQ.offer(id*2+2);
                }
            }
            max = Math.max(right-left+1, max);
        }
        return max;
    }
}
