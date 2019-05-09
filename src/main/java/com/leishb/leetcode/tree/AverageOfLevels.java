package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/5/6.
 */
public class AverageOfLevels {

    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList();
        if(root==null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            double sum = 0;
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                sum += node.val;
            }
            result.add(sum/(double)size);
        }
        return result;
    }

    public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> sumList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<Double> result = new ArrayList<>();
        dfs(root, sumList, countList, 0);
        for (int i=0;i<sumList.size();i++){
            result.add(sumList.get(i)/countList.get(i));
        }
        return result;
    }


    private void dfs(TreeNode root, List<Double> sumList, List<Integer> countList, int level){
        if (root==null){
            return;
        }
        if (sumList.size()==level){
            sumList.add(0.0);
            countList.add(0);
        }
        sumList.set(level, sumList.get(level)+root.val);
        countList.set(level, countList.get(level)+1);
        dfs(root.left, sumList, countList, level+1);
        dfs(root.right, sumList, countList, level+1);
    }
}
