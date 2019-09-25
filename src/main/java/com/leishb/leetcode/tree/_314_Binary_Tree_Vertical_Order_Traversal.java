package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/9/25.
 */
public class _314_Binary_Tree_Vertical_Order_Traversal {

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null) return ans;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.offer(root);
        q2.offer(0);
        while (!q1.isEmpty()){
            TreeNode cur = q1.poll();
            int k = q2.poll();
            min = Math.min(k, min);
            max = Math.max(k, max);
            map.putIfAbsent(k, new ArrayList<>());
            map.get(k).add(cur.val);
            if (cur.left!=null){
                q1.offer(cur.left);
                q2.offer(k-1);
            }
            if (cur.right!=null){
                q1.offer(cur.right);
                q2.offer(k+1);
            }
        }
        for (int i=min;i<=max;i++){
            ans.add(map.get(i));
        }
        return ans;
    }


    private void visit(TreeNode root, Map<Integer, List<Integer>> map, int k){
        if (root!=null){
            min = Math.min(k, min);
            max = Math.max(k, max);
            map.putIfAbsent(k, new ArrayList<>());
            map.get(k).add(root.val);
            visit(root.left, map, k-1);
            visit(root.right, map, k+1);
        }
    }
}
