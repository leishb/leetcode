package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2019/10/18.
 */
public class _333_Largest_BST_Subtree {

    public int largestBSTSubtree(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        helper(root, lists);
        Collections.sort(lists, (l1, l2)->l2.size()-l1.size());
        for (List<Integer> list : lists){
            int i = 1;
            for (;i<list.size();i++){
                if (list.get(i) <= list.get(i-1)){
                    break;
                }
            }
            if (i==list.size()) return list.size();
        }
        return 0;
    }



    private List<Integer> helper(TreeNode root, List<List<Integer>> lists){
        if (root==null) return new ArrayList<>();
        List<Integer> left = helper(root.left, lists);
        List<Integer> right= helper(root.right, lists);
        List<Integer> cur = new ArrayList<>(left);
        cur.add(root.val);
        cur.addAll(right);
        lists.add(cur);
        return cur;
    }


    TreeNode prev = null;
    int count = 0;
    int max = 0;

    private void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            if (prev!=null && root.val > prev.val){
                count +=1;
            }else {
                count = 1;
            }
            max = Math.max(count, max);
            prev = root;
            inOrder(root.right);
        }
    }


    public int largestBSTSubtree2(TreeNode root) {
        helper(root);
        return max;
    }

    private int[] helper(TreeNode root){
        if (root==null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 1};
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int cur = 0;
        if (left[3]==1 && right[3] == 1 &&root.val > left[1] && root.val < right[0]){
            max = Math.max(max, left[2]+right[2]+1);
            cur = 1;
        }
        return new int[]{Math.min(root.val, Math.min(left[0], right[0])), Math.max(root.val, Math.max(left[1], right[1]))
        , left[2]+right[2]+1, cur};
    }

}
