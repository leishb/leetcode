package com.leishb.leetcode.finwick;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountRangeSum2 {


    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) return 0;
        long[] sum = new long[n+1];
        Set<Long> set = new HashSet<>();
        set.add(0L);
        for (int i=0;i<n;i++){
            sum[i+1] = sum[i] + nums[i];
            set.add(sum[i + 1]);
        }
        Long[] arr = set.toArray(new Long[set.size()]);
        Arrays.sort(arr);
        SegmentTree root = buildTree(arr, 0, arr.length- 1);
        update(root, 0, 1);
        int count = 0;
        for (int i=1;i<=n;i++){
            count += queryCount(root, sum[i] - upper, sum[i] - lower);
            update(root, sum[i], 1);
        }
        return count;
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


    @Test
    public void test(){

    }
}
