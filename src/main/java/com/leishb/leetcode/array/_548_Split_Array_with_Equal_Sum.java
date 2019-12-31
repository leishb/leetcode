package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/22.
 */
public class _548_Split_Array_with_Equal_Sum {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean splitArray(int[] nums) {
        if (nums.length<7) return false;
        int[] sums = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            sums[i+1] = sums[i] + nums[i];
        }
        for (int j=3;j<nums.length-3;j++){
            Set<Integer> set = new HashSet<>();
            for (int i=1;i<j-1;i++){
                if (sums[i] == sums[j]-sums[i+1]){
                    set.add(sums[i]);
                }
            }
            for (int k=j+2;k<nums.length-1;k++){
                if (sums[k]-sums[j+1] == sums[nums.length] - sums[k+1] && set.contains(sums[k]-sums[j+1])){
                    return true;
                }
            }
        }
        return false;
    }



    @Test
    public void test(){
        Assert.assertTrue(splitArray(new int[]{1,2,1,2,1,2,1}));
        Assert.assertTrue(splitArray(new int[]{1,2,1,3,0,0,2,2,1,3,3}));
    }
}
