package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/12/11.
 */
public class _786_Kth_Smallest_Prime_Fraction {


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        Queue<int[]> pq = new PriorityQueue<>((a, b)->A[a[0]]*A[b[1]]-A[b[0]]*A[a[1]]);
        for (int i=1;i<A.length;i++){
            pq.offer(new int[]{0, i});
        }
        while (--K>0){
            int[] cur = pq.poll();
            if (++cur[0]<cur[1]){
                pq.offer(cur);
            }
        }
        return new int[]{A[pq.peek()[0]], A[pq.peek()[1]]};
    }





    @Test
    public void test(){
    }
}
