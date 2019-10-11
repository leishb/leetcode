package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/9/27.
 */
public class _987_Vertical_Order_Traversal_of_a_Binary_Tree {


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Map<Integer, List<Integer>>> map = new HashMap();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList();
        Queue<int[]> idq = new LinkedList();
        queue.offer(root);
        idq.offer(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] cur = idq.poll();
            int x = cur[0], y = cur[1];
            TreeNode node = queue.poll();
            min = Math.min(min, cur[0]);
            max = Math.max(max, cur[0]);

            map.putIfAbsent(x, new LinkedHashMap());
            map.get(x).putIfAbsent(y, new ArrayList());
            map.get(x).get(y).add(node.val);
            if (node.left !=null) {
                queue.offer(node.left);
                idq.offer(new int[]{x-1, y-1});
            }
            if (node.right!=null) {
                queue.offer(node.right);
                idq.offer(new int[]{x+1, y-1});
            }
        }

        List<List<Integer>> ans = new ArrayList();
        for (int i=min;i<=max ;i++ ) {
            List<Integer> list = new ArrayList();
            for (List<Integer> v: map.get(i).values()) {
                Collections.sort(v);
                list.addAll(v);
            }
            ans.add(list);
        }
        return ans;
    }

    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        TreeMap<Integer, TreeMap<Integer, Queue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        for (TreeMap<Integer, Queue<Integer>> v : map.values()){
            List<Integer> list = new ArrayList<>();
            for (Queue<Integer> vs : v.values()){
                while (!vs.isEmpty()){
                    list.add(vs.poll());
                }
            }
            ans.add(list);
        }
        return ans;
    }


    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, Queue<Integer>>> map){
        if (root==null) return;
        map.putIfAbsent(x, new TreeMap<>());
        map.get(x).putIfAbsent(y, new PriorityQueue<>());
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x-1, y+1, map);
        dfs(root.right, x+1, y+1, map);
    }

}
