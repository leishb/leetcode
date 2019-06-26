package com.leishb.leetcode.favorite;

import com.leishb.leetcode.tag.MergeSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/6/19.
 */
@MergeSort
public class CountSmaller {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] counts = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            indexes[i] = i;
        }
        merge(0, nums.length-1, nums, counts, indexes);
        List<Integer> ans = new ArrayList<>();
        for (int i: counts){
            ans.add(i);
        }
        return ans;
    }


    private void merge(int start, int end, int[] nums, int[] counts, int[] indexes){
        if (start<end){
            int mid = (start+end)/2;
            merge(start, mid, nums, counts, indexes);
            merge(mid+1, end, nums, counts, indexes);
            mergeSort(start, mid, end, nums, counts, indexes);
        }
    }


    private void mergeSort(int start, int mid,  int end, int[] nums, int[] counts, int[] indexes){
        int i=start;
        int j=mid+1;
        int k=0;
        int rightCount = 0;
        int[] temp = new int[end-start+1];
        while (i<=mid && j<=end){
            if (nums[indexes[i]] > nums[indexes[j]]){
                temp[k++] = indexes[j++];
                rightCount++;
            }else {
                counts[indexes[i]] += rightCount;
                temp[k++] = indexes[i++];
            }
        }
        while (i<=mid){
            counts[indexes[i]] += rightCount;
            temp[k++] = indexes[i++];
        }
        while (j<=end){
            temp[k++] = indexes[j++];
        }
        for (i=0;i<temp.length;i++){
            indexes[start++]=temp[i];
        }
    }



    @Test
    public void test(){
        System.out.println(countSmaller(new int[]{2,0,1}));
        System.out.println(countSmaller(new int[]{6,5}));
        System.out.println(countSmaller(new int[]{1,2,0}));
        System.out.println(countSmaller(new int[]{6,5,1,2}));
        System.out.println(countSmaller(new int[]{5,2,6,1}));
        System.out.println(countSmaller(new int[]{5,2,4,3}));
    }
}
