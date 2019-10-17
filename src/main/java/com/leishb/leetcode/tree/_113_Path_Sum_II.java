package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/14.
 */
public class _113_Path_Sum_II {


    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        dfs(ans, new ArrayList<>(), root, sum);
        return ans;
    }

    private void dfs(List<List<Integer>> ans , List<Integer> list, TreeNode root, int sum){
        if (root.left==null && root.right==null){
            if (sum==root.val){
                list.add(root.val);
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        if (root.left!=null){
            List<Integer> copy = new ArrayList<>(list);
            copy.add(root.val);
            dfs(ans, copy, root.left, sum-root.val);
        }
        if (root.right!=null){
            List<Integer> copy = new ArrayList<>(list);
            copy.add(root.val);
            dfs(ans, copy, root.right, sum-root.val);
        }
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        pathSum(root, 1);
    }
}
