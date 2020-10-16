package com.leishb.leetcode.finwick;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReversePairs_2 {



    public int reversePairs(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(2 * (long) nums[i]);
        }
        Long[] arr = set.toArray(new Long[set.size()]);
        Arrays.sort(arr);
        SegmentTree root = buildTree(arr, 0, arr.length - 1);
        int ans = 0;
        update(root, 2 * (long)nums[nums.length - 1], 1);
        for (int i = nums.length - 2 ; i >= 0; i--){
            ans += queryCount(root, nums[i]);
            update(root, 2 * (long)nums[i], 1);
        }
        return ans;
    }


    class SegmentTree{
        SegmentTree left;
        SegmentTree right;
        long min;
        long max;
        int count;

        SegmentTree(long min, long max){
            this.min = min;
            this.max = max;
        }
    }


    public SegmentTree buildTree(Long[] arr, int start, int end){
        if (start == end) return new SegmentTree(arr[start], arr[end]);
        int mid = (end - start) / 2 + start;
        SegmentTree root = new SegmentTree(arr[start], arr[end]);
        root.left = buildTree(arr, start, mid);
        root.right = buildTree(arr, mid + 1, end);
        return root;
    }



    public void update(SegmentTree root, long val, int count){
        if (val < root.min || val > root.max) return;
        if (root.min == val && root.max == val) {
            root.count += count;
            return;
        }
        update(root.left, val, count);
        update(root.right, val, count);
        root.count = root.left.count + root.right.count;
    }


    public int queryCount(SegmentTree root, long min, long max){
        if (min > root.max || max < root.min) return 0;
        if (root.min >= min && root.max <= max){
            return root.count;
        }
        return queryCount(root.left, min, max) + queryCount(root.right, min, max);
    }


    public int queryCount(SegmentTree root,  long max){
        if (root == null || max <= root.min ) return 0;
        if (max > root.max) return root.count;
        return queryCount(root.left, max) + queryCount(root.right,  max);
    }
}
