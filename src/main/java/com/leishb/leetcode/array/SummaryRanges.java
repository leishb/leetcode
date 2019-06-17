package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/6/14.
 */
public class SummaryRanges {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length==0){
            return ans;
        }
        int i=0;
        while (i<nums.length) {
            int j=i;
            while (j+1<nums.length && (long)nums[j+1]-(long)nums[j]<=1){
                j++;
            }
            if (nums[j]==nums[i]){
                ans.add(nums[i]+"");
            }else if (nums[j]>nums[i]){
                ans.add(nums[i]+"->"+nums[j]);
            }
            i=j+1;
        }
        return ans;
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }
        for (int i=0;i<nums.length;i++){
            int start = i;
            while (i+1<nums.length && (long)nums[i+1]-(long)nums[i] <= 1){
                i++;
            }
            if (nums[start]==nums[i]){
                ans.add(nums[i]+"");
            }else {
                ans.add(nums[start]+"->"+nums[i]);
            }
        }
        return ans;
    }
}
