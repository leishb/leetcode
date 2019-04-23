package com.leishb.leetcode.array;

import java.util.TreeSet;

/**
 * Created by me on 2019/4/23.
 */
public class ContainsDuplicate {


    /**
     * Accepted
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i=0;i<nums.length;i++){
            Long floor = set.floor((long)nums[i]+t);
            Long ceil = set.ceiling((long)nums[i]-t);
            if ((floor!=null && floor>=nums[i]) || (ceil!=null && ceil <=nums[i])){
                return true;
            }
            set.add((long)nums[i]);
            if (i>=k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }
}
