package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2019/12/30.
 */
public class _1305_All_Elements_in_Two_Binary_Search_Trees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list = new ArrayList<>();
        BSTIterator t1 = new BSTIterator(root1);
        BSTIterator t2 = new BSTIterator(root2);
        while (t1.hasNext() && t2.hasNext()){
            TreeNode n1 = t1.peek();
            TreeNode n2 = t2.peek();
            if (n1.val < n2.val){
                t1.next();
                list.add(n1.val);
            }else {
                t2.next();
                list.add(n2.val);
            }
        }
        while (t1.hasNext()){
            list.add(t1.next().val);
        }
        while (t2.hasNext()){
            list.add(t2.next().val);
        }
        return list;
    }

    class BSTIterator{

        Stack<TreeNode> stack = new Stack<>();

        BSTIterator(TreeNode root){
            leftMostInStack(root);
        }

        private void leftMostInStack(TreeNode root){
            TreeNode cur = root;
            while (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
        }

        public TreeNode next(){
            TreeNode cur =stack.pop();
            leftMostInStack(cur.right);
            return cur;
        }

        public boolean hasNext(){
            return !stack.isEmpty();
        }


        public TreeNode peek(){
            return stack.peek();
        }
    }
}
