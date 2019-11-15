package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Created by me on 2019/11/12.
 */
public class _862_Shortest_Subarray_with_Sum_at_Least_K {


    class SegmentTree{
        SegmentTree left;
        SegmentTree right;
        int start;
        int end;
        int min ;
        SegmentTree(int start, int end, int min){
            this.start = start;
            this.end = end;
            this.min = min;
        }
    }


    private SegmentTree buildTree(int[] sum, int start, int end){
        if (start==end){
            return new SegmentTree(start, end, sum[start]);
        }
        int mid = (start+end)/2;
        SegmentTree left = buildTree(sum, start, mid);
        SegmentTree right = buildTree(sum, mid+1, end);
        SegmentTree root = new SegmentTree(start, end, Math.min(left.min, right.min));
        root.left = left;
        root.right = right;
        return root;
    }


    private int query(SegmentTree root, int l, int r, int min){
        if (root==null || l > r)return -1;
        if (root.start > r || root.end < l || root.min > min) return -1;
        if (root.start==root.end && root.min<=min) return root.start;
        int rr = query(root.right, l, r, min);
        if (rr==-1){
            return query(root.left, l, r, min);
        }
        return rr;
    }


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray(int[] A, int K) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for (int i=1;i<A.length;i++){
            sum[i] = sum[i-1]+A[i];
        }
        SegmentTree root = buildTree(sum, 0, A.length-1);
        int ans = A.length+1;
        for (int i=0;i<A.length;i++){
            if (sum[i]>=K) ans = Math.min(ans, i+1);
            int j = query(root, 0, i-1, sum[i]-K);
            if (j!=-1){
                ans = Math.min(ans, i-j);
            }
        }
        return ans==A.length+1?-1:ans;
    }


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray2(int[] A, int K) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for (int i=1;i<A.length;i++){
            sum[i] = sum[i-1]+A[i];
        }
        TreeMap<Long, Integer> tm = new TreeMap<>();
        int ans = A.length+1;
        for (int i=0;i<A.length;i++){
            if (sum[i]>=K)ans = Math.min(ans, i+1);
            long min = sum[i]-K;
            Long lower = tm.floorKey(min);
            while (lower!=null){
                ans = Math.min(ans, i-tm.get(lower));
                tm.remove(lower);
                lower = tm.lowerKey(lower);
            }
            tm.put((long)sum[i], i);
        }
        return ans==A.length+1?-1:ans;
    }


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray3(int[] A, int K) {
        int[] sum = new int[A.length];
        sum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sum[i] = sum[i - 1] + A[i];
        }
        int ans = A.length+1;
        Deque<Integer> dq = new LinkedList<>();
        for (int i=0;i<A.length;i++) {
            if (sum[i] >= K) ans = Math.min(ans, i + 1);
            while (!dq.isEmpty() && sum[i] <= sum[dq.getLast()]){
                dq.removeLast();
            }
            while (!dq.isEmpty() && sum[i]-sum[dq.getFirst()]>=K){
                ans = Math.min(ans, i-dq.getFirst());
                dq.removeFirst();
            }
            dq.addLast(i);
        }
        return ans==A.length+1?-1:ans;
    }
    @Test
    public void test(){
        Assert.assertTrue(shortestSubarray3(new int[]{84,-37,32,40,95}, 167)==3);


    }
}
