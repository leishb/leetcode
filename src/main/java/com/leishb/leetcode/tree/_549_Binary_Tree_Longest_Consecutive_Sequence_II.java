package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/9.
 */
public class _549_Binary_Tree_Longest_Consecutive_Sequence_II {


    int max = 0;

    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }

    Map<TreeNode, Integer> inc = new HashMap<>();
    Map<TreeNode, Integer> des = new HashMap<>();

    private void helper(TreeNode root){
        if (root!=null){
            helper(root.left);
            helper(root.right);
            int curInc = 1;
            if (root.left!=null && root.val-root.left.val==1){
                curInc += inc.get(root.left);
            }
            if (root.right!=null && root.val-root.right.val==1){
                curInc = Math.max(curInc, 1+inc.get(root.right));
            }
            inc.put(root, curInc);
            int curDes = 1;
            if (root.left!=null && root.left.val-root.val==1){
                curDes = 1+des.get(root.left);
            }
            if (root.right!=null && root.right.val-root.val==1){
                curDes = Math.max(curDes, 1+des.get(root.right));
            }
            des.put(root, curDes);
            max = Math.max(max, inc.get(root)+des.get(root)-1);
            max = Math.max(max, des.get(root)+inc.get(root)-1);
        }
    }


    private int[] postOrder(TreeNode root){
        if (root==null) return new int[2];
        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);
        int inc = 1, des = 1;
        if (root.left!=null && root.val-root.left.val == 1){
            inc = 1 + left[0];
        }
        if (root.right!=null && root.val-root.right.val==1){
            inc = Math.max(inc, 1+right[0]);
        }
        if (root.left!=null && root.left.val-root.val==1){
            des = 1+ left[1];
        }
        if (root.right!=null && root.right.val-root.val==1){
            des = Math.max(des, 1+right[1]);
        }
        max = Math.max(max, inc+des-1);
        return new int[]{inc, des};
    }

    public int longestConsecutiveI(TreeNode root) {
        helperPostOrder(root);
        return max;
    }



    private int helperPostOrder(TreeNode root){
        if (root==null) return 0;
        int left = helperPostOrder(root.left);
        int right = helperPostOrder(root.right);
        int cur = 1;
        if (root.left!=null && root.left.val-root.val==1){
            cur = 1+left;
        }
        if (root.right!=null && root.right.val-root.val==1){
            cur = Math.max(cur, 1+right);
        }
        max = Math.max(max, cur);
        return cur;
    }


    private void dfs(TreeNode root, int cur, int  target){
        if (root==null) return;
        if (root.val==target){
            cur +=1;
        }else {
            cur = 1;
        }
        max = Math.max(max, cur);
        dfs(root.left, cur, root.val+1);
        dfs(root.right, cur, root.val+1);
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        longestConsecutive(root);
    }
}
