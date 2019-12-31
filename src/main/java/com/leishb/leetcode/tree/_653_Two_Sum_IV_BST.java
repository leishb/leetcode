package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2019/12/25.
 */
public class _653_Two_Sum_IV_BST {



    public boolean findTarget(TreeNode root, int k) {
        if (root==null) return false;
        List<Integer> list = new ArrayList<>();
        inOrder(list, root);
        int i=0, j = list.size()-1;
        while (i<j){
            if (list.get(i) + list.get(j)==k) return true;
            else if (list.get(i) + list.get(j) < k) i++;
            else j--;
        }
        return false;
    }


    private void inOrder(List<Integer> list, TreeNode root){
        if (root==null) return;
        inOrder(list, root.left);
        list.add(root.val);
        inOrder(list, root.right);
    }


    public boolean findTarget2(TreeNode root, int k) {
        Stack<TreeNode> stackL = new Stack<>();
        Stack<TreeNode> stackR = new Stack<>();

        for (TreeNode cur = root;cur!=null;cur = cur.left){
            stackL.push(cur);
        }
        for (TreeNode cur = root;cur!=null;cur = cur.right){
            stackR.push(cur);
        }
        while (stackL.size()!= 0 && stackR.size()!=0 && stackL.peek() != stackR.peek()){
            int sum = stackL.peek().val + stackR.peek().val;
            if (sum==k)return true;
            else if (sum < k){
                for (TreeNode cur = stackL.pop().right;cur!=null;cur=cur.left){
                    stackL.push(cur);
                }
            }else {
                for (TreeNode cur = stackR.pop().left;cur!=null;cur=cur.right){
                    stackR.push(cur);
                }
            }
        }
        return false;
    }
}
