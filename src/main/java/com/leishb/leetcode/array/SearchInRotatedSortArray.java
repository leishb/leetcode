package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/25.
 */
@BinarySearch
public class SearchInRotatedSortArray {


    @Test
    public void test(){
        Assert.assertTrue(searchInRotateSortArray(new int[]{5,6,7,8,0,1,2,4}, 7)==2);
        Assert.assertTrue(searchInRotateSortArray(new int[]{5,6,7,0,1,2,4}, 7)==2);
        Assert.assertTrue(searchInRotateSortArray(new int[]{0,1,2,3,4,5,6,7}, 2)==2);
        Assert.assertTrue(searchInRotateSortArray(new int[]{0,1,2,3,4,5,6,7}, 7)==7);
        Assert.assertTrue(searchInRotateSortArray(new int[]{3,1,2}, 1)==1);
        Assert.assertTrue(searchInRotateSortArray(new int[]{3,1}, 1)==1);
        Assert.assertTrue(searchInRotateSortArray(new int[]{5,1,2,3,4}, 1)==1);
        Assert.assertTrue(searchInRotateSortArray(new int[]{8,1,2,3,4,5,6,7}, 1)==1);
        Assert.assertTrue(searchInRotateSortArray(new int[]{8}, 8)==0);
        Assert.assertTrue(searchInRotateSortArray(new int[]{2,3,4,5,1}, 1)==4);
        Assert.assertTrue(searchInRotateSortArray(new int[]{5,6,7,8,0,1,2,4}, 1)==5);


        Assert.assertTrue(searchInRotateSortArray2(new int[]{5,6,7,8,0,1,1,2,4}, 1));
        Assert.assertTrue(searchInRotateSortArray2(new int[]{5,6,6,7,8,0,1,1,2,4}, 6));
        Assert.assertTrue(searchInRotateSortArray2(new int[]{2,2,2,2,4,0,1}, 1));
        Assert.assertTrue(searchInRotateSortArray2(new int[]{2,2,2,2,4,0,1}, 2));
        Assert.assertTrue(searchInRotateSortArray2(new int[]{2,2,2,2,4,0,1,1,1}, 1));
        Assert.assertTrue(!searchInRotateSortArray2(new int[]{5,6,6,7,8,0,1,1,2,4}, 9));
        Assert.assertTrue(searchInRotateSortArray2(new int[]{1,3,1,1,1}, 3));


        Assert.assertTrue(findMin(new int[]{5,6,7,8,0,1,1,2,4})==0);
        Assert.assertTrue(findMin(new int[]{5,6,6,7,8,0,1,1,2,4})==0);
        Assert.assertTrue(findMin(new int[]{2,2,2,2,4,0,1})==0);
        Assert.assertTrue(findMin(new int[]{2,2,2,2,4,0,1})==0);
        Assert.assertTrue(findMin(new int[]{2,2,2,2,4,0,1,1,1})==0);
        Assert.assertTrue(findMin(new int[]{5,6,6,7,8,0,1,1,2,4})==0);
        Assert.assertTrue(findMin(new int[]{1,3,1,1,1})==1);
    }


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public boolean searchInRotateSortArray2(int[] nums, int target){
        if (nums.length==0){
            return false;
        }
        int low =0;
        int high = nums.length-1;
        while (low<=high){
            int mid = (high-low)/2 + low;
            if (target==nums[mid]){
                return true;
            }
            if (nums[mid] > nums[high]){
                if (target >= nums[low] && target< nums[mid]){
                    high = mid;
                }else {
                    low=mid+1;
                }
            } else if (nums[mid] < nums[high]){
                if (target > nums[mid] && target<=nums[high]){
                    low=mid+1;
                }else {
                    high=mid;
                }
            }else {
                high--;//nums[mid]==nums[high], no idea, but we can eliminate nums[high];
            }
        }
        return nums[low]==target;
    }


    /**
     * Accepted
     * <br>https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/</br>
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int low =0;
        int high = nums.length-1;
        while (low<=high){
            int mid = (high-low)/2 + low;
            if (mid!=0 && nums[mid-1]>nums[mid]){
                return nums[mid];
            }
            if (nums[mid] > nums[high]){
                low=mid+1;
            } else if (nums[mid] < nums[high]){
                high=mid;
            }else {//When num[mid] == num[hi], we couldn't sure the position of minimum in mid's left or right, so just let upper bound reduce one.
                high--;//nums[mid]==nums[high], no idea, but we can eliminate nums[high];
            }
        }
        return nums[low];
    }

    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotateSortArray(int[] nums, int target){
        int low =0;
        int high = nums.length-1;
        while (low<=high){
            int mid = (high-low)/2 + low;
            if (target==nums[mid]){
                return mid;
            }
            if (nums[mid] >= nums[low]){
                if (target >= nums[low] && target< nums[mid]){
                    high = mid-1;
                }else {
                    low=mid+1;
                }
            } else if (nums[mid] < nums[low]) {
                if (target > nums[mid] && target<=nums[high]){
                    low=mid+1;
                }else {
                    high=mid-1;
                }
            }
        }
        return -1;
    }
}
