package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/8/16.
 */
public class _814_Binary_Tree_Pruning {

    /**
     * Accepted
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        serialize(root, map);
        Set<TreeNode> set = new HashSet<>();
        for (String key : map.keySet()){
            if (!key.contains("1")){
                set.addAll(map.get(key));
            }
        }
        if (set.contains(root)){
            return null;
        }
        preOrder(root, set);
        return root;
    }

    private void preOrder(TreeNode root, Set<TreeNode> set){
        if (root!=null){
            if (set.contains(root.left)){
                root.left = null;
            }
            if (set.contains(root.right)){
                root.right=null;
            }
            preOrder(root.left, set);
            preOrder(root.right, set);
        }
    }



    private String serialize(TreeNode root, Map<String, List<TreeNode>> map){
        if (root==null){
            return "NULL";
        }
        String s = root.val + serialize(root.left, map) + serialize(root.right, map);
        map.putIfAbsent(s, new ArrayList<>());
        map.get(s).add(root);
        return s;
    }


    /**
     * Accepted
     * @param root
     * @return
     * */
    public TreeNode pruneTree2(TreeNode root) {
        if (root==null){
            return null;
        }
        root.left = pruneTree2(root.left);
        root.right = pruneTree2(root.right);
        if (root.val==0 && root.left==null && root.right==null) return null;
        return root;
    }



    private boolean containsOne(TreeNode root){
        if (root==null)return false;
        boolean left = containsOne(root.left);
        boolean right = containsOne(root.right);
        if (!left) root.left=null;
        if (!right) root.right=null;
        return root.val==1 || left || right;
    }
}
