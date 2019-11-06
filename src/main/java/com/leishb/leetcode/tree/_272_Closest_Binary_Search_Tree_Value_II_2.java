package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/31.
 */
public class _272_Closest_Binary_Search_Tree_Value_II_2 {


    /**
     * Accepted
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (search(root, target)){
            ans.add((int) target);
        }
        TreeNode greater = getGreater(root, target);
        TreeNode smaller = getSmaller(root, target);
        while (ans.size() < k){
            if (greater==null && smaller==null) break;
            if (greater==null){
                ans.add(smaller.val);
                smaller = getSmaller(root, smaller.val);
            }else if (smaller==null){
                ans.add(greater.val);
                greater = getGreater(root, greater.val);
            }else if (Math.abs(greater.val-target) < Math.abs(smaller.val - target)){
                ans.add(greater.val);
                greater = getGreater(root, greater.val);
            }else {
                ans.add(smaller.val);
                smaller = getSmaller(root, smaller.val);
            }
        }
        return ans;
    }


    private boolean search(TreeNode root, double target){
        TreeNode cur = root;
        while (cur!=null){
            if (cur.val == target) return true;
            if (cur.val > target){
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        return false;
    }


    private TreeNode getGreater(TreeNode root, double target){
        TreeNode ans = null;
        TreeNode cur = root;
        while (cur!=null){
            if(cur.val > target){
                ans = cur;
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        return ans;
    }


    private TreeNode getSmaller(TreeNode root, double target){
        TreeNode ans = null;
        TreeNode cur = root;
        while (cur!=null){
            if(cur.val < target){
                ans = cur;
                cur = cur.right;
            }else {
                cur = cur.left;
            }
        }
        return ans;
    }
}
