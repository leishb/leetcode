package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/7/23.
 */
public class _623_Add_One_Row_to_Tree {


    /**
     * Accepted
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d==1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            depth++;
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (depth+1==d){
                    TreeNode left = node.left;
                    TreeNode right = node.right;
                    node.left = new TreeNode(v);
                    node.right = new TreeNode(v);
                    node.left.left = left;
                    node.right.right = right;
                }
            }
            if (depth+1== d){
                break;
            }
        }
        return root;
    }


    /**
     * Accepted
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if (d==1){
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        List<TreeNode> list = new ArrayList<>();
        dfs(list, d, 1, root);
        for (TreeNode node : list){
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(v);
            node.right = new TreeNode(v);
            node.left.left = left;
            node.right.right = right;
        }
        return root;
    }

    private void dfs(List<TreeNode> list, int d, int depth, TreeNode root){
        if (depth>=d || root==null){
            return;
        }
        if (depth+1==d){
            list.add(root);
        }
        dfs(list, d, depth+1, root.left);
        dfs(list, d, depth+1, root.right);
    }
}
