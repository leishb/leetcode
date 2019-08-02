package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/8/1.
 */
public class _863_All_Nodes_Distance_K_in_Binary_Tree {


    /**
     * Accepted
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        inOrder(root, null);
        int t = target.val;
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(t);
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                int n = queue.poll();
                set.add(n);
                for (int j : graph.getOrDefault(n, new ArrayList<>())){
                    if (!set.contains(j)){
                        queue.offer(j);
                    }
                }
                if (K==0){
                    ans.add(n);
                }
            }
            K--;
        }
        return ans;
    }


    Map<Integer, List<Integer>> graph = new HashMap<>();

    private void inOrder(TreeNode root, TreeNode parent){
        if (root==null){
            return;
        }
        if (parent!=null){
            graph.putIfAbsent(root.val, new ArrayList<>());
            graph.get(root.val).add(parent.val);
            graph.putIfAbsent(parent.val, new ArrayList<>());
            graph.get(parent.val).add(root.val);
        }
        inOrder(root.left, root);
        inOrder(root.right, root);
    }
}
