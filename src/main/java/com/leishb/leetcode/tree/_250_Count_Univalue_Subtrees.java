package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2019/10/12.
 */
public class _250_Count_Univalue_Subtrees {

    public int countUnivalSubtrees(TreeNode root) {
        Map<TreeNode, Set<Integer>> map = new HashMap<>();
        helper(root, map);
        int count = 0;
        for (Set<Integer> set : map.values()){
            if (set.size()==1) count++;
        }
        return count;
    }


    private Set<Integer> helper(TreeNode root,Map<TreeNode, Set<Integer>> map){
        Set<Integer> set = new HashSet<>();
        if (root!=null){
            set.add(root.val);
            set.addAll(helper(root.left , map));
            set.addAll(helper(root.right, map));
            map.put(root, set);
        }
        return set;
    }


    int count = 0;

    public int countUnivalSubtrees2(TreeNode root) {
        if (root==null)return 0;
        helper(root);
        return count;
    }


    private boolean helper(TreeNode root){
        if (root.left==null && root.right==null){
            count++;
            return true;
        }
        boolean uniq = true;
        if (root.left!=null){
            uniq = helper(root.left) && root.val==root.left.val;
        }
        if (root.right!=null){
            uniq = helper(root.right) && uniq && root.val==root.right.val;
        }
        if (uniq) count++;
        return uniq;
    }
}
