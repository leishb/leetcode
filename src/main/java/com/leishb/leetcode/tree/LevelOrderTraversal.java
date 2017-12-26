package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/12/26.
 */
public class LevelOrderTraversal {

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        zigzagLevelOrder(root);
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root == null){
            return ans;
        }
        List<List<TreeNode>> nodeList = new ArrayList<>();
        nodeList.add(Arrays.asList(root));
        ans.add(Arrays.asList(root.val));
        while (true){
            List<Integer> valList = new ArrayList<>();
            List<TreeNode> nl = new ArrayList<>();
            for (TreeNode node : nodeList.get(nodeList.size()-1)){
                if (node.left != null){
                    nl.add(node.left);
                    valList.add(node.left.val);
                }
                if (node.right != null){
                    nl.add(node.right);
                    valList.add(node.right.val);
                }
            }
            if (nl.size()==0) {
                break;
            }
            nodeList.add(nl);
            ans.add(valList);
        }
        return ans;
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if(root == null){
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList();
            int size = queue.size();
            for(int i=0;i<size;i++){
                if(queue.peek().left!=null){
                    queue.offer(queue.peek().left);
                }
                if(queue.peek().right!=null){
                    queue.offer(queue.peek().right);
                }
                list.add(queue.poll().val);
            }
            ans.add(list);
        }
        return ans;
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean order = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (order){
                    temp.add(node.val);
                }else {
                    temp.add(0, node.val);
                }
            }
            order = !order;
            ans.add(temp);
        }
        return ans;
    }
}
