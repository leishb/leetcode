package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2017/10/26.
 */
@DFS
@BackTracking
public class Subsets {

    @Test
    public void test(){
        System.out.println(subsets(new int[]{1,2,3,4,5}));
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ret.add(new ArrayList<Integer>());
        List<Integer> list = new ArrayList<Integer>();
        helper(nums, 0, list, ret);
        return ret;
    }


    private void helper(int[] nums, int start, List<Integer> list, List<List<Integer>> ret){
        for (int i=start; i<nums.length;i++){
            List<Integer> copy = new ArrayList<Integer>(list);
            copy.add(nums[i]);
            ret.add(copy);
            helper(nums, i+1, copy, ret);
        }
    }
}
