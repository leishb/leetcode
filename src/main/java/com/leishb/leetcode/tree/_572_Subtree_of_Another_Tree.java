package com.leishb.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/21.
 */
public class _572_Subtree_of_Another_Tree {


    public boolean isSubtree(TreeNode s, TreeNode t) {
        Set<String> set = new HashSet<>();
        serialize(s, set);
        String ts = serialize(t, new HashSet<>());
        return set.contains(ts);
    }


    private String serialize(TreeNode root, Set<String> set){
        if (root==null) return "N";
        String s = root.val + "," + serialize(root.left, set) + "," + serialize(root.right, set);
        set.add(s);
        return s;
    }




    public boolean isSubtree2(TreeNode s, TreeNode t){
        if (s==null && t==null) return true;
        if (s==null || t==null) return false;
        if (isSame(s, t)){
            return true;
        }
        return isSubtree2(s.left, t) || isSubtree2(s.right, t);
    }


    private boolean isSame(TreeNode s, TreeNode t){
        if (s==null && t==null) return true;
        if (s==null || t==null) return false;
        return s.val==t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
