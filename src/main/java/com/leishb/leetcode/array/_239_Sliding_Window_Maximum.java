package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.TreeMap;

/**
 * Created by me on 2019/10/14.
 */
public class _239_Sliding_Window_Maximum {



    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        int[] ans = new int[nums.length-k+1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<k;i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        ans[0] = map.lastKey();
        for (int i=k;i<nums.length;i++){
            map.put(nums[i-k], map.get(nums[i-k])-1);
            if (map.get(nums[i-k])==0) map.remove(nums[i-k]);
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            ans[i-k+1] = map.lastKey();
        }
        return ans;
    }


    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length==0) return new int[0];
        int[] ans = new int[nums.length-k+1];
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i=0;i<k;i++){
            if (nums[i] >= max){
                max = nums[i];
                maxIndex = i;
            }
        }
        ans[0] = max;
        for (int i=k;i<nums.length;i++){
            if (nums[i] >= max){
                max = nums[i];
                maxIndex = i;
            }else if (maxIndex == i-k){
                max = nums[i];
                maxIndex = i;
                for (int j=i-k+1;j<=i;j++){
                    if (nums[j] >= max){
                        max = nums[j];
                        maxIndex = j;
                    }
                }
            }
            ans[i-k+1] = max;
        }
        return ans;
    }
    @Test
    public void test(){
        maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }
}
