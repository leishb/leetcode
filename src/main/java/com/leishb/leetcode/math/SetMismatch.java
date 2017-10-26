package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/10/18.
 */
public class SetMismatch {

    @Test
    public void test(){
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));


        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums2(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));


        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums3(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));

        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums4(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));

        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));

        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums5(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));

        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums6(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));


        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{2,2}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{1,2,2,4}), new int[]{2,3}));
        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{1,1,2,3}), new int[]{1,4}));
        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{2,2,3,4}), new int[]{2,1}));
        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{1,2,3,3,4,5,6,8}), new int[]{3,7}));
        Assert.assertTrue(arrayEquals(findErrorNums7(new int[]{1,3,4,5,6,6,7,8}), new int[]{6,2}));
    }


    /**
     * Accepted
     * using sort
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int a = 0;
        int b = 0;
        for (int i=0;i<nums.length-1;i++){
            if (nums[i+1] == nums[i]){
                a = nums[i];
            }
            if (nums[i+1]-nums[i]==2){
                b = nums[i]+1;
            }
        }
        if (nums[0]!=1){
            b = 1;
        }
        if (nums[nums.length-1]!=nums.length){
            b = nums.length;
        }
        return new int[]{a,b};
    }

    /**
     * using extra space
     * @param nums
     * @return
     */
    public int[] findErrorNums2(int[] nums) {
        int[] arr = new int[nums.length+1];
        for (int i=0;i<nums.length;i++){
            arr[nums[i]] = arr[nums[i]] + 1;
        }
        int dup =0;
        int missing = 0;
        for (int i=1;i<arr.length;i++){
            if (arr[i]==0){
                missing = i;
            }
            if (arr[i]==2){
                dup = i;
            }
        }
        return new int[]{dup, missing};
    }


    /**
     * using map
     * @param nums
     * @return
     */
    public int[] findErrorNums3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums){
            if (map.containsKey(num)){
                map.put(num , map.get(num)+1);
            }else {
                map.put(num, 1);
            }
        }
        int dup = 0;
        int missing = 0;
        for (int i=1;i<nums.length+1;i++){
            if (!map.containsKey(i)){
                missing = i;
            }else if (map.get(i) == 2){
                dup = i;
            }
        }
        return new int[]{dup, missing};
    }


    /**
     * using sum
     * @param nums
     * @return
     */
    public int[] findErrorNums4(int[] nums) {
        long sum = nums.length * (nums.length+1)/2;
        Set<Integer> set = new HashSet<Integer>();
        int dup = 0;
        int missing = 0;
        for (int num : nums){
            sum -= num;
            if (set.contains(num)){
                dup = num;
            }
            set.add(num);
        }
        missing = (int)sum + dup;
        return new int[]{dup, missing};
    }


    public int[] findErrorNums5(int[] nums) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                result[0] = nums[i];
                result[1] = i + 1;
                break;
            }
        }

        return result;
    }

    /**
     *
     * using constant space
     * @param nums
     * @return
     */
    public int[] findErrorNums6(int[] nums) {
        int dup=0, missing=0;
        for (int num : nums){
            if (nums[Math.abs(num)-1] < 0){
                dup = Math.abs(num);
            }else {
                nums[Math.abs(num)-1] *= -1;
            }
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]>0){
                missing = i+1;
            }
        }
        return new int[]{dup, missing};
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * using array indexing
     * @param nums
     * @return
     */
    public int[] findErrorNums7(int[] nums){
        for (int i=0;i<nums.length;i++){
            while (nums[i]!=nums[nums[i]-1]){
                swap(nums, i, nums[i]-1);
            }
        }
        int dup = 0;
        int missing = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                dup = nums[i];
                missing = i + 1;
                break;
            }
        }
        return new int[]{dup,missing};
    }



    private boolean arrayEquals(int[] a, int[] b){
        for (int i=0;i<a.length;i++){
            if (a[i]!=b[i]){
                return false;
            }
        }
        return true;
    }
}
