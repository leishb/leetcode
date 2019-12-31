package com.leishb.leetcode.design;

/**
 * Created by me on 2019/12/2.
 */
public class _307_Range_Sum_Query_Mutable {


    SegmentTree root = null;

    public _307_Range_Sum_Query_Mutable(int[] nums) {
        if (nums.length==0) return;
        root = buildTree(nums, 0, nums.length-1);
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return query(root, i, j);
    }


    class SegmentTree{
        int start;
        int end;
        int sum;
        SegmentTree left;
        SegmentTree right;
        SegmentTree(int start, int end, int sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }


    private SegmentTree buildTree(int[] nums, int start, int end){
        if (start>end) return null;
        if (start==end){
            return new SegmentTree(start, end, nums[start]);
        }
        int mid = (start+end)/2;
        SegmentTree root = new SegmentTree(start, end, 0);
        root.left = buildTree(nums, start, mid);
        root.right = buildTree(nums, mid+1, end);
        if (root.left!=null) root.sum+=root.left.sum;
        if (root.right!=null) root.sum+=root.right.sum;
        return root;
    }

    private void update(SegmentTree root, int index, int val){
        if (root==null || root.start > index || root.end < index) return;
        if (root.start==root.end && root.start==index){
            root.sum = val;
            return;
        }
        update(root.left, index, val);
        update(root.right, index, val);
        int sum = 0;
        if (root.left!=null) sum+=root.left.sum;
        if (root.right!=null) sum+=root.right.sum;
        root.sum = sum;
    }


    private int query(SegmentTree root, int start, int end){
        if (start > end || root==null || root.end < start || root.start > end){
            return 0;
        }
        if (root.start >= start && root.end<=end){
            return root.sum;
        }
        int sum = 0;
        sum += query(root.left, start, end);
        sum += query(root.right, start, end);
        return sum;
    }
}
