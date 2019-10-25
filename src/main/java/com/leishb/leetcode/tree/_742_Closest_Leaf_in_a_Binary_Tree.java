package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/10/18.
 */
public class _742_Closest_Leaf_in_a_Binary_Tree {

    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        inOrder(root, parents, k, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        Set<TreeNode> set = new HashSet<>();
        set.add(node);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.left==null && cur.right==null){
                return cur.val;
            }
            if (cur.left!=null && !set.contains(cur.left)){
                set.add(cur.left);
                queue.offer(cur.left);
            }
            if (cur.right!=null && !set.contains(cur.right)){
                set.add(cur.right);
                queue.offer(cur.right);
            }
            if (parents.getOrDefault(cur, null)!=null && !set.contains(parents.get(cur))){
                set.add(parents.get(cur));
                queue.offer(parents.get(cur));
            }
        }
        return 0;
    }

    TreeNode node = null;

    private void inOrder(TreeNode root , Map<TreeNode, TreeNode> map, int k,  TreeNode parent){
        if (root!=null){
            inOrder(root.left, map, k,  root);
            if (parent!=null){
                map.put(root, parent);
            }
            if (k==root.val){
                node = root;
            }
            inOrder(root.right, map, k,  root);
        }
    }
}
