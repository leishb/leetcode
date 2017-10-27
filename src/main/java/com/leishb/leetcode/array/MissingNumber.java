package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/26.
 */
public class MissingNumber {


    @Test
    public void test(){
        Assert.assertTrue(missingNumber(new int[]{1,2,0})==3);
        Assert.assertTrue(missingNumber(new int[]{0,1,3})==2);
        Assert.assertTrue(missingNumber(new int[]{2,1,3})==0);


        Assert.assertTrue(firstMissingPositive(new int[]{1,2,0})==3);
        Assert.assertTrue(firstMissingPositive(new int[]{3,4,-1,1})==2);
        Assert.assertTrue(firstMissingPositive(new int[]{})==1);
        Assert.assertTrue(firstMissingPositive(new int[]{2,2,2})==1);
        Assert.assertTrue(firstMissingPositive(new int[]{2})==1);
        Assert.assertTrue(firstMissingPositive(new int[]{1})==2);


    }

    /**
     * Accepted
     * https://leetcode.com/problems/missing-number/
     * @param nums
     * @return
     */
    public int missingNum(int[] nums){
        int[] bits = new int[nums.length+1];
        for (int num:nums){
            bits[num]=1;
        }
        for (int i=0;i<bits.length;i++){
            if (bits[i]==0){
                return i;
            }
        }
        return -1;
    }


    /**
     * Accepted<br></br>
     * 查找从0开始的缺失数据<br></br>
     * https://leetcode.com/problems/missing-number/
     * @see com.leishb.leetcode.math.SetMismatch#findErrorNums7(int[])
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums){
        for (int i=0;i<nums.length;i++){
            while (nums[i]<nums.length && nums[i]!=nums[nums[i]]) {//从0开始，所以nums[nums[i]]
                swap(nums, i, nums[i]);
            }
        }
        int index = -1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i){
                 index =i;
            }
        }
        return index==-1 ? nums.length : index;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * Accepted<br></br>
     * 查找缺失的正数<br>
     * <li>1、数组值越界的情况
     * <li>2、空数组的情况
     * <li>3、{1}的情况，应该返回2 <br></br>
     * https://leetcode.com/problems/first-missing-positive/
     * @see this#missingNumber(int[])
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums.length==0){
            return 1;
        }
        for (int i=0;i<nums.length;i++){
            while(nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[nums[i]-1]){//从1开始，所以nums[nums[i]-1]
                swap(nums, i, nums[i]-1);
            }
        }
        int index = -1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i+1){
                index = i+1;
                break;
            }
        }
        return index==-1 ? nums.length+1 : index;
    }
}
