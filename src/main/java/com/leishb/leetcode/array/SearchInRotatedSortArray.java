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
    }


    /**
     * Wrong
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotateSortArray(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;
        int splitIndex = -1;
        while (left<=right){
            int mid = (right-left)/2+left;
            if (nums[mid] > nums[left] && nums[mid] > nums[right]){//5,6,7,8,0,1,2,4 : 7
                left = mid+1;
            }else if (mid!=0 && nums[mid] < nums[mid-1]){//5,6,7,8,0,1,2,4 : 0
                splitIndex = mid;
                break;
            }else if (mid != 0 && nums[mid] > nums[mid-1]){ //5,6,7,8,0,1,2,4 : 2
                right = mid-1;
                splitIndex = mid;
            }
            if (mid==0){
                if (mid < nums.length-1 && nums[mid] > nums[mid+1]) {//3,1 : 1
                    splitIndex = mid + 1;
                    break;
                }else if (mid == nums.length-1){// len =1
                    splitIndex = mid;
                    break;
                } else {//0,1,2,3,4
                    right = mid-1;
                    splitIndex = mid;
                }
            }
        }
        if (splitIndex==0||splitIndex==-1){
            left =0;
            right = nums.length-1;
        }else {
            if (nums[splitIndex-1] >= target && nums[0]<=target){//在左侧
                left = 0;
                right = splitIndex-1;
            }else {//在右侧
                left = splitIndex;
                right = nums.length-1;
            }
        }
        while (left<=right){
            int mid = (right-left)/2+left;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] <target){
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        return -1;
    }
}
