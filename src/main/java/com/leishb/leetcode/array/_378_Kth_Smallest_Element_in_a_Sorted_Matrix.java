package com.leishb.leetcode.array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/11/13.
 */
public class _378_Kth_Smallest_Element_in_a_Sorted_Matrix {

    /**
     * Accepted
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m-1][n-1];
        while (lo<=hi){
            int mid = (hi-lo)/2+lo;
            int less = getLessEqualThan(matrix, mid);
            if (less < k){
                lo = mid+1;
            }else {
                hi = mid-1;
            }
        }
        return lo;
    }


    private int getLessEqualThan(int[][] matrix, int val){
        int i=0, j = matrix[0].length-1, count = 0;
        while (i<matrix.length && j>=0){
            if (matrix[i][j] > val){
                j--;
            }else {
                i++;
                count += j+1;
            }
        }
        return count;
    }


    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> minHeap = new PriorityQueue<>((a, b)->matrix[a[0]][a[1]]-matrix[b[0]][b[1]]);

        for (int i=0;i<m;i++){
            minHeap.offer(new int[]{i, 0});
        }
        while (--k>0){
            int[] cur = minHeap.poll();
            if (cur[1]++<n){
                minHeap.offer(cur);
            }
        }
        return matrix[minHeap.peek()[0]][minHeap.peek()[1]];
    }
}
