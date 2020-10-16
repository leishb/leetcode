package com.leishb.leetcode.finwick;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CountRangeSum {


    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) return 0;
        long[] sum = new long[n];
        long[] copy = new long[n];
        sum[0] = copy[0] = nums[0];
        for (int i=1;i<n;i++){
            sum[i] = sum[i - 1] + nums[i];
            copy[i] = sum[i];
        }
        Arrays.sort(copy);
        int ans = 0;
        System.out.println(Arrays.toString(copy));
        FinwickTree tree = new FinwickTree(n);
        for(int i=0;i<n;i++){
            int leftBounder = searchLeft(copy, sum[i] - upper);
            int rightBounder = searchRight(copy, sum[i] - lower);
            if (leftBounder != -1 && rightBounder != -1){
                ans += tree.prefixSum(rightBounder + 1) - tree.prefixSum(leftBounder);
            }
            if (sum[i] >= lower && sum[i] <= upper) ans++;
            tree.update(Arrays.binarySearch(copy, sum[i]) + 1, 1);
        }
        return ans;
    }


    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length;
        if (n == 0) return 0;
        long[] sum = new long[n+1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(0L, 1);
        int ans = 0;
        for (int i=1;i<=n;i++){
            Map<Long, Integer> subMap = map.subMap(sum[i] - upper, true, sum[i] - lower, true);
            for (int v : subMap.values()) ans += v;
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }

    class FinwickTree{
        int[] sum;

        FinwickTree(int n){
            this.sum = new int[n + 1];
        }

        public int prefixSum(int i){
            int ans = 0;
            while (i > 0){
                ans += sum[i];
                i -= lowbit(i);
            }
            return ans;
        }


        public void update(int i, int delta){
            while (i < sum.length){
                sum[i] += delta;
                i += lowbit(i);
            }
        }

        private int lowbit(int i){
            return i & (-i);
        }
    }


    private int searchLeft(long[] nums, long k){
        int i = 0, j = nums.length- 1 , ans = -1;
        while (i<=j){
            int m = (i + j) / 2;
            if (nums[m] < k){
                i = m + 1;
            }else {
                ans = m;
                j = m - 1;
            }
        }
        return ans;
    }


    private int searchRight(long[] nums, long k){
        int i = 0, j = nums.length- 1 , ans = -1;
        while (i<=j){
            int m = (i + j) / 2;
            if (nums[m] <= k){
                ans = m;
                i = m + 1;
            }else {
                j = m - 1;
            }
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(countRangeSum(new int[]{-2, 5, 1}, -2, 2));
    }
}
