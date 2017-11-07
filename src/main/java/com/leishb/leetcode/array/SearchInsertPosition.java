package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/26.
 */
@BinarySearch
public class SearchInsertPosition {


    @Test
    public void test(){
        Assert.assertTrue(searchInsertPosition(new int[]{1,3,5,6}, 5)==2);
        Assert.assertTrue(searchInsertPosition(new int[]{1,3,5,6}, 2)==1);
        Assert.assertTrue(searchInsertPosition(new int[]{1,3,5,6}, 7)==4);
        Assert.assertTrue(searchInsertPosition(new int[]{1,3,5,6}, 0)==0);
        Assert.assertTrue(searchInsertPosition(new int[]{1,3,5}, 1)==0);



        Assert.assertTrue(searchInsertPosition2(new int[]{1,3,5,6}, 5)==2);
        Assert.assertTrue(searchInsertPosition2(new int[]{1,3,5,6}, 2)==1);
        Assert.assertTrue(searchInsertPosition2(new int[]{1,3,5,6}, 7)==4);
        Assert.assertTrue(searchInsertPosition2(new int[]{1,3,5,6}, 0)==0);
        Assert.assertTrue(searchInsertPosition2(new int[]{1,3,5}, 1)==0);
    }

    public int searchInsertPosition2(int[] nums, int k){
        int low = 0;
        int high = nums.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (nums[mid]==k){
                return mid;
            }else if (nums[mid] > k){
                high=mid-1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int searchInsertPosition(int[] nums, int k){
        int index = -1;
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == k){
                index = mid;
                break;
            }
            if (nums[mid] > k){
                if (mid==0){
                    index = 0;
                    break;
                }else if (nums[mid-1] < k){
                    index = mid;
                    break;
                } else if (nums[mid-1] >k){
                    right = mid-1;
                } else if (nums[mid-1] ==k){
                    index = mid-1;
                    break;
                }
            }
            if (nums[mid] <k){
                if (mid == nums.length-1){
                    index = nums.length;
                    break;
                }else if (nums[mid + 1] >k){
                    index = mid+1;
                    break;
                }else if (nums[mid + 1] <k){
                    left = mid+1;
                }else if (nums[mid+1]==k){
                    index = mid+1;
                    break;
                }
            }
        }
        return index;
    }
}
