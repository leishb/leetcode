package com.leishb.leetcode.favorite;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/6/21.
 */
public class CountSmallerWithBIT {

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

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        Arrays.sort(copy);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i=0;i<copy.length;i++){
            if (i==0||copy[i]!=copy[i-1]){
                ranks.put(copy[i], ++rank);
            }
        }
        FinwickTree tree = new FinwickTree(ranks.size());
        for (int i=nums.length-1;i>=0;i--){
            int rk = ranks.get(nums[i]);
            ans.add(tree.prefixSum(rk-1));
            tree.update(rk, 1);
        }
        Collections.reverse(ans);
        return ans;
    }


    @Test
    public void test(){
        System.out.println(countSmaller(new int[]{3,2,2,6,1}));
        System.out.println(countSmaller(new int[]{2,0,1}));
        System.out.println(countSmaller(new int[]{6,5}));
        System.out.println(countSmaller(new int[]{1,2,0}));
        System.out.println(countSmaller(new int[]{6,5,1,2}));
        System.out.println(countSmaller(new int[]{5,2,6,1}));
        System.out.println(countSmaller(new int[]{5,2,4,3}));
    }
}
