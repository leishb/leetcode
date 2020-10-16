package com.iqiyi.pay.config.test.arg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSmaller {


    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length==0) return ans;
        int[] copy = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i=0;i<nums.length;i++){
            if (i==0||copy[i] != copy[i-1]){
                map.put(copy[i], j++);
            }
        }
        SegmentTree root = buildTree(new int[j], 0, j - 1);
        for (int i=nums.length-1;i>=0;i--){
            int rank = map.get(nums[i]);
            if (rank==0) ans.add(0);
            else {
                ans.add(querySum(root, 0, rank - 1));
            }
            update(root, rank, 1);
        }
        Collections.reverse(ans);
        return ans;
    }


    //1 ,3 , 2, 3, 1
    //1 ,1 ,2, 3, 3
    public int reversePairs(int[] nums) {
        if (nums.length==0) return 0;
        int[] copy = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i=0;i<nums.length;i++){
            if (i==0||copy[i] != copy[i-1]){
                map.put(copy[i], j++);
            }
        }
        int ans = 0;
        SegmentTree root = buildTree(new int[j], 0, j - 1);
        for (int i=nums.length-1;i>=0;i--){
            int index = search(copy, nums[i]);
            System.out.println(index);
            if (index != -1){
                int r = map.get(copy[index]);
                ans += querySum(root, 0, r);
            }
            update(root, map.get(nums[i])  , 1);
        }
        return ans;
    }


    private int search(int[] nums, int k){
        int i = 0, j = nums.length-1, ans = -1;
        while (i<=j){
            int m = (i + j) / 2;
            long p = nums[m];
            if (p * 2 >= k){
                j = m - 1;
            }else {
                ans = m;
                i = m + 1;
            }
        }
        return ans;
    }



    class SegmentTree{
        SegmentTree left;
        SegmentTree right;
        int start;
        int end;
        int sum;
        SegmentTree(int start, int end, int sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }


    public SegmentTree buildTree(int[] arr, int start, int end){
        if (start == end) return new SegmentTree(start, end, arr[start]);
        int mid = (start + end) / 2;
        SegmentTree left = buildTree(arr, start, mid);
        SegmentTree right = buildTree(arr, mid + 1, end);
        SegmentTree root = new SegmentTree(start, end, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return root;
    }

    public int querySum(SegmentTree root, int start, int end){
        if (start > end) return  0;
        if (root.start == start && root.end == end) return root.sum;
        int mid = (root.start + root.end) / 2;
        if (end <= mid){
            return querySum(root.left, start, end);
        }else if (mid < start){
            return querySum(root.right, start, end);
        }
        return querySum(root.left, start, mid) + querySum(root.right, mid + 1, end);
    }


    public void update(SegmentTree root, int index, int delta){
        if (root.start == index && root.end == index){
            root.sum += delta;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid){
            update(root.left, index, delta);
        }else {
            update(root.right, index, delta);
        }
        root.sum = root.left.sum + root.right.sum;
    }


    private void reverse(int[] arr){
        int i = 0, j = arr.length - 1;
        while (i<=j){
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            i++;
            j--;
        }
    }


    public int reversePairs2(int[] nums) {
        if (nums.length==0) return 0;
        int[] copy = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        int ans = 0;
        FinwickTree tree = new FinwickTree(nums.length);
        for (int i=nums.length-1;i>=0;i--){
            int index = search(copy, nums[i]);
            if (index != -1){
                ans += tree.prefixSum(index + 1);
            }
            tree.update(Arrays.binarySearch(copy, nums[i]) + 1, 1);
        }
        return ans;
    }


    class FinwickTree{

        int[] sums;

        FinwickTree(int n){
            sums = new int[n+1];
        }

        public void update(int i, int delta){
            while (i<sums.length){
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        public int prefixSum(int i){
            int ans = 0;
            while (i>0){
                ans += sums[i];
                i -= lowbit(i);
            }
            return ans;
        }

        private int lowbit(int i){
            return i&(-i);
        }
    }


    @Test
    public void test(){
        System.out.println(reversePairs2(new int[]{6, 2, 1}));
//        System.out.println(search(new int[]{1, 2, 6}, 2));

    }
}
