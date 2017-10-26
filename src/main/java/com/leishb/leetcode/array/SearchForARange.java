package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/25.
 */
@BinarySearch
public class SearchForARange {


    @Test
    public void test(){
        Assert.assertArrayEquals(searchForRange(new int[]{5,7,7,8,8,10}, 8), new int[]{3,4});
        Assert.assertArrayEquals(searchForRange(new int[]{0,0,0,0,0,0}, 0), new int[]{0,5});
        Assert.assertArrayEquals(searchForRange(new int[]{0,0,0,0,0,0}, 1), new int[]{-1,-1});
        Assert.assertArrayEquals(searchForRange(new int[]{5,6,7,8,9}, 7), new int[]{2,2});

        Assert.assertArrayEquals(searchForRange2(new int[]{5,7,7,8,8,10}, 8), new int[]{3,4});
        Assert.assertArrayEquals(searchForRange2(new int[]{0,0,0,0,0,0}, 0), new int[]{0,5});
        Assert.assertArrayEquals(searchForRange2(new int[]{0,0,0,0,0,0}, 1), new int[]{-1,-1});
        Assert.assertArrayEquals(searchForRange2(new int[]{5,6,7,8,9}, 7), new int[]{2,2});
        Assert.assertArrayEquals(searchForRange2(new int[]{5,5,7,8,9}, 5), new int[]{0,1});
        Assert.assertArrayEquals(searchForRange2(new int[]{5,5,7,8,8}, 8), new int[]{3,4});
    }

    public int[] searchForRange(int[] nums, int target){
        int midIndex = -1;
        int leftIndex = -1;
        int rightIndex = -1;
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == target){
                midIndex = mid;
                break;
            }
            if (nums[mid] > target){
                right = mid-1;
            }
            if (nums[mid] < target){
                left = mid +1;
            }
        }
        if (midIndex==-1){
            return new int[]{-1,-1};
        }
        //向左查找
        right = midIndex-1;
        while (left <= right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == target){
                leftIndex = mid;
                right = mid-1;
            }
            if (nums[mid] > target){
                right = mid-1;
            }
            if (nums[mid] < target){
                left = mid +1;
            }
        }
        //向右查找
        left = midIndex +1;
        right = nums.length-1;
        while (left <= right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == target){
                rightIndex = mid;
                left = mid+1;
            }
            if (nums[mid] > target){
                right = mid-1;
            }
            if (nums[mid] < target){
                left = mid +1;
            }
        }
        return new int[]{leftIndex==-1 ? midIndex : leftIndex, rightIndex == -1?midIndex:rightIndex};
    }


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public int[] searchForRange2(int[] nums, int target){
        int midIndex = -1;
        int leftIndex = -1;
        int rightIndex = -1;
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == target){
                if (midIndex == -1){
                    midIndex = mid;
                }
                //向左查找
                leftIndex = mid;
                right = mid -1;
            }
            if (nums[mid] > target){
                right = mid-1;
            }
            if (nums[mid] < target){
                left = mid +1;
            }
        }
        if (midIndex==-1){
            return new int[]{-1,-1};
        }
        //向右查找
        left = midIndex +1;
        right = nums.length-1;
        while (left <= right){
            int mid = (right-left)/2 + left;
            if (nums[mid] == target){
                rightIndex = mid;
                left = mid+1;
            }
            if (nums[mid] > target){
                right = mid-1;
            }
            if (nums[mid] < target){
                left = mid +1;
            }
        }
        return new int[]{leftIndex==-1 ? midIndex : leftIndex, rightIndex == -1?midIndex:rightIndex};
    }
}
