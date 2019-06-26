package com.leishb.leetcode.favorite;

/**
 * Created by me on 2019/6/19.
 */
@com.leishb.leetcode.tag.SegmentTree
public class NumArrayT {

    class SegmengTreeNode{
        int start;
        int end;
        int sum;
        SegmengTreeNode left;
        SegmengTreeNode right;

        SegmengTreeNode(int start, int end, int sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }

    private SegmengTreeNode root;

    public NumArrayT(int[] nums) {
       if (nums.length >0){
           root = buidTree(0, nums.length-1, nums);
       }
    }


    private SegmengTreeNode buidTree(int start, int end, int[] nums){
        if (start==end){
            return new SegmengTreeNode(start, end, nums[start]);
        }
        int mid = (start+end)/2;
        SegmengTreeNode left = buidTree(start, mid, nums);
        SegmengTreeNode right = buidTree(mid+1, end , nums);
        SegmengTreeNode node =  new SegmengTreeNode(start, end, left.sum+right.sum);
        node.left = left;
        node.right = right;
        return node;
    }

    public void update(int i, int val) {
        updateTree(root, i, val);
    }


    private void updateTree(SegmengTreeNode root, int index, int val){
        if (root==null){
            return;
        }
        if (index == root.start && index == root.end){
            root.sum = val;
            return;
        }
        int mid = (root.start+root.end)/2;
        if (index<=mid){
            updateTree(root.left, index, val);
        }else {
            updateTree(root.right, index, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }


    private int sumRange(SegmengTreeNode root , int i, int j) {
        if (root == null){
            return 0;
        }
        if (i==root.start && j == root.end){
            return root.sum;
        }
        int mid = (root.start+root.end)/2;
        if (j <= mid){
            return sumRange(root.left, i, j);
        }else if (i > mid){
            return sumRange(root.right, i, j);
        }else {
            return sumRange(root.left, i, mid) + sumRange(root.right, mid+1, j);
        }
    }


    public static void main(String[] args){
        NumArrayT arr = new NumArrayT(new int[]{1,3,5});
        System.out.println(arr.sumRange(0,2));
        arr.update(1,2);
        System.out.println(arr.sumRange(0,2));
    }
}
