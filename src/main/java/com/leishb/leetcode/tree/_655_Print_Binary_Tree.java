package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/7/25.
 */
public class _655_Print_Binary_Tree {


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        int h = getHeight(root);
        int rows = h;
        int cols = (int) (Math.pow(2, h)-1);
        for (int i=0;i<rows;i++){
            List<String> list = new ArrayList<>();
            for (int j=0;j<cols;j++){
                list.add("");
            }
            result.add(list);
        }
        populateTree(root, 0, rows, 0, cols-1, result);
        return result;
    }


    private void populateTree(TreeNode root, int rows, int  totalRows, int i, int j, List<List<String>> result){
        if (root==null||rows==totalRows){
            return;
        }
        result.get(rows).set((i+j)/2, root.val+"");
        populateTree(root.left, rows+1, totalRows, i, (i+j)/2-1, result);
        populateTree(root.right, rows+1, totalRows, (i+j)/2+1, j, result);
    }


    private int getHeight(TreeNode root){
        if (root==null){
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right))+1;
    }




    public List<List<String>> printTree2(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        int h = getHeight(root);
        int rows = h;
        int cols = (int) (Math.pow(2, h)-1);
        for (int i=0;i<rows;i++){
            List<String> list = new ArrayList<>();
            for (int j=0;j<cols;j++){
                list.add("");
            }
            result.add(list);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<int[]> indexQ = new LinkedList<>();
        queue.offer(root);
        indexQ.offer(new int[]{0, cols-1});
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                int[] idx = indexQ.poll();
                if (node==null){
                    continue;
                }
                result.get(depth).set((idx[0]+idx[1])/2, node.val+"");
                queue.offer(node.left);
                queue.offer(node.right);
                indexQ.offer(new int[]{idx[0], (idx[0]+idx[1])/2-1});
                indexQ.offer(new int[]{(idx[0]+idx[1])/2+1, idx[1]});
            }
            depth++;
        }
        return result;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(printTree2(root));
    }
}
