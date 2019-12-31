package com.leishb.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/28.
 */
public class _1261_Find_Elements_in_a_Contaminated_Binary_Tree {



    TreeNode root ;

    Set<Integer> set ;

    public _1261_Find_Elements_in_a_Contaminated_Binary_Tree(TreeNode root) {
        this.root = root;
        this.set = new HashSet<>();
        dfs(root, 0, set);
    }



    private void dfs(TreeNode root, int val, Set<Integer> set){
        if (root==null) return;
        root.val = val;
        set.add(val);
        dfs(root.left, 2*val+1, set);
        dfs(root.right, 2*val+2, set);
    }

    public boolean find(int target) {
        return set.contains(target);
    }
}
