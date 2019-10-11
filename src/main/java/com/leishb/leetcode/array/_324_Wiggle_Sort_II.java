package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/10/9.
 */
public class _324_Wiggle_Sort_II {



    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int k = select(nums, n/2, 0,  nums.length-1);
        int[] tempArr = new int[n];
        int odd = 1;
        int even = n%2==0?n-2:n-1;
        for (int i=0;i<nums.length;i++){
            if (nums[i] > nums[k]){
                tempArr[odd] = nums[i];
                odd+=2;
            }
            if (nums[i] < nums[k]){
                tempArr[even] = nums[i];
                even -=2;
            }
        }
        while (odd<nums.length){
            tempArr[odd] = nums[k];
            odd+=2;
        }
        while (even >=0){
            tempArr[even] = nums[k];
            even-=2;
        }
        for (int i=0;i<n;i++){
            nums[i] = tempArr[i];
        }
    }



    private int select(int[] nums, int k, int start, int end){
        int p = partition(nums, start, end);
        if (p==k)return k;
        if (p > k){
            return select(nums, k, start, p-1);
        }else {
            return select(nums, k, p+1, end);
        }
    }


    private int partition(int[] nums, int start, int end){
        int pivot = nums[start];
        int i=start, j=end;
        while (i<=j){
            while (i<=j && nums[i]<=pivot){
                i++;
            }
            while (i<=j && nums[j]>pivot){
                j--;
            }
            if (i<j){
                swap(nums, i, j);
            }
        }
        swap(nums, start, j);
        return j;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void wiggleSortI(int[] nums) {
        Arrays.sort(nums);

    }

    @Test
    public void test(){
        wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
        wiggleSort(new int[]{1,1,2,1,2,2,1});
        wiggleSort(new int[]{4,5,5,6});
        wiggleSort(new int[]{5,3,1,2,6,7,8,5,5});
    }
}
