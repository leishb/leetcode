package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/6/26.
 */
@BinarySearch
public class _373_KSmallestPairs {

    /**
     * Accepted
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        long[] arr1 = new long[k>nums1.length?nums1.length:k];
        long[] arr2 = new long[k>nums2.length?nums2.length:k];
        for (int i=0;i<arr1.length;i++){
            arr1[i] = nums1[i];
        }
        for (int i=0;i<arr2.length;i++){
            arr2[i] = nums2[i];
        }
        PriorityQueue<long[]> queue = new PriorityQueue<>((Comparator<long[]>) (o1, o2) -> Long.compare(o2[2], o1[2]));
        for (int i=0;i<arr1.length;i++){
            for (int j=0;j<arr2.length;j++){
                long sum = arr1[i] + arr2[j];
                if (queue.size()==k && sum > queue.peek()[2]){
                    break;
                }
                if (queue.size()<k){
                    queue.offer(new long[]{i, j, sum});
                }else if (queue.peek()[2] >= sum){
                    queue.poll();
                    queue.offer(new long[]{i, j, sum});
                }
            }
        }
        while (!queue.isEmpty()){
            long[] arr = queue.poll();
            result.add(0, Arrays.asList(nums1[((int) arr[0])], nums2[(int)arr[1]]));
        }
        return result;
    }

    /**
     * Accepted
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1==null || nums2==null || nums1.length ==0 || nums2.length ==0) return result;
        PriorityQueue<int[]> queue = new PriorityQueue<>((Comparator<int[]>) (o1, o2) -> Integer.compare(o1[2], o2[2]));
        for (int i=0;i<nums1.length;i++){
            queue.offer(new int[]{i, 0, nums1[i]+nums2[0]});
        }
        while (k-->0 && !queue.isEmpty()){
            int[] arr = queue.poll();
            result.add(Arrays.asList(nums1[arr[0]], nums2[arr[1]]));
            if (arr[1]+1<nums2.length){
                queue.offer(new int[]{arr[0], arr[1]+1, nums1[arr[0]]+nums2[arr[1]+1]});
            }
        }
        return result;
    }

    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int ans = 0;
        if (matrix == null || matrix.length==0 || matrix[0].length==0) return ans;
        PriorityQueue<int[]> queue = new PriorityQueue<>((Comparator<int[]>) (o1, o2) -> Integer.compare(o1[2], o2[2]));
        for (int i=0;i<matrix.length;i++){
            queue.offer(new int[]{i, 0, matrix[i][0]});
        }
        while (k-->0 && !queue.isEmpty()){
            int[] arr = queue.poll();
            ans = arr[2];
            if (arr[1]+1<matrix[0].length){
                queue.offer(new int[]{arr[0], arr[1]+1, matrix[arr[0]][arr[1]+1]});
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int ans = 0;
        if (matrix == null || matrix.length==0 || matrix[0].length==0) return ans;
        PriorityQueue<int[]> queue = new PriorityQueue<>((Comparator<int[]>) (o1, o2) -> Integer.compare(o2[2], o1[2]));
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (queue.size()==k && matrix[i][j] >= queue.peek()[2]){
                    break;
                }
                if (queue.size()<k){
                    queue.offer(new int[]{i, j, matrix[i][j]});
                }else if (queue.size()==k && matrix[i][j] < queue.peek()[2]){
                    queue.poll();
                    queue.offer(new int[]{i, j, matrix[i][j]});
                }
            }
        }
        return queue.peek()[2];
    }


    /**
     * Accepted
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest3(int[][] matrix, int k) {
        if (matrix == null || matrix.length==0 || matrix[0].length==0) return 0;
        int lo = matrix[0][0];
        int hi = matrix[matrix.length-1][matrix[0].length-1];
        while (lo<=hi){
            int mid = (hi-lo)/2+lo;
            int count = getLessEqual(matrix, mid);
            if (count<k){
                lo = mid+1;
            }else {
                hi = mid-1;
            }
        }
        return lo;
    }

    private int getLessEqual(int[][] matrix, int val){
        int count = 0;
        int i=0;
        int j=matrix[0].length-1;
        while (i<matrix.length && j>=0){
            if (matrix[i][j] > val){
                j--;
            }else {
                count += j+1;
                i++;
            }
        }
        return count;
    }
    @Test
    public void test(){
        System.out.println(kthSmallest2(new int[][]{{1,2},{1,3}}, 2));
    }
}
