package com.leishb.leetcode.favorite;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/6/25.
 */
public class CountBiggerWithBIT {

    class FenwickTree{

        int[] sums;

        public FenwickTree(int n){
            sums = new int[n+1];
        }

        public void update(int i, int delta){
            while (i<sums.length){
                sums[i] += delta;
                i = i+lowbit(i);
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

        public int rangeSum(int i, int j){
            return prefixSum(j)-prefixSum(i);
        }

        private int lowbit(int i){
            return i&-i;
        }
    }



    public List<Integer> countBigger(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i=0;i<copy.length;i++){
            if (i==0||copy[i]!=copy[i-1]){
                ranks.put(copy[i], ++rank);
            }
        }
        List<Integer> ans = new ArrayList<>();
        FenwickTree tree = new FenwickTree(ranks.size());
        for (int i=0;i<nums.length;i++){
            int rk = ranks.get(nums[i]);
            ans.add(tree.rangeSum(rk-1, ranks.size()));
            tree.update(rk, 1);
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(countBigger(new int[]{5,7,5,6,4,7}));
    }
}
