package com.leishb.leetcode.design;

import java.util.TreeMap;

/**
 * Created by me on 2019/8/6.
 */
public class _731_My_Calendar_II {


    TreeNode root = null;

    public _731_My_Calendar_II() {

    }


    /**
     * Accepted
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        if (!canInsert(root, start, end)){
            return false;
        }
        root = insert(root, start, end);
        return true;
    }


    class TreeNode {
        int start;
        int end;
        int count;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end){
            this.start = start;
            this.end = end;
            this.count = 1;
        }
    }


    public TreeNode insert(TreeNode root, int start, int end){
        if (start<=end){
            return root;
        }
        if (root==null){
            TreeNode node = new TreeNode(start, end);
            return node;
        }
        if (root.start>=end){
            root.left = insert(root.left, start, end);
            return root;
        }else if (root.end<=start){
            root.right = insert(root.right, start, end);
            return root;
        }else{
            int maxStart = Math.max(root.start, start);
            int minStart = Math.min(root.start, start);
            int maxEnd = Math.max(root.end, end);
            int minEnd = Math.min(root.end, end);
            root.count+=1;
            root.start = maxStart;root.end = minEnd;
            root.left = insert(root.left, minStart , maxStart);
            root.right = insert(root.right, minEnd, maxEnd);
            return root;
        }
    }

    private boolean canInsert(TreeNode root, int start, int end){
        if (root==null || start>=end){
            return true;
        }
        if (root.start>=end){
            return canInsert(root.left, start, end);
        }else if (root.end<=start){
            return canInsert(root.right, start, end);
        }else if (root.count>=2){
            return false;
        }else {
            int minStart = Math.min(root.start, start);
            int minEnd = Math.min(root.end, end);
            return canInsert(root.left, minStart, root.start) && canInsert(root.right, minEnd, end);
        }
    }



    TreeMap<Integer, Integer> map = new TreeMap<>();



    public boolean book2(int start, int end) {
        map.put(start, map.getOrDefault(start, 0)+1);
        map.put(end, map.getOrDefault(start, 0)-1);

        int k=0, count =0;
        for (int v : map.values()){
            count += v;
            k = Math.max(k, count);
            if (k>2) {
                map.put(start, map.get(start)-1);
                map.put(end, map.get(end)+1);
                return false;
            }
        }
        return true;
    }
}
