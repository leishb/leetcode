package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(subsets2(new int[]{1,2,3,4,5}));
        System.out.println(subsetsWithDup(new int[]{1,2,3,4,5}));
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
        System.out.println(subsetsWithDup2(new int[]{1,2,2}));
        System.out.println(subsetsWithDup(new int[]{4,4,4,1,4}));
        System.out.println(subsetsWithDup2(new int[]{4,4,4,1,4}));
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


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        for (int i=0;i<nums.length;i++){
            int preN = ret.size();
            for (int j=0;j<preN;j++){
                List<Integer> cur = ret.get(j);
                List<Integer> copy = new ArrayList<>(cur);
                copy.add(nums[i]);
                ret.add(copy);
            }
        }
        return ret;
    }

    /**
     *1,2,2
     [2],
     [1],
     [1,2,2],
     [2,2],
     [1,2],
     []

     Accepted
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        List<Integer> list = new ArrayList<>();
        helperWithDup(nums, 0, list, ret);
        return ret;
    }


    private void helperWithDup(int[] nums, int start, List<Integer> list, List<List<Integer>> ret){
        for (int i=start;i<nums.length;i++){
            if (i>start &&nums[i]==nums[i-1]){
                continue;
            }
            List<Integer> copy = new ArrayList<>(list);
            copy.add(nums[i]);
            ret.add(copy);
            helperWithDup(nums, i+1, copy, ret);
        }
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        for (int i=0;i<nums.length;){
            int count = 0;
            while (count+i<nums.length && nums[i+count]==nums[i]){
                count++;
            }
            int preN = ret.size();
            for (int j=0;j<preN;j++){
                List<Integer> cur = ret.get(j);
                List<Integer> copy = new ArrayList<>(cur);
                for (int k=0;k<count;k++){
                    copy.add(nums[i]);
                    ret.add(new ArrayList<>(copy));
                }
            }
            i += count;
        }
        return ret;
    }
}
