package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/15.
 */
@TwoPointers
public class SubArrayProductLessThanK {

    @Test
    public void test(){
        Assert.assertTrue(countOfLessThanK2(new int[]{10,5,2,6}, 100)==8);
        Assert.assertTrue(countOfLessThanK2(new int[]{1,1,1}, 1)==0);
        Assert.assertTrue(countOfLessThanK2(new int[]{10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19)==18);

        Assert.assertTrue(countOfLessThanK3(new int[]{10,5,2,6}, 100)==8);
        Assert.assertTrue(countOfLessThanK3(new int[]{1,1,1}, 1)==0);
        Assert.assertTrue(countOfLessThanK3(new int[]{10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19)==18);
    }


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int countOfLessThanK2(int[] nums, int k){
        int count=0;
        int j=0;
        int p=1;
        for(int i=0;i<nums.length;i++){
            p *= nums[i];
            while (p>=k && j<=i){
                p/=nums[j++];
            }
            count += i-j+1;
        }
        return count;
    }



    public int countOfLessThanK3(int[] nums, int k){

        return div(nums, 0, nums.length-1, k);
    }

    private int div(int[] nums, int start, int end, int k){
        if (start==end){
            if (nums[start]<k){
                return 1;
            }
            return 0;
        }

        int mid = (start+end)/2;
        int left = div(nums, start, mid, k);
        int right = div(nums, mid+1, end, k);

        int p=nums[mid] * nums[mid+1];
        int mc = 0;

        int i=mid;
        int j=mid+1;
        while (i>=start && j<=end){
            if (p<k){
                mc++;
            }
            if (i==start && j==end){
                break;
            }else if (i == start){
                j++;
                p *= nums[j];
            }else if (j==end){
                i--;
                p *= nums[i];
            }else {
                if (nums[i-1]<nums[j+1]){
                    i--;
                    p *= nums[i];
                }else {
                    j++;
                    p *= nums[j];
                }
            }
        }
        return left + right + mc;
    }
}
