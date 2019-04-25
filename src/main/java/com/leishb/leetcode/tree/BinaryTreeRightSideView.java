package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/4/25.
 */
public class BinaryTreeRightSideView {


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root==null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (i==size-1){
                    result.add(node.val);
                }
            }
        }
        return result;
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result, int curDepth){
        if (root==null){
            return;
        }
        if (curDepth==result.size()){
            result.add(root.val);
        }
        dfs(root.right, result, curDepth+1);
        dfs(root.left, result, curDepth+1);
    }
}
