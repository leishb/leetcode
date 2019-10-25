package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/25.
 */
public class _366_Find_Leaves_of_Binary_Tree {


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        while (true){
            List<Integer> list = new ArrayList<>();
            Map<TreeNode, TreeNode> map = new HashMap<>();
            inorder(root, list, null, map);
            ans.add(list);
            if (map.containsKey(root)){
                break;
            }
            for (TreeNode key : map.keySet()){
                TreeNode p = map.get(key);
                if (p.left==key) p.left=null;
                if (p.right==key) p.right=null;
            }
        }
        return ans;
    }



    private void inorder(TreeNode root, List<Integer> list, TreeNode parent, Map<TreeNode, TreeNode> map){
        if (root==null) return;
        if (root.left==null && root.right==null){
            list.add(root.val);
            map.put(root, parent);
            return;
        }
        inorder(root.left, list, root, map);
        inorder(root.right, list, root, map);
    }


    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        while (root!=null){
            List<Integer> leaves = new ArrayList<>();
            if (isLeave(root, leaves)) root=null;
            ans.add(leaves);
        }
        return ans;
    }


    private boolean isLeave(TreeNode root, List<Integer> leaves){
        if (root.left==null && root.right==null){
            leaves.add(root.val);
            return true;
        }
        if (root.left!=null){
            if (isLeave(root.left, leaves)) root.left=null;
        }
        if (root.right!=null){
            if (isLeave(root.right, leaves)) root.right=null;
        }
        return false;
    }

    public List<List<Integer>> findLeaves3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        getHeight(root, ans);
        return ans;
    }


    private int getHeight(TreeNode root, List<List<Integer>> ans){
        if (root==null) return -1;
        int level = Math.max(getHeight(root.left, ans), getHeight(root.right, ans))+1;
        if (ans.size()==level){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        return level;
    }
}
