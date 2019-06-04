package com.leishb.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/6/4.
 */
public class ArrayNesting {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int max = 0;
        boolean[] visisted = new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
            if (visisted[i]){
                continue;
            }
            Set<Integer> set = new HashSet<>();
            arrayNesting(nums, i, set, visisted);
            max = Math.max(max, set.size());
        }
        return max;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int arrayNesting2(int[] nums) {
        int max = 0;
        boolean[] visisted = new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
            if (visisted[i]){
                continue;
            }
            int count = 0;
            int cur = nums[i];
            do {
                count++;
                visisted[cur] = true;
                cur = nums[cur];
            }while (cur!=nums[i]);
            max = Math.max(max, count);
        }
        return max;
    }


    private void arrayNesting(int[] nums, int k, Set<Integer> set, boolean[] visisted) {
        if (set.contains(nums[k])){
            return;
        }
        visisted[k] = true;
        set.add(nums[k]);
        arrayNesting(nums, nums[k], set, visisted);
    }
}
