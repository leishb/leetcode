package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/3.
 */
@BinarySearch
public class Search2DMatrix {


    @Test
    public void test(){
        Assert.assertTrue(searchMatrix(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 30));
        Assert.assertTrue(searchMatrix(new int[][]{
                {1,   3,  5,  7},
        }, 3));
        Assert.assertTrue(searchMatrix(new int[][]{
                {1,   3,  5,  7},
        }, 1));


        Assert.assertTrue(searchMatrix2(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 30));
        Assert.assertTrue(searchMatrix2(new int[][]{
                {1,   3,  5,  7},
        }, 3));
        Assert.assertTrue(searchMatrix2(new int[][]{
                {1,   3,  5,  7},
        }, 1));
    }


    /**
     * Accepted
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length==0 || matrix[0].length==0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m-1;
        while (low<=high){
            int mid = (high-low)/2 + low;
            if (matrix[mid][0]==target){
                return true;
            }
            if (matrix[mid][0]>target){
                high=mid-1;
            }
            if (matrix[mid][0]<target){
                low=mid+1;
            }
        }
        if (low==0){
            return false;
        }
        int left = 0;
        int right = n-1;
        while (left<=right){
            int mid = (right-left)/2 + left;
            if (matrix[low-1][mid]==target){
                return true;
            }
            if (matrix[low-1][mid]>target){
                right=mid-1;
            }
            if (matrix[low-1][mid]<target){
                left=mid+1;
            }
        }
        return false;
    }

    /**
     * Accepted
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m*n-1;
        while (low<=high){
            int mid = (high-low)/2 + low;
            int j = mid%n;
            int i= (mid-j)/n;
            if (matrix[i][j]==target){
                return true;
            }
            if (matrix[i][j]>target){
                high=mid-1;
            }
            if (matrix[i][j]<target){
                low=mid+1;
            }
        }
        return false;
    }
}
