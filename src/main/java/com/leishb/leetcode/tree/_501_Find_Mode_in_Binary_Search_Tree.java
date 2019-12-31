package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/12/30.
 */
public class _501_Find_Mode_in_Binary_Search_Tree {


    int maxFreq = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public int[] findMode(TreeNode root) {
        inOrder(root);
        List<Integer> list = new ArrayList<>();
        for (int k : map.keySet()){
            if (map.get(k)==maxFreq){
                list.add(k);
            }
        }
        int[] ans = new int[list.size()];
        int k = 0;
        while (k<list.size()){
            ans[k] = list.get(k);
            k++;
        }
        return ans;
    }


    private void inOrder(TreeNode root){
        if (root==null) return;
        inOrder(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(root.val));
        inOrder(root.right);
    }


    public int[] findMode2(TreeNode root) {
        BSTIterator it = new BSTIterator(root);
        Stack<Integer> stack = new Stack<>();
        int max = 0, count = 0;
        Integer prev = null;
        while (it.hasNext()){
            TreeNode next = it.next();
            if (prev==null || next.val==prev){
                count++;
                if (count > max){
                    while (!stack.isEmpty())stack.pop();
                    max = count;
                }
            }else {
                count = 1;
            }
            if (count == max){
                stack.push(next.val);
            }
            prev = next.val;
        }
        int[] ans = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()){
            ans[i++] = stack.pop();
        }
        return ans;
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
    }

    Stack<Integer> stack = new Stack<>();
    Integer prev = null;
    int max = 0, count = 0;
    public int[] findMode3(TreeNode root) {
        transfer(root);
        int[] ans = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()){
            ans[i++] = stack.pop();
        }
        return ans;
    }


    private void transfer(TreeNode root){
        if (root==null) return;
        transfer(root.left);
        if (prev==null || root.val==prev){
            count++;
        }else {
            count = 1;
        }
        if (count > max){
            while (!stack.isEmpty()) stack.pop();
            max= count;
        }
        if (count==max){
            stack.push(root.val);
        }
        prev = root.val;
        transfer(root.right);
    }
}
