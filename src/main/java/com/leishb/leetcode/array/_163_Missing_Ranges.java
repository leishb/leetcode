package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2020/1/8.
 */
public class _163_Missing_Ranges {


    /**
     * Accepted
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums==null || nums.length==0){
            res.add(formRange(lower, upper));
            return res;
        }
        if (lower<nums[0]){
            res.add(formRange(lower, nums[0]-1));
        }
        for (int i=0;i<nums.length-1;i++){
            if (nums[i]!=nums[i+1] && nums[i]+1!=nums[i+1]){
                int l = nums[i]+1;
                int r = nums[i+1]-1;
                if (r<lower || l>upper) continue;
                res.add(formRange(Math.max(l, lower), Math.min(r, upper)));
            }
        }
        if (upper>nums[nums.length-1]){
            res.add(formRange(nums[nums.length-1]+1, upper));
        }
        return res;
    }


    private String formRange(int lower, int higher){
        if (lower==higher){
            return String.valueOf(lower);
        }
        return String.valueOf(lower)+"->"+higher;
    }
}
