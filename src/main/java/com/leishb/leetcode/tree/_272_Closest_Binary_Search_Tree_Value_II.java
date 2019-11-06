package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/31.
 */
public class _272_Closest_Binary_Search_Tree_Value_II {


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
        int[] greater = getGreater(root, target);
        int[] smaller = getSmaller(root, target);
        while (ans.size() < k){
            while ((smaller[1]==0 ||  Math.abs(greater[0]-target) < Math.abs(smaller[0]-target))
                    && greater[1]!=0  && ans.size() < k){
                ans.add(greater[0]);
                greater = getGreater(root, greater[0]);
            }
            while ((greater[1]==0 ||   Math.abs(greater[0]-target) >= Math.abs(smaller[0]-target))
                   && smaller[1]!=0  && ans.size() < k){
                ans.add(smaller[0]);
                smaller = getSmaller(root, smaller[0]);
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



    private int[] getGreater(TreeNode root, double target){
        TreeNode cur = root;
        int ans = Integer.MAX_VALUE;
        boolean found = false;
        while (cur!=null){
            if (cur.val > target){
                ans = Math.min(ans, cur.val);
                found = true;
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        return new int[]{ans, found?1:0};
    }


    private int[] getSmaller(TreeNode root, double target){
        TreeNode cur = root;
        int ans = Integer.MIN_VALUE;
        boolean found = false;
        while (cur!=null){
            if (cur.val >= target){
                cur = cur.left;
            }else {
                found = true;
                ans = Math.max(ans, cur.val);
                cur = cur.right;
            }
        }
        return new int[]{ans, found?1:0};
    }
}
