package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/9/18.
 */
public class _1110_Delete_Nodes_And_Return_Forest {

    /**
     * Accepted
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int k : to_delete){
            set.add(k);
        }
        Map<Integer, TreeNode> map = new HashMap<>();
        map.put(root.val, root);
        visit(root, null, set, map);
        res.addAll(map.values());
        return res;
    }


    private void visit(TreeNode root, TreeNode parent, Set<Integer> set, Map<Integer, TreeNode> map){
        if (root!=null){
            if (set.contains(root.val)){
                map.remove(root.val);
                if (parent!=null && parent.left==root){
                    parent.left=null;
                }
                if (parent!=null && parent.right==root){
                    parent.right=null;
                }
                if(root.left!=null){
                    map.put(root.left.val, root.left);
                }
                if(root.right!=null){
                    map.put(root.right.val, root.right);
                }
            }
            visit(root.left, root, set, map);
            visit(root.right, root, set, map);
        }
    }



    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int k : to_delete){
            set.add(k);
        }
        helper(root, true, set, res);
        return res;
    }


    private TreeNode helper(TreeNode root, boolean isRoot, Set<Integer> set, List<TreeNode> res){
        if (root==null)return null;
        boolean deleted = set.contains(root.val);
        if (isRoot && !deleted){
            res.add(root);
        }
        root.left = helper(root.left, deleted, set, res);
        root.right = helper(root.right, deleted, set, res);
        return deleted?null:root;
    }
}
