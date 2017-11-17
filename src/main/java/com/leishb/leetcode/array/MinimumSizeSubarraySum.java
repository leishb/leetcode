package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/6.
 */
@BinarySearch
@TwoPointers
public class MinimumSizeSubarraySum {

    @Test
    public void test(){
        Assert.assertTrue(minSubArrayLen(11, new int[]{1,2,3,4,5})==3);
        Assert.assertTrue(minSubArrayLen(16, new int[]{1,2,3,4,5})==0);

        Assert.assertTrue(minSubArrayLen2(11, new int[]{1,2,3,4,5})==3);
        Assert.assertTrue(minSubArrayLen2(16, new int[]{1,2,3,4,5})==0);
        Assert.assertTrue(minSubArrayLen2(5, new int[]{1,2,3,4,5})==1);


        Assert.assertTrue(minSubArrayLen3(11, new int[]{1,2,3,4,5})==3);
        Assert.assertTrue(minSubArrayLen3(16, new int[]{1,2,3,4,5})==0);
        Assert.assertTrue(minSubArrayLen3(5, new int[]{1,2,3,4,5})==1);
        Assert.assertTrue(minSubArrayLen3(213, new int[]{12, 28, 83, 4, 25,26,25,2,25,25,25,12})==8);
    }


    /**
     * Accepted
     * @param s
     * @param nums
     * @return
     */
    @TwoPointers
    public int minSubArrayLen2(int s, int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int i=0, j=0, sum=0, min =Integer.MAX_VALUE;
        while (j<nums.length){
            sum += nums[j++];
            while (sum>=s){
                min = Math.min(min, j-i);
                sum -= nums[i++];
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * Accepted
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int low = 0;
        int high = nums.length;
        if (high==0){
            return 0;
        }
        int[] sums = new int[nums.length+1];
        sums[0]=0;
        for (int i=0;i<nums.length;i++){
            sums[i+1] = sums[i]+nums[i];
        }
        int result = high+1;
        while (low<=high){
            int mid = (low+high)/2;
            boolean midSum = false;
            for (int i=0;i+mid-1<nums.length;i++){
                if (sums[i+mid]-sums[i] >=s){
                    midSum = true;
                    result = mid;
                }
            }
            if (midSum){
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        return result==nums.length+1 ? 0 : result;
    }


    private boolean windowExist(int size, int[] nums, int s){
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            if (i>=size){
                sum -= nums[i-size];
            }
            sum+=nums[i];
            if (sum>=s){
                return true;
            }
        }
        return false;
    }


    /**
     * Wrong
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen3(int s, int[] nums) {
        int res = div(nums, 0, nums.length-1, s);
        return res==Integer.MAX_VALUE ? 0 : res;
    }


    private int div(int[] nums, int start, int end, int s){
        if (start==end){
            if (nums[start]>=s){
                return 1;
            }
            return Integer.MAX_VALUE;
        }
        int mid=(start+end)/2;
        int left = div(nums, start, mid, s);
        int right = div(nums, mid+1, end, s);
        int mk = Integer.MAX_VALUE;
        int i=mid;
        int j=mid+1;
        int sum=nums[i]+nums[j];
        while (i>=start && j<=end){
            if (sum>=s){
                mk = Math.min(mk, j-i+1);
                break;
            }
            if (i==start && j==end){
                break;
            }else if (i == start){
                j++;
                sum +=nums[j];
            }else if (j==end){
                i--;
                sum += nums[i];
            }else {
                if (nums[i-1]>=nums[j+1]){
                    i--;
                    sum += nums[i];
                }else {
                    j++;
                    sum += nums[j];
                }
            }
        }
        return Math.min(left ,Math.min(right, mk));
    }
}
