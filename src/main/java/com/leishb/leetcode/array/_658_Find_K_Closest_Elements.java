package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/3.
 */
@BinarySearch
public class _658_Find_K_Closest_Elements {


    /**
     * Accepted
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });
        for (int i=0;i<arr.length;i++){
            queue.offer(new int[]{Math.abs(arr[i]-x), i});
        }
        while (k-->0){
            result.add(arr[queue.poll()[1]]);
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Accepted
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr.length==0){
            return result;
        }
        int index = Arrays.binarySearch(arr, x);
        int right = index>=0?index:-(index+1);
        int left = right-1;
        if (left>0 && right<arr.length-1){
            while (right-left<k){
                while (right<arr.length && left>0 &&right-left<k && (Math.abs(x-arr[left]) <= Math.abs(arr[right]-x) || right==arr.length-1)){
                    left--;
                }
                while (right<arr.length-1 && left>=0 && right-left<k && (Math.abs(x-arr[left]) > Math.abs(arr[right]-x) || left==0)){
                    right++;
                }
            }
            if (Math.abs(x-arr[left])>Math.abs(arr[right]-x)){
                left++;
            }else {
                right--;
            }
        }else if (left<=0){
            left=0;
            right = k-1;
        }else {
            right = arr.length-1;
            left = right-k+1;
        }
        for (int i=left;i<=right;i++){
            result.add(arr[i]);
        }
        return result;
    }



    public int binarySearch(int[] arr, int x){
        int hi = arr.length-1;
        int low = 0;
        while (low<=hi){
            int mid = (hi+low)/2;
            if (arr[mid]>x){
                hi = mid-1;
            }else if (arr[mid] < x){
                low = mid+1;
            }else {
                return mid;
            }
        }
        return low;
    }

    @Test
    public void test(){
        System.out.println(findClosestElements2(new int[]{0,1,1,1,2,3,6,7,8,9}, 9, 4));
        Assert.assertTrue(binarySearch(new int[]{0,1,2,3,6,7,8,9}, 4)==4);
        Assert.assertTrue(binarySearch(new int[]{1,2,3,6,7,8,9}, 0)==0);
        Assert.assertTrue(binarySearch(new int[]{1,2,3,6,7,8,9}, 10)==7);
        Assert.assertTrue(binarySearch(new int[]{1,2,3,6,7,8,9}, 5)==3);
    }
}
