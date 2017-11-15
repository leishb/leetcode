package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2017/11/14.
 */
public class MajorityElements {

    @Test
    public void test(){
        System.out.println(majorityElement2(new int[]{1,2,2,3,2,1,1,3}));
        System.out.println(majorityElement2(new int[]{1,1,1,1}));

        System.out.println(majorityElement(new int[]{1,2,2,3,2,1,1,3}));
        System.out.println(majorityElement(new int[]{1,1,1,1}));

        System.out.println(majorityElement(new int[]{1,2,2,3,2,1,1,3}, 3));
        System.out.println(majorityElement(new int[]{1,1,1,1}, 3));
    }


    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length==0){
            return ret;
        }
        int count1=0, count2=0, candidate1=0, candidate2=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==candidate1){
                count1++;
            }else if (nums[i]==candidate2){
                count2++;
            }else if (count1==0) {
                candidate1= nums[i];
            }else if (count2==0){
                candidate2=nums[i];
            } else {
                count1--;
                count2--;
            }
        }
        count1=0;
        count2=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==candidate1){
                count1++;
            }
            if (nums[i]==candidate2){
                count2++;
            }
        }
        if (count1>nums.length/3){
            ret.add(candidate1);
        }
        if (count2>nums.length/3 && candidate2 != candidate1){
            ret.add(candidate2);
        }
        return ret;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length==0){
            return ret;
        }
        int count1=0, count2=0, candidate1=0, candidate2=0;
        for (int i=0;i<nums.length;i++){
            if ((count1==0 || nums[i]==candidate1) && nums[i]!=candidate2){
                candidate1= nums[i];
                count1++;
            }else if (count2==0 || nums[i]==candidate2){
                candidate2=nums[i];
                count2++;
            }else {
                count1--;
                count2--;
            }
        }
        count1=0;
        count2=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==candidate1){
                count1++;
            }
            if (nums[i]==candidate2){
                count2++;
            }
        }
        if (count1>nums.length/3){
            ret.add(candidate1);
        }
        if (count2>nums.length/3 && candidate2 != candidate1){
            ret.add(candidate2);
        }
        return ret;
    }

    public List<Integer> majorityElement(int[] nums, int k) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length==0 || k<2){
            return ret;
        }
        int[] counts = new int[k-1];
        int[] candidates = new int[k-1];

        for (int i=0;i<nums.length;i++){
            boolean settle = false;
            for (int j=0;j<k-1;j++){
                if (candidates[j]==nums[i]){
                    counts[j]+=1;
                    settle=true;
                    break;
                }
            }
            if (settle){
                continue;
            }
            for (int j=0;j<k-1;j++){
                if (counts[j]==0){
                    counts[j]=1;
                    candidates[j]=nums[i];
                    settle=true;
                    break;
                }
            }
            if (settle){
                continue;
            }
            for (int j=0;j<k-1;j++){
                counts[j]=counts[j]>0 ? counts[j]-1 : 0;
            }
        }
        Arrays.fill(counts, 0);
        for (int i=0;i<nums.length;i++) {
            for (int j = 0; j < k - 1; j++) {
                if (nums[i]==candidates[j]){
                    counts[j]++;
                    break;
                }
            }
        }
        for (int i=0;i<k-1;i++){
            if (counts[i]>nums.length/k){
                ret.add(candidates[i]);
            }
        }
        return ret;
    }
}
