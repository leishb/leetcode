package com.leishb.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/4.
 */
public class _663_Equal_Tree_Partition {

    int zeros = 0;

    /**
     * Accepted
     * @param root
     * @return
     */
    public boolean checkEqualTree(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        int total = dfs(root, set);
        if (total==0)return zeros>1;
        return total%2==0 && set.contains(total/2);
    }

    private int dfs(TreeNode root, Set<Integer> set){
        if (root==null) return 0;
        int left = dfs(root.left, set);
        int right = dfs(root.right, set);
        int sum = left + right + root.val;
        set.add(sum);
        if (sum==0) zeros++;
        return sum;
    }
}
