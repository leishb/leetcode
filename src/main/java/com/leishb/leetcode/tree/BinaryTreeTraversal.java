package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2017/12/20.
 */
public class BinaryTreeTraversal {


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();

        travel(root , list);

        return list;
    }


    private void travel(TreeNode root, List<Integer> list){
        if(root==null){
            return;
        }
        travel(root.left, list);
        list.add(root.val);
        travel(root.right, list);
    }


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            ans.add(node.val);
            cur = node.right;
        }
        return ans;
    }

    /**
     * Accepted
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        traversal(root, ans);
        return ans;
    }

    private void traversal(TreeNode root, List<Integer> ans){
        if(root == null){
            return;
        }
        ans.add(root.val);
        traversal(root.left, ans);
        traversal(root.right, ans);
    }


    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                ans.add(cur.val);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            cur = node.right;
        }
        return ans;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur !=null){
                stack.push(cur);
                ans.add(0, cur.val);
                cur = cur.right;
            }
            TreeNode node = stack.pop();
            cur = node.left;
        }
        return ans;
    }
}
